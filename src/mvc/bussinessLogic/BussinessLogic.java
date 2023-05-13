package mvc.bussinessLogic;

import mvc.dataAccess.ClientDAO;
import mvc.dataAccess.OrderDAO;
import mvc.dataAccess.ProductDAO;
import mvc.models.Client;
import mvc.models.Orders;
import mvc.models.Product;
import mvc.presentation.ViewProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

/**
 * uses the dataAccess classes to access the database "ordersmanagement"
 *
 */
public class BussinessLogic {
    ///aici is functiile pe care controller le va apela
    ///iar aceasta clasa va apela DAO
    private ClientDAO clientDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public BussinessLogic()
    {
        this.clientDAO = new ClientDAO();
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
    }

    /**
     * adds a new client to the table client
     * checks first if all the parameters are valid
     * @param age
     * @param name
     * @param address
     */
    public void addClient(int age, String name, String address)
    {
        if (age == -1)
            return;
        if (name == null)
            return;
        if (address == null)
            return;
        clientDAO.insert(new Client(name, age, address));
        System.out.println("CLIENTUL A FOST ADAUGAT CU SUCCES");
    }

    /**
     * deletes the client with id = param.id
     * @param id
     */
    public void deleteClient(int id)
    {
        if (id == -1)
            return;
        clientDAO.deleteById(id);
    }

    /**
     * edits a client with parameters
     * @param name
     * @param id
     * @param address
     * @param age
     */
    public void editClient(String name, int id, String address, int age)
    {
        if (age == -1)
            return;
        if (name == null)
            return;
        if (address == null)
            return;
        if (id == -1)
            return;
        clientDAO.update(new Client(id, name, age, address));
        System.out.println("S-A ACTUALIZAT CU SUCCESS");
    }

    /**
     * shows the client  table in the given JTable
     * @param table
     */
    public void showClients(JTable table)
    {
        populateTheTabel(table, clientDAO.findAll());
        System.out.println("S-AU AFISAT CLIENTII");
    }


    public void showOrders(JTable table)
    {
        populateTheTabel(table, orderDAO.findAll());
        System.out.println("S-AU AFISAT ORDERS");
    }

    /**
     * inserts a new order if it is possible
     * @param cId checks if it is valid
     * @param pId same
     * @param qty checks if valid and if enough products are in stock
     *
     */
    public void insertOrder(int cId,int pId, int qty)
    {
        int ok = clientDAO.findById(cId);
        if (ok == 0)
        {
            System.out.println("NU EXISTA ACEL CLIENT");
            return;
        }
        ok = productDAO.findById(pId);
        if (ok == 0)
        {
            System.out.println("NU EXISTA ACEL PRODUS IN BAZA DE DATE");
            return;
        }
        Product product = productDAO.findByIdObject(pId);
        int currentQuantityInStock = product.getQuantity();
        if (qty > currentQuantityInStock)
        {
            System.out.println("NU EXISTA SUFICIENT IN STOC");
            return;
        }
        /**
         * if code got this far it means that the order can in fact made
         */
        Orders o = new Orders(cId, pId, qty);
        orderDAO.insert(o);

        product.setQuantity(product.getQuantity() - qty);
        productDAO.update(product);
    }
    public void showProducts(JTable table)
    {
        populateTheTabel(table, productDAO.findAll());
        System.out.println("S-AU AFISAT PRODUCTS");
    }

    /**
     * edits an already existing product with the id = param id
     * and changes the other columns of the table with the other given parameters
     * @param id must not be -1
     * @param name must exist
     * @param price must not be -1
     * @param q must not be -1
     */
    public void editProduct(int id,String name,double price,int q)
    {
        if (id == -1)
            return;
        if (name == null)
            return;
        if (price == -1)
            return;
        if (q == -1)
            return;
        Product p = new Product(id ,name, price, q);
        productDAO.update(p);
    }

    /**
     * deletes the product with the id given as parameter
     * @param id
     */
    public void deleteProduct(int id)
    {
        if (id == -1)
            return;
        productDAO.deleteById(id);
    }
    /**
     * inserts a new product if possible
     */
    public void insertProduct(String name, double price, int q)
    {
        if (name == null)
            return;
        if (price == -1)
            return;
        if (q == -1)
            return;
        Product p = new Product(name, price, q);
        productDAO.insert(p);
    }

    /**
     * puts the element of the list into the table with reflection
     * enables the use of list of unknown type to be placed in the table
     * @param table the table in which the elemetns will be placed
     * @param list the list of elements that will be placed in the table; each element on a
     *             different row
     */
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
