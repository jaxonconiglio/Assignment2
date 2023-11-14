import javax.swing.*;
import java.awt.*;;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class RandProductSearch extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;

    public RandProductSearch() {

        searchField = new JTextField(20);
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProducts();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter partial product name"));
        panel.add(searchField);
        panel.add(searchButton);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setTitle("RandProductSearch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setVisible(true);
    }

    private void searchProducts() {
        resultArea.setText("");

        try (BufferedReader reader = new BufferedReader(new FileReader("product_record.txt"))) {
            String line;
            String partialName = searchField.getText().trim().toLowerCase();

            while ((line = reader.readLine()) != null) {
                Product product = Product.parseRecord(line);

                if (product.getName().toLowerCase().contains(partialName)) {
                    resultArea.append(product.getFormattedRecord() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RandProductSearch();
            }
        });
    }
}
