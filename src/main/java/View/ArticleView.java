package View;

import Model.Article;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.List;

public class ArticleView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;

    public ArticleView() {
        setTitle("Google Scholar Search - SerpApi");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Layout
        setLayout(new BorderLayout());

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(30);
        searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Enter author/topic:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Article table
        String[] columns = {"Title", "Authors", "Year", "Cited By", "Link"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // double clic
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String url = (String) tableModel.getValueAt(row, 4);
                        openInBrowser(url);
                    }
                }
            }
        });

        setVisible(true);
    }

    public String getSearchQuery() {
        return searchField.getText();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void displayArticles(List<Article> articles) {
        tableModel.setRowCount(0); // limpiar tabla
        for (Article article : articles) {
            tableModel.addRow(new Object[]{
                    article.getTitle(),
                    article.getAuthors(),
                    article.getYear(),
                    article.getCitedBy(),
                    article.getLink()
            });
        }
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void openInBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Opening browser not supported on this system.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not open link: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
