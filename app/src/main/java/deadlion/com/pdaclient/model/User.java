package deadlion.com.pdaclient.model;

/**
 * Created by Mike on 27.07.2015.
 */
public class User {
    private String userLogin;
    private String userPassword;
    private String captcha;
    private String memberId;
    private String urlAvatar;
    private boolean isAuthorized;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public User(String userLogin, String userPassword, String captcha) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.captcha = captcha;
    }

    public User(String userLogin, String memberId) {
        this.userLogin = userLogin;
        this.memberId = memberId;
    }
}
