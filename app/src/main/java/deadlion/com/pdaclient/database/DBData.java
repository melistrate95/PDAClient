package deadlion.com.pdaclient.database;

/**
 * Created by Mike on 27.07.2015.
 */
public class DBData {
    public static final String CATEGORY = "CATEGORY";
    public static final String POST_TITLE = "POST_TITLE";
    public static final String PHOTO_URL = "PHOTO_URL";
    public static final String AUTHOR_ID = "ID_AUTHOR";
    public static final String DATE_OF_PUBLICATION = "DATA_OF_PUBLICATION";
    public static final String COUNT_OF_COMMENTS = "COUNT_OF_COMMENTS";
    public static final String SHORT_POST_TEXT = "SHORT_POST_TEXT";
    public static final String POST_URL = "POST_URL";

    public static final String FULL_POST_TEXT = "FULL_POST_TEXT";
    public static final String POST_COMMENTS = "POST_COMMENTS";

    public static final String AUTHOR_NAME = "AUTHOR_NAME";

    public static final String FULL_POST_TABLE = "FULL_POST_TABLE";
    public static final String SHORT_POST_TABLE = "SHORT_POST_TABLE";
    public static final String AUTHORS_TABLE = "AUTHORS_TABLE";



    public static String getShortTableQuery() {
        return "CREATE TABLE " + SHORT_POST_TABLE + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CATEGORY + " TEXT,"
                + POST_TITLE + " TEXT,"
                + PHOTO_URL + " TEXT,"
                + AUTHOR_ID + " TEXT,"
                + DATE_OF_PUBLICATION + " TEXT,"
                + COUNT_OF_COMMENTS + " INTEGER,"
                + SHORT_POST_TEXT + " TEXT,"
                + POST_URL + " TEXT"
                + ");";
    }

    public static String getFullTableQuery() {
        return "CREATE TABLE " + FULL_POST_TABLE + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + POST_URL + " TEXT,"
                + FULL_POST_TEXT + " TEXT,"
                + POST_COMMENTS + " TEXT"
                + ");";
    }

    public static String getAuthorsTableQuery() {
        return "CREATE TABLE " + AUTHORS_TABLE + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUTHOR_NAME + " TEXT,"
                + AUTHOR_ID + " TEXT"
                + ");";
    }

}
