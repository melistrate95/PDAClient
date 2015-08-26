package deadlion.com.pdaclient.model;

import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.PostCategory;

public class Post {
    private PostCategory category;
    private String url;
    private String photoUrl;
    private String dateOfPublication;
    private int countOfComments;
    private Author author;

    private String title;
    private String description;
    private String text;
    private String comments;

    /************************************************************/

    public PostCategory getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public int getCountOfComments() {
        return countOfComments;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public String getComments() {
        return comments;
    }

    /************************************************************/

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCategory(PostCategory category) {
        this.category = category;
    }

    /************************************************************/

    public Post(String title, String description, PostCategory category, String url, String photoUrl, String dateOfPublication, int countOfComments, Author author) {
        this.category = category;
        this.url = url;
        this.photoUrl = photoUrl;
        this.dateOfPublication = dateOfPublication;
        this.countOfComments = countOfComments;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public Post(String title, String description, String text, String comments, PostCategory category, String url, String photoUrl, String dateOfPublication, int countOfComments, Author author) {
        this.category = category;
        this.url = url;
        this.photoUrl = photoUrl;
        this.dateOfPublication = dateOfPublication;
        this.countOfComments = countOfComments;
        this.author = author;
        this.title = title;
        this.description = description;
        this.text = text;
        this.comments = comments;
    }
}
