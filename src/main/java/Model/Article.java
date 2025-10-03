package Model;

public class Article {

    private String title;
    private String authors;
    private String year;
    private String citedBy;
    private String link;

    public Article(String title, String authors, String year, String citedBy, String link) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.citedBy = citedBy;
        this.link = link;
    }

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
