package mvc.bussinessLogic;

import mvc.dataAccess.ClientDAO;
import mvc.dataAccess.OrderDAO;
import mvc.dataAccess.ProductDAO;
import mvc.models.Client;
import mvc.models.Product;
import mvc.presentation.ViewProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

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

    public void deleteClient(int id)
    {
        if (id == -1)
            return;

        clientDAO.deleteById(id);
    }
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
    public void showProducts(JTable table)
    {
        populateTheTabel(table, productDAO.findAll());
        System.out.println("S-AU AFISAT PRODUCTS");
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
