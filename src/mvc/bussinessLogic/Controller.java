package mvc.bussinessLogic;

import mvc.dataAccess.ClientDAO;
import mvc.dataAccess.OrderDAO;
import mvc.dataAccess.ProductDAO;
import mvc.models.*;
import mvc.presentation.View;
import mvc.presentation.ViewClient;
import mvc.presentation.ViewOrder;
import mvc.presentation.ViewProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Controller {

    private View view;
    private DatabaseConnection databaseConnection;
    private ViewClient viewClient;
    private ViewOrder viewOrder;
    private ViewProduct viewProduct;
    private ClientDAO clientDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;




    public Controller(View view, DatabaseConnection databaseConnection) {
        this.view = view;
        this.databaseConnection = databaseConnection;

        this.clientDAO = new ClientDAO();
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();

        this.view.addOrderListener(new OrderListener());
        this.view.addProductListener(new ProductListener());
        this.view.addClientListener(new ClientListener());
    }

    class ProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewProduct = new ViewProduct();
            viewProduct.setVisible(true);

            viewProduct.addProduct(new ProductAdd());
            viewProduct.editProduct(new ProductEdit());
            viewProduct.deleteProduct(new ProductDelete());
            viewProduct.viewProduct(new ProductViewListener());
        }
    }
    class ProductAdd implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ProductDelete implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }class ProductEdit implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    class ProductViewListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            ///show the products
            JTable table = viewProduct.getTable();
            ArrayList<Product> products = getProducts();
            //populateTheTabel(table, products);
        }
    }
    ArrayList<Product> getProducts()
    {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(1, "Pepsi", 2.5, 10));
        products.add(new Product(2, "Cola", 2.75, 50));
        products.add(new Product(3, "Candy", 1, 1000));

        return products;
    }
















    class OrderListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewOrder = new ViewOrder();
            viewOrder.setVisible(true);

            viewOrder.viewOrders(new OrderViewListener());
            viewOrder.createOrder(new OrderCreateListener());
        }
    }
    class OrderCreateListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("CREATE ORDER!");
        }
    }

    class OrderViewListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Order> orders = getOrders();
            JTable table = viewOrder.getTable();
            //populateTheTabel(table, orders);
        }
    }
    ArrayList<Order> getOrders()
    {
        ArrayList<Order> orders = new ArrayList<>();

        orders.add(new Order(1, 1, 1, 10));
        orders.add(new Order(2, 1, 1, 5));
        orders.add(new Order(3, 10,2, 1));

        return orders;
    }






    class ClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewClient = new ViewClient();
            viewClient.setVisible(true);

            viewClient.addLisneter(new ClientAddListener());
            viewClient.editListener(new ClientEditListener());
            viewClient.deleteListener(new ClientDeleteListener());
            viewClient.showListener(new ClientShowListener());
        }
    }
    class ClientEditListener implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
//            UPDATE users
//            SET name = 'John', age = 30
//            WHERE id = 7;
            try{
                Connection connection = ConnectionFactory.getConnection();
                String sql = "UPDATE client SET name = ?, age = ?, address = ? WHERE idClient = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, viewClient.getNameTextField());
                statement.setInt(2, viewClient.getAgeTextField());
                statement.setString(3, viewClient.getAddressTextField());
                statement.setInt(4, viewClient.getIdTextField());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Row with id = 7 updated successfully.");
                } else {
                    System.out.println("No rows were updated.");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }


        }
    }
    class ClientDeleteListener implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
//            DELETE FROM users
//            WHERE id = 7;
            try{
                Connection connection = ConnectionFactory.getConnection();
                String sql = "DELETE FROM client WHERE idClient = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                System.out.println(viewClient.getIdTextField());
                statement.setInt(1, viewClient.getIdTextField());
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Row with id = 7 deleted successfully.");
                } else {
                    System.out.println("No rows were deleted.");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    class ClientShowListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ///shows the clients
            List<Client> clients;
            clients = getClients();

            JTable table = viewClient.getTable();
            populateTheTabel(table, clients);
        }
    }
    List<Client> getClients()
    {
        //ArrayList<Client> clienti = new ArrayList<>();
        List<Client> clienti = clientDAO.findAll();
//        try{
//            Connection connection = ConnectionFactory.getConnection();
//            String sql = "SELECT * FROM client";
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(sql);
//
//            String name = new String();
//            String address = new String();
//            int id;
//            int age;
//            while (result.next())
//            {
//                id = Integer.parseInt(result.getString(1));
//                name = result.getString(2);
//                age = Integer.parseInt(result.getString(3));
//                address = result.getString(4);
//
//                clienti.add(new Client(id, name, age, address));
//            }
//        }
//        catch (Exception ex)
//        {
//            System.out.println("EROARE EXCEPTION!");
//        }

        return clienti;
    }

    class ClientAddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("insert into client ( name, age, address)" + "values ( ?, ?, ?)");

                preparedStatement.setString(1, viewClient.getNameTextField());
                preparedStatement.setInt(2, viewClient.getAgeTextField());
                preparedStatement.setString(3, viewClient.getAddressTextField());

                preparedStatement.executeUpdate();

                System.out.println("SUCCES!");
            } catch (Exception ex) {
                System.out.println("EROARE");
            }
        }
    }


    void populateTheTabel(JTable table, List<?> list)
    {
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        Class<?> clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        ///makes the header
        for (Field field : fields) {
            String name = field.getName();
            model.addColumn(name);
        }

        ///populate the table with clients
        for (Object obj : list)
        {
            Vector<Object> row = new Vector<>();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    row.add(value);
                } catch (IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
            model.addRow(row);
        }
    }


}
