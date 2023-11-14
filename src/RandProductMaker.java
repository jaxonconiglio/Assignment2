import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;


public class RandProductMaker extends JFrame {
    private JTextField nameField, descriptionField, idField, costField, countField;
    private RandomAccessFile randomAccessFile;
    private int recordCount;

    public RandProductMaker() {
        nameField = new JTextField(20);
        descriptionField = new JTextField(40);
        idField = new JTextField(10);
        costField = new JTextField(10);
        countField = new JTextField(5);
        countField.setEditable(false);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description;"));
        panel.add(descriptionField);
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Cost:"));
        panel.add(costField);
        panel.add(new JLabel("Record Count:"));
        panel.add(countField);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(addButton, BorderLayout.SOUTH);

        setTitle("RandProductMaker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setVisible(true);

        try {
            randomAccessFile = new RandomAccessFile("product.data", "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addRecord() {

        Product product = new Product();
        product.setName(nameField.getText());
        product.setDescription(descriptionField.getText());
        product.setId(idField.getText());
        product.setCost(Double.parseDouble(costField.getText()));

        try {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeUTF(product.getFormattedRecord());
            recordCount++;
            countField.setText(Integer.toString(recordCount));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true))){
                writer.write(product.getFormattedRecord() + System.lineSeparator());
            }
            nameField.setText("");
            descriptionField.setText("");
            idField.setText("");
            costField.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new RandProductMaker();
            }
        });
    }
}
