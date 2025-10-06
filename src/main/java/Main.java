
import Controller.ArticleController;
import View.ArticleView;

import javax.swing.*;

/**
 * Entry point for the Google Scholar article search application.
 * Initializes the GUI and binds the controller to the search button.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArticleView view = new ArticleView();
            ArticleController controller = new ArticleController(view);

            // Conect the search button
            view.getSearchButton().addActionListener(e -> {
                String query = view.getSearchQuery();
                if (!query.isEmpty()) {
                    controller.searchArticles(query);
                } else {
                    view.displayError("Please enter a search query.");
                }
            });
        });
    }
}
