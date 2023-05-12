package mvc.presentation;

import mvc.bussinessLogic.BussinessLogic;
import mvc.dataAccess.ClientDAO;
import mvc.dataAccess.OrderDAO;
import mvc.dataAccess.ProductDAO;
import mvc.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public class Controller {

    private View view;
    private ViewClient viewClient;
    private ViewOrder viewOrder;
    private ViewProduct viewProduct;
    private ClientDAO clientDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private BussinessLogic logica;




    public Controller(View view) {
        this.view = view;

        logica = new BussinessLogic();
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
            String name = viewProduct.getNameTextField();
            double price = viewProduct.getPriceTextField();
            int q = viewProduct.getQuantityTextField();
            Product p = new Product(name, price, q);
            productDAO.insert(p);
        }
    }

    class ProductDelete implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = viewProduct.getIdTextField();
            productDAO.deleteById(id);

        }
    }class ProductEdit implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = viewProduct.getIdTextField();
            String name = viewProduct.getNameTextField();
            double price = viewProduct.getPriceTextField();
            int q = viewProduct.getQuantityTextField();
            Product p = new Product(id ,name, price, q);

            productDAO.update(p);
        }
    }
    class ProductViewListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            ///show the products
            JTable table = viewProduct.getTable();
            logica.showProducts(table);
        }
    }


    ///separator



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
            ////VA TREBUIE TERMINAT MAI INCOLO
            int cId = viewOrder.getClientIdTextField();//id needs to be checked that exists
            int ok = clientDAO.findById(cId);
            if (ok == 0)
            {
                System.out.println("NU EXISTA ACEL CLIENT");
                return;
            }
            int pId = viewOrder.getProductIdTextField();//same here
            ok = productDAO.findById(pId);
            if (ok == 0)
            {
                System.out.println("NU EXISTA ACEL PRODUS IN BAZA DE DATE");
                return;
            }

            int q = viewOrder.getQuantityTextField();//qty must be checked
            Product product = productDAO.findByIdObject(pId);
            int currentQuantityInStock = product.getQuantity();
            if (q > currentQuantityInStock)
            {
                System.out.println("NU EXISTA SUFICIENT IN STOC");
                return;
            }

            Orders o = new Orders(cId, pId, q);
            orderDAO.insert(o);

            product.setQuantity(product.getQuantity() - q);
            productDAO.update(product);
        }
    }

    class OrderViewListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = viewOrder.getTable();
            logica.showOrders(table);
        }
    }


    ///separator


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
            int age = viewClient.getAgeTextField();
            String name = viewClient.getNameTextField();
            String address = viewClient.getAddressTextField();
            int id = viewClient.getIdTextField();

            logica.editClient(name, id ,address, age);
        }
    }
    class ClientDeleteListener implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
//            DELETE FROM users
//            WHERE id = 7;
            int id = viewClient.getIdTextField();
            logica.deleteClient(id);
        }
    }

    class ClientShowListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ///shows the clients
//            List<Client> clients;
//            clients = getClients();
//
//            JTable table = viewClient.getTable();
//            populateTheTabel(table, clients);
//            System.out.println("CLIENTII AU FOST AFISATI");
            logica.showClients(viewClient.getTable());
        }
    }

    class ClientAddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            int age = viewClient.getAgeTextField();
            String name = viewClient.getNameTextField();
            String address = viewClient.getAddressTextField();
            logica.addClient(age, name, address);
        }
    }
}
