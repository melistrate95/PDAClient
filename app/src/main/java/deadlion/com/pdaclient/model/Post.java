package deadlion.com.pdaclient.model;

import java.util.Date;

import deadlion.com.pdaclient.model.enum_model.PostCategory;

/**
 * Created by Mike on 27.07.2015.
 */
public class Post {
    private PostCategory postCategory;
    private String title;
    private String photoUrl;
    private User author;
    private String dataOfPublication;
    private int countOfComments;
    private String shortPostText;
    private String postUrl;

    private String fullPostText;
    private String postComments;

    public PostCategory getPostCategory() {
        return postCategory;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public User getAuthor() {
        return author;
    }

    public String getDataOfPublication() {
        return dataOfPublication;
    }

    public int getCountOfComments() {
        return countOfComments;
    }

    public String getShortPostText() {
        return shortPostText;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public String getFullPostText() {
        return fullPostText;
    }

    public String getPostComments() {
        return postComments;
    }

    public void setFullPostText(String fullPostText) {
        this.fullPostText = fullPostText;
    }

    public void setPostComments(String postComments) {
        this.postComments = postComments;
    }

    public Post(PostCategory postCategory, String title, String photoUrl, User author, String dataOfPublication, int countOfComments, String shortPostText, String postUrl) {
        this.postCategory = postCategory;
        this.title = title;
        this.photoUrl = photoUrl;
        this.author = author;
        this.dataOfPublication = dataOfPublication;
        this.countOfComments = countOfComments;
        this.shortPostText = shortPostText;
        this.postUrl = postUrl;
    }

    public Post() {

    }
}
