package deadlion.com.pdaclient.model;

import deadlion.com.pdaclient.model.enum_model.PostCategory;

public class Post {
    private PostCategory postCategory;
    private String postUrl;

    private String title;
    private String photoUrl;
    private String dateOfPublication;
    private int countOfComments;
    private String shortPostText;
    private int authorId;

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

    public int getAuthorId() {
        return authorId;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
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

    public Post(PostCategory postCategory, String title, String photoUrl, int authorId, String dateOfPublication, int countOfComments, String shortPostText, String postUrl, String fullPostText, String comments) {
        this.postCategory = postCategory;
        this.title = title;
        this.photoUrl = photoUrl;
        this.authorId = authorId;
        this.dateOfPublication = dateOfPublication;
        this.countOfComments = countOfComments;
        this.shortPostText = shortPostText;
        this.postUrl = postUrl;
        this.fullPostText = fullPostText;
        this.postComments = comments;
    }

    public Post() {

    }
}
