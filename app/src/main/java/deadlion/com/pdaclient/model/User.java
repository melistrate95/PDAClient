package deadlion.com.pdaclient.model;

public class User {
    private String login;
    private String password;
    private String memberId;
    private String avatarUrl;

    /************************************************************/

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isAuthorized() {
        return login != null;
    }

    /************************************************************/

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    /************************************************************/

    public User(String memberId, String login, String password, String avatarUrl) {
        this.login = login;
        this.password = password;
        this.memberId = memberId;
        this.avatarUrl = avatarUrl;
    }

    public User(String memberId, String login) {
        this.memberId = memberId;
        this.login = login;
    }
}
