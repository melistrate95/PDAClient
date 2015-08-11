package deadlion.com.pdaclient.model.complex_type;

public class Author {
    private String name;
    private String memberId;

    /************************************************************/

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    /************************************************************/

    // TODO: Писать такой конструктор, или не писать, вот в чем вопрос.
    public Author(String authorUrl) {

    }

    public Author(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
    }
}


