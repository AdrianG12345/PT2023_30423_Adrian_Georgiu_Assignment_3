package mvc.controller;

import mvc.models.Client;
import mvc.models.Order;
import mvc.models.Product;
import mvc.view.View;
import mvc.view.ViewClient;
import mvc.view.ViewOrder;
import mvc.view.ViewProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

public class Controller {

    private View view;
    private ViewClient viewClient;
    private ViewOrder viewOrder;
    private ViewProduct viewProduct;




    public Controller(View view) {
        this.view = view;
        this.view.addOrderListener(new OrderListener());
        this.view.addProductListener(new ProductListener());
        this.view.addClientListener(new ClientListener());
    }

    class ProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("S-A APASAT PRODUCT");

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
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);

            ArrayList<Product> products = getProducts();
            // Extract column names using reflection
            Class<?> clazz = products.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                model.addColumn(name);
            }

            // Populate the table with data
            for (Object obj : products) {
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
            System.out.println("S-A APASAT ORDER");

            viewOrder = new ViewOrder();
            viewOrder.setVisible(true);

            viewOrder.viewOrders(new OrderViewListener());
        }
    }


    class OrderViewListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Order> orders = getOrders();

            JTable table = viewOrder.getTable();
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);


            Class<?> clazz = orders.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();

            ///makes the header
            for (Field field : fields) {
                String name = field.getName();
                model.addColumn(name);
            }

            ///populate the table with clients
            for (Object obj : orders)
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
            System.out.println("S-A APASAT Client");

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

        }
    }
    class ClientDeleteListener implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ClientShowListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ///shows the clients
            ArrayList<Client> clients;
            clients = getClients();


            JTable table = viewClient.getTable();
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);


            Class<?> clazz = clients.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();

            ///makes the header
            for (Field field : fields) {
                String name = field.getName();
                model.addColumn(name);
            }

            ///populate the table with clients
            for (Object obj : clients)
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


//            DefaultTableModel model = new DefaultTableModel();
//            table.setModel(model);
//
//            // Extract column names using reflection
//            Class<?> clazz = list.get(0).getClass();
//            Field[] fields = clazz.getDeclaredFields();
//            for (Field field : fields) {
//                String name = field.getName();
//                model.addColumn(name);
//            }
//
//            // Populate the table with data
//            for (Object obj : list) {
//                Vector<Object> row = new Vector<>();
//                for (Field field : fields) {
//                    field.setAccessible(true);
//                    try {
//                        Object value = field.get(obj);
//                        row.add(value);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//                model.addRow(row);
//            }


        }
    }
    ArrayList<Client> getClients()
    {
        ArrayList<Client> clienti = new ArrayList<>();

        clienti.add(new Client(1,"Mama" ,10, "aici"));
        clienti.add(new Client(2, "Andrei", 15, "la tine"));
        clienti.add(new Client(3, "David", 70, "la mine"));

        return clienti;
    }

    class ClientAddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }




}