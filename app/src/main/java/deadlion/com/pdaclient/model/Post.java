package deadlion.com.pdaclient.model;

import deadlion.com.pdaclient.model.enum_model.PostCategory;

/**
 * Created by Mike on 27.07.2015.
 */
public class Post {
    private PostCategory postCategory;
    private String title;
    private String photoUrl;
    private String dataOfPublication;
    private String countOfComments;
    private String shortPostText;
    private String postUrl;

    private String fullPostText;
    private String postComments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDataOfPublication() {
        return dataOfPublication;
    }

    public void setDataOfPublication(String dataOfPublication) {
        this.dataOfPublication = dataOfPublication;
    }

    public String getCountOfComments() {
        return countOfComments;
    }

    public void setCountOfComments(String countOfComments) {
        this.countOfComments = countOfComments;
    }

    public String getShortPostText() {
        return shortPostText;
    }

    public void setShortPostText(String shortPostText) {
        this.shortPostText = shortPostText;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getFullPostText() {
        return fullPostText;
    }

    public void setFullPostText(String fullPostText) {
        this.fullPostText = fullPostText;
    }

    public String getPostComments() {
        return postComments;
    }

    public void setPostComments(String postComments) {
        this.postComments = postComments;
    }

    public PostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(PostCategory postCategory) {
        this.postCategory = postCategory;
    }

    public Post(String title, String photoUrl, String dataOfPublication,
                String countOfComments, String shortPostText, String postUrl, PostCategory postCategory) {
        this.title = title;
        this.photoUrl = photoUrl;
        this.dataOfPublication = dataOfPublication;
        this.countOfComments = countOfComments;
        this.shortPostText = shortPostText;
        this.postUrl = postUrl;
        this.postCategory = postCategory;
    }

    public Post() {

    }
}
