package mvc.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewClient extends JFrame{
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField ageTextField;
    private JTextField addressTextField;
    private JTable table;
    private JButton editClientButton;
    private JButton viewClientButton;
    private JButton deleteClientButton;
    private JButton addClientButton;
    public ViewClient()
    {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1099, 679);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Client");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titleLabel.setBounds(323, 40, 335, 45);
        contentPane.add(titleLabel);

        addClientButton = new JButton("Add");
        addClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        addClientButton.setBounds(10, 141, 154, 58);
        contentPane.add(addClientButton);


        deleteClientButton = new JButton("Delete");
        deleteClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        deleteClientButton.setBounds(10, 198, 154, 58);
        contentPane.add(deleteClientButton);

        viewClientButton = new JButton("View");
        viewClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        viewClientButton.setBounds(10, 250, 154, 63);
        contentPane.add(viewClientButton);

        editClientButton = new JButton("Edit");
        editClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        editClientButton.setBounds(10, 305, 154, 58);
        contentPane.add(editClientButton);

        nameTextField = new JTextField();
        nameTextField.setBounds(285, 130, 189, 24);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel nameLAbel = new JLabel("Name:");
        nameLAbel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        nameLAbel.setBounds(194, 130, 81, 24);
        contentPane.add(nameLAbel);

        idTextField = new JTextField();
        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idTextField.setColumns(10);
        idTextField.setBounds(285, 175, 189, 24);
        contentPane.add(idTextField);

        ageTextField = new JTextField();
        ageTextField.setColumns(10);
        ageTextField.setBounds(285, 241, 189, 24);
        contentPane.add(ageTextField);

        addressTextField = new JTextField();
        addressTextField.setColumns(10);
        addressTextField.setBounds(285, 305, 189, 24);
        contentPane.add(addressTextField);

        JLabel idLabel = new JLabel("Id:");
        idLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        idLabel.setBounds(194, 164, 81, 24);
        contentPane.add(idLabel);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        ageLabel.setBounds(194, 241, 81, 24);
        contentPane.add(ageLabel);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        addressLabel.setBounds(194, 302, 81, 24);
        contentPane.add(addressLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(484, 78, 518, 529);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }


    public void addLisneter(ActionListener action)
    {
        this.addClientButton.addActionListener(action);
    }
    public void showListener(ActionListener action)
    {
        this.viewClientButton.addActionListener(action);
    }
    public void editListener(ActionListener action)
    {
        this.editClientButton.addActionListener(action);
    }
    public void deleteListener(ActionListener action)
    {
        this.deleteClientButton.addActionListener(action);
    }

    public JTable getTable() {
        return table;
    }

    public String getNameTextField() {
        return nameTextField.getText();
    }

    public int getIdTextField() {
        return Integer.parseInt(idTextField.getText());
    }

    public int getAgeTextField() {
        return Integer.parseInt(ageTextField.getText());
    }

    public String getAddressTextField() {
        return addressTextField.getText();
    }
}
