package deadlion.com.pdaclient.database;

/**
 * Created by Mike on 27.07.2015.
 */
public class DBData {
    public static final String CATEGORY = "CATEGORY";
    public static final String POST_TITLE = "POST_TITLE";
    public static final String PHOTO_URL = "PHOTO_URL";
    public static final String DATA_OF_PUBLICATION = "DATA_OF_PUBLICATION";
    public static final String COUNT_OF_COMMENTS = "COUNT_OF_COMMENTS";
    public static final String SHORT_POST_TEXT = "SHORT_POST_TEXT";
    public static final String POST_URL = "POST_URL";

    public static final String FULL_POST_TEXT = "FULL_POST_TEXT";
    public static final String POST_COMMENTS = "POST_URL";

    public static final String SHORT_POST_TABLE = "SHORT_POST_TABLE";
    public static final String FULL_POST_TABLE = "FULL_POST_TABLE";

    public static String getShortTableQuery() {
        return "create table " + SHORT_POST_TABLE + " ("
                + "id integer primary key autoincrement,"
                + CATEGORY + " text,"
                + POST_TITLE + " text,"
                + PHOTO_URL + " text,"
                + DATA_OF_PUBLICATION + " text,"
                + COUNT_OF_COMMENTS + " text,"
                + SHORT_POST_TEXT + " text,"
                + POST_URL + " text"
                + ");";
    }

    public static String getFullTableQuery() {
        return "create table " + FULL_POST_TABLE + " ("
                + "id integer primary key autoincrement,"
                + CATEGORY + " text,"
                + POST_TITLE + " text,"
                + PHOTO_URL + " text,"
                + DATA_OF_PUBLICATION + " text,"
                + COUNT_OF_COMMENTS + " text,"
                + SHORT_POST_TEXT + " text,"
                + POST_URL + " text"
                + FULL_POST_TEXT + " text,"
                + POST_COMMENTS + " text"
                + ");";
    }


}
