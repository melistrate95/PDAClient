package deadlion.com.pdaclient.model;

public class Author {
    private String name;
    private String memberId;
    private int lastLoadedPostId;

    // TODO: Необходимо написать конструктор
    public Author(String authorUrl, Post lastLoadedPost) {

    }

    public Author(String name, String memberId, int lastLoadedPostId) {
        this.name = name;
        this.memberId = memberId;
        this.lastLoadedPostId = lastLoadedPostId;
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getLastLoadedPostId() {
        return lastLoadedPostId;
    }
}


