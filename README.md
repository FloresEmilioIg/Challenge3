# Challenge3
## 📌 **Project Purpose**

The main goal of Challenge3 is to develop a Java application using the MVC (Model-View-Controller) design pattern that retrieves academic data from the Google Scholar Author API via SerpApi, processes the data, and stores it in a structured MySQL database.

This project emphasizes:

- Clean architecture

- Modular development

- Robust API interaction and error handling

It provides a user-friendly GUI for searching academic authors or topics and viewing their publications.

## ⚙️ **Key Functionalities**

- API Consumption: Performs GET requests to the Google Scholar Author API through SerpApi.

- Error Handling: Manages network errors, invalid responses, and API rate limits.

- Data Parsing: Extracts relevant publication details such as title, authors, year, citation count, and link.

- Database Integration: Stores retrieved data in a MySQL database with a consistent schema, preventing duplicates.

- MVC Design Pattern: Separates concerns clearly:

  - Model – represents data (Article class)

  - View – handles GUI interactions (ArticleView)

  - Controller – manages API requests, data processing, and updates (ArticleController)

- GUI Features: Search field, results table, double-click to open article links.

- Unit Testing Ready: Designed to allow testing of API requests, data parsing, and database persistence.

- Documentation: Fully documented with Javadoc for all classes.

## 🌍 **Project Relevance**

This application simplifies the retrieval and organization of academic publication data:

- For researchers: Speeds up collection of publication metrics and author data.

- For institutions: Helps structure and analyze large volumes of publication data.

- For developers: Serves as a reusable example of API integration, Java MVC design, and database interaction.

In short, it enables structured access, storage, and usage of Google Scholar data.

## 🛠 **Technical Overview**
**Classes**

Model:

- Article – represents an academic article, including title, authors, year, citation count, and link.

DAO / Database:

- ArticleD – handles saving articles to MySQL, avoids duplicates, and parses citation counts.

- DatabaseManager – manages database connection and table initialization.

Controller:

- ArticleController – performs API requests, parses JSON responses, updates the database, and passes data to the view.

View:

- ArticleView – GUI for user interaction, displays articles in a table, allows opening links.

Main:

- Initializes GUI and binds search functionality.

## **Database Schema**
Table: articles

| Column             | Type               | Description                   |
| ------------------ | ------------------ | ----------------------------- |
| `id`               | INT AUTO_INCREMENT | Primary key                   |
| `researcher_name`  | VARCHAR(255)       | Name of the author/researcher |
| `title`            | TEXT               | Article title                 |
| `authors`          | TEXT               | Comma-separated authors       |
| `publication_date` | VARCHAR(50)        | Year of publication           |
| `abstract`         | TEXT               | Currently empty               |
| `link`             | TEXT               | URL to the article            |
| `keywords`         | TEXT               | Currently empty               |
| `cited_by`         | INT                | Citation count                |


## **API Documentation**

Technical document Sprint1_CH3.pdf included in the repository provides:

- Endpoints and query parameters

- Authentication and API key usage

- Response formats

- Usage limits

- Code examples for integration

## 🔒 **Repository Access**

The repository is configured for the Digital NAO team to easily access, clone, and contribute.
If access issues arise, please contact the repository maintainer.

## ✅ **Javadoc Documentation**

All project classes are documented using Javadoc:

- Model: Article.java

- Database / DAO: ArticleD.java, DatabaseManager.java

- Controller: ArticleController.java

- View: ArticleView.java

- Entry Point: Main.java

Developers can generate HTML Javadoc from the source to explore class details and methods.
