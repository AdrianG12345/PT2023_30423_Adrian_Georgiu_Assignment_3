package mvc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewOrder extends JFrame {
    private JPanel contentPane;
    private JTextField clientIdTextField;
    private JTextField pepsiTextField;
    private JTextField colaTextField;
    private JTextField oreoTextField;
    private JTextField vodkaTextField;
    private JTextField rumTextField;
    private JTextField milkaTextField;
    private JTextField beefTextField;
    private JButton vodkaButton;
    private JButton rumButton;
    private JButton milkaButton;
    private JButton beefButton;
    private JTextArea nrClientsTextArea;
    private JButton colaButton;
    private JButton oreoButton;
    private JButton pepsiButton;
    private JButton viewButton;
    private JTable table;


    public ViewOrder()
    {
        setBounds(100, 100, 1099, 679);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Orders");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        titleLabel.setBounds(344, 10, 335, 45);
        contentPane.add(titleLabel);

        pepsiButton = new JButton("Add");
        pepsiButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        pepsiButton.setBounds(10, 141, 94, 27);
        contentPane.add(pepsiButton);

        JLabel clientLabel = new JLabel("Client id and up to:");
        clientLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
        clientLabel.setBounds(49, 50, 194, 38);
        contentPane.add(clientLabel);

        clientIdTextField = new JTextField();
        clientIdTextField.setBounds(8, 98, 96, 19);
        contentPane.add(clientIdTextField);
        clientIdTextField.setColumns(10);

        nrClientsTextArea = new JTextArea();
        nrClientsTextArea.setBounds(132, 98, 59, 22);
        contentPane.add(nrClientsTextArea);

        colaButton = new JButton("Add");
        colaButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        colaButton.setBounds(10, 189, 94, 27);
        contentPane.add(colaButton);

        oreoButton = new JButton("Add");
        oreoButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        oreoButton.setBounds(10, 234, 94, 27);
        contentPane.add(oreoButton);

        vodkaButton = new JButton("Add");
        vodkaButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        vodkaButton.setBounds(10, 285, 94, 27);
        contentPane.add(vodkaButton);

        rumButton = new JButton("Add");
        rumButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        rumButton.setBounds(10, 342, 94, 27);
        contentPane.add(rumButton);

        milkaButton = new JButton("Add");
        milkaButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        milkaButton.setBounds(10, 393, 94, 27);
        contentPane.add(milkaButton);

        beefButton = new JButton("Add");
        beefButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        beefButton.setBounds(10, 454, 94, 27);
        contentPane.add(beefButton);

        JLabel productLabel1 = new JLabel("Pepsi");
        productLabel1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        productLabel1.setBounds(146, 141, 106, 27);
        contentPane.add(productLabel1);

        JLabel productLabel2 = new JLabel("Cola");
        productLabel2.setFont(new Font("Tahoma", Font.PLAIN, 19));
        productLabel2.setBounds(132, 189, 106, 27);
        contentPane.add(productLabel2);

        JLabel lblOreo = new JLabel("Oreo");
        lblOreo.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblOreo.setBounds(132, 234, 106, 27);
        contentPane.add(lblOreo);

        JLabel lblVodka = new JLabel("Vodka");
        lblVodka.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblVodka.setBounds(132, 285, 106, 27);
        contentPane.add(lblVodka);

        JLabel lblCaptainMorgan = new JLabel("Captain Morgan");
        lblCaptainMorgan.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblCaptainMorgan.setBounds(137, 342, 150, 27);
        contentPane.add(lblCaptainMorgan);

        JLabel lblMilka = new JLabel("Milka");
        lblMilka.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblMilka.setBounds(137, 393, 106, 27);
        contentPane.add(lblMilka);

        JLabel lblMeat = new JLabel("Beef");
        lblMeat.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblMeat.setBounds(137, 454, 106, 27);
        contentPane.add(lblMeat);

        pepsiTextField = new JTextField();
        pepsiTextField.setBounds(235, 149, 96, 19);
        contentPane.add(pepsiTextField);
        pepsiTextField.setColumns(10);

        colaTextField = new JTextField();
        colaTextField.setColumns(10);
        colaTextField.setBounds(224, 197, 96, 19);
        contentPane.add(colaTextField);

        oreoTextField = new JTextField();
        oreoTextField.setColumns(10);
        oreoTextField.setBounds(224, 242, 96, 19);
        contentPane.add(oreoTextField);

        vodkaTextField = new JTextField();
        vodkaTextField.setColumns(10);
        vodkaTextField.setBounds(224, 293, 96, 19);
        contentPane.add(vodkaTextField);

        rumTextField = new JTextField();
        rumTextField.setColumns(10);
        rumTextField.setBounds(288, 350, 96, 19);
        contentPane.add(rumTextField);

        milkaTextField = new JTextField();
        milkaTextField.setColumns(10);
        milkaTextField.setBounds(205, 401, 96, 19);
        contentPane.add(milkaTextField);

        beefTextField = new JTextField();
        beefTextField.setColumns(10);
        beefTextField.setBounds(205, 462, 96, 19);
        contentPane.add(beefTextField);

        viewButton = new JButton("View orders");
        viewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        viewButton.setBounds(49, 529, 189, 27);
        contentPane.add(viewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(484, 78, 518, 529);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }
    public void viewOrders(ActionListener action) {
        this.viewButton.addActionListener(action);
    }

    public JTable getTable() {
        return table;
    }
}
