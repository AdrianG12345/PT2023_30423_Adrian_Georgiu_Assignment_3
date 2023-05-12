package mvc.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewOrder extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTextField clientIdTextField;
    private JTextField productIdTextField;
    private JTextField quantityTextField;
    private JButton createOrderButton;
    private JButton viewButton;



    public ViewOrder()
    {
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1099, 679);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Orders");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        titleLabel.setBounds(77, 24, 206, 45);
        contentPane.add(titleLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(484, 78, 518, 529);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        clientIdTextField = new JTextField();
        clientIdTextField.setBounds(230, 102, 96, 19);
        contentPane.add(clientIdTextField);
        clientIdTextField.setColumns(10);

        viewButton = new JButton("View orders");
        viewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        viewButton.setBounds(180, 372, 189, 27);
        contentPane.add(viewButton);

        productIdTextField = new JTextField();
        productIdTextField.setColumns(10);
        productIdTextField.setBounds(230, 140, 96, 19);
        contentPane.add(productIdTextField);

        JLabel clientIdLabel = new JLabel("Client Id for order");
        clientIdLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
        clientIdLabel.setBounds(10, 95, 189, 19);
        contentPane.add(clientIdLabel);

        JLabel productIdLabel = new JLabel("Product Id for order");
        productIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        productIdLabel.setBounds(10, 138, 192, 16);
        contentPane.add(productIdLabel);

        JLabel quantityLabel = new JLabel("Desired quantity:\r\n");
        quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        quantityLabel.setBounds(10, 184, 177, 19);
        contentPane.add(quantityLabel);

        quantityTextField = new JTextField();
        quantityTextField.setColumns(10);
        quantityTextField.setBounds(230, 187, 96, 19);
        contentPane.add(quantityTextField);

        createOrderButton = new JButton("Create Order\r\n");
        createOrderButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        createOrderButton.setBounds(180, 287, 187, 33);
        contentPane.add(createOrderButton);
    }

    public void createOrder(ActionListener action)
    {
        this.createOrderButton.addActionListener(action);
    }
    public void viewOrders(ActionListener action)
    {
        this.viewButton.addActionListener(action);
    }

    public JTable getTable() {
        return table;
    }
}
