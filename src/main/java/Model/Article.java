package Model;

/**
 * Represents an academic article obtained from Google Scholar.
 * <p>
 * This class models the data for an article, including its title, authors,
 * publication date, abstract, keywords, link, and citation count.
 * </p>
 */
public class Article {

    private String title;
    private String authors;
    private String year;
    private String citedBy;
    private String link;

    /**
     * Constructs a new Article object with all required fields.
     *
     * @param title   the article title
     * @param authors a comma-separated list of authors
     * @param year    the publication year of the article
     * @param citedBy the number of times the article has been cited
     * @param link    the URL link to the article
     */
    public Article(String title, String authors, String year, String citedBy, String link) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.citedBy = citedBy;
        this.link = link;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthors() { return authors; }
    public String getYear() { return year; }
    public String getCitedBy() { return citedBy; }
    public String getLink() { return link; }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nAuthors: " + authors +
                "\nYear: " + year +
                "\nCited by: " + citedBy +
                "\nLink: " + link + "\n";
    }


}
