package mvc.dataAccess;

import mvc.models.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        // TODO:
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        String query = "SELECT * FROM " + type.getSimpleName();
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        return null;
    }

    public int findById(int id) {///checks to see if it exists
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            List<T> list = createObjects(resultSet);
            if (list.size() != 0)
                return 1;
            return 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }
    public T findByIdObject(int id) {///checks to see if it exists
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {///cauta constructorul fara parametrii
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);///obtine valoarea din tabel mysql
                    ///din query
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);///aici da set la fiecare parametru cu valoarea din tabele
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t) {///add?
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "INSERT INTO " + type.getSimpleName() + "  (";
        try {
            connection = ConnectionFactory.getConnection();
            Field[] fields = type.getDeclaredFields();
            for (int i = 1; i < fields.length; i++)///starting from 1 to avoid id
            {
                if (fields[i].getName().equals("id"))
                    continue;
                if (i > 1)
                    query += ",";
                query += fields[i].getName();
            }
            query += ") values (";
            for (int i = 1; i < fields.length; i++) {
                if (i > 1) {
                    query += ",";
                }
                query += "?";
            }
            query += ")";

            statement = connection.prepareStatement(query);

            int parameterIndex = 1;
            for (Field field : fields) {
                if (field.getName().equals("id"))
                    continue;
                field.setAccessible(true);
                statement.setObject(parameterIndex++, field.get(t));
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        }

        return t;
    }

    public T update(T t) {///edit?
        // TODO:
        //UPDATE users
//            SET name = 'John', age = 30
//            WHERE id = 7;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "UPDATE " + type.getSimpleName() + " SET ";

        // get all the fields of the object
        Field[] fields = type.getDeclaredFields();

        // append each field name with a '?' placeholder to the query string
        for (Field field : fields) {
            if (field.getName().equals("id"))
                continue;
            query += field.getName() + " = ?, ";
        }

        // remove the last comma and space
        query = query.substring(0, query.length() - 2);

        // append the WHERE clause to the query string
        query += " WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            // set the values of the object's fields as parameters in the prepared statement
            int i = 1;
            for (Field field : fields) {
                if (field.getName().equals("id"))
                    continue;
                field.setAccessible(true);
                Object value = field.get(t);
                statement.setObject(i, value);
                i++;
            }

            // set the value of the id field as the last parameter in the prepared statement
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(t);
            statement.setObject(i, idValue);

            //execute the statement
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        }
        return t;
    }

    public void deleteById(int id)
    {
//            DELETE FROM users
//            WHERE id = 7;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
