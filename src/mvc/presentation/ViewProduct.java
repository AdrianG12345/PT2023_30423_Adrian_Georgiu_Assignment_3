package mvc.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewProduct extends JFrame {
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField priceTextField;
    private JTextField quantityTextField;
    private JTable table;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton editProductButton;
    private JButton viewProductButton;
    public ViewProduct()
    {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1099, 679);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Products");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titleLabel.setBounds(330, 36, 335, 45);
        contentPane.add(titleLabel);

        addProductButton = new JButton("Add");
        addProductButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        addProductButton.setBounds(10, 141, 154, 58);
        contentPane.add(addProductButton);

        deleteProductButton = new JButton("Delete");
        deleteProductButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        deleteProductButton.setBounds(10, 198, 154, 58);
        contentPane.add(deleteProductButton);

        viewProductButton = new JButton("View");
        viewProductButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        viewProductButton.setBounds(10, 250, 154, 63);
        contentPane.add(viewProductButton);

        editProductButton = new JButton("Edit");
        editProductButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        editProductButton.setBounds(10, 305, 154, 58);
        contentPane.add(editProductButton);

        nameTextField = new JTextField();
        nameTextField.setBounds(285, 134, 189, 24);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel nameLAbel = new JLabel("Name:");
        nameLAbel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        nameLAbel.setBounds(194, 130, 81, 24);
        contentPane.add(nameLAbel);

        idTextField = new JTextField();
        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idTextField.setColumns(10);
        idTextField.setBounds(250, 179, 189, 24);
        contentPane.add(idTextField);

        priceTextField = new JTextField();
        priceTextField.setColumns(10);
        priceTextField.setBounds(250, 245, 189, 24);
        contentPane.add(priceTextField);

        quantityTextField = new JTextField();
        quantityTextField.setColumns(10);
        quantityTextField.setBounds(222, 332, 189, 24);
        contentPane.add(quantityTextField);

        JLabel idLabel = new JLabel("Id:");
        idLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        idLabel.setBounds(194, 164, 81, 24);
        contentPane.add(idLabel);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        priceLabel.setBounds(194, 241, 81, 24);
        contentPane.add(priceLabel);

        JLabel quantityLabel = new JLabel("Quantity available:");
        quantityLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        quantityLabel.setBounds(177, 285, 154, 36);
        contentPane.add(quantityLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(484, 78, 518, 529);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }
    public void addProduct(ActionListener action)
    {
        this.addProductButton.addActionListener(action);
    }
    public void editProduct(ActionListener action)
    {
        this.editProductButton.addActionListener(action);
    }
    public void deleteProduct(ActionListener action)
    {
        this.deleteProductButton.addActionListener(action);
    }
    public void viewProduct(ActionListener action)
    {
        this.viewProductButton.addActionListener(action);
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

    public double getPriceTextField() {
        return Double.parseDouble(priceTextField.getText());
    }

    public int getQuantityTextField() {
        return Integer.parseInt(quantityTextField.getText());
    }
}
