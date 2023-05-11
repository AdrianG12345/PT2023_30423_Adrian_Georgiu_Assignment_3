package mvc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JPanel contentPane;
    private JButton productButton;

    private JButton clientButton;

    private JButton orderButton;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 911, 502);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Database Menu");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titleLabel.setBounds(268, 38, 335, 45);
        contentPane.add(titleLabel);

        clientButton = new JButton("Clients\r\n");
        clientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        clientButton.setBounds(38, 197, 244, 88);
        contentPane.add(clientButton);

        orderButton = new JButton("Orders\r\n");
        orderButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
        orderButton.setBounds(281, 197, 244, 88);
        contentPane.add(orderButton);

        productButton = new JButton("Product");
        productButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
        productButton.setBounds(524, 197, 244, 88);
        contentPane.add(productButton);


        this.setVisible(true);
    }

    public void addOrderListener(ActionListener action) {
        this.orderButton.addActionListener(action);
    }

    public void addProductListener(ActionListener action)
    {
        this.productButton.addActionListener(action);
    }

    public void addClientListener(ActionListener action)
    {
        this.clientButton.addActionListener(action);
    }
}
