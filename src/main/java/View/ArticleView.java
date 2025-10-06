package View;

import Model.Article;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.List;

/**
 * GUI class for displaying Google Scholar search results in a JTable.
 * Allows searching articles and opening article links in a browser.
 */
public class ArticleView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;

    /**
     * Constructs the main view window and initializes components.
     */
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
        tableModel = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // cells are non-editable
            }
        };
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Double-click to open link
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

    /**
     * Returns the current search query entered by the user.
     *
     * @return the search query text
     */
    public String getSearchQuery() {
        return searchField.getText();
    }

    /**
     * Returns the search button component for event binding.
     *
     * @return the search JButton
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Updates the table to display the given list of articles.
     *
     * @param articles list of Article objects
     */
    public void displayArticles(List<Article> articles) {
        tableModel.setRowCount(0); // clean table
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

    /**
     * Displays an error message in a dialog.
     *
     * @param message the error message
     */
    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Opens the given URL in the default system browser.
     *
     * @param url the URL to open
     */
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
