package deadlion.com.pdaclient.database.table;

import android.content.ContentValues;
import android.database.Cursor;

import deadlion.com.pdaclient.database.DBHelper;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.enum_model.PostCategory;

public class PostTable extends BaseTable<Post> {

    public static final String TABLE_NAME = "SHORT_POST_TABLE";

    public final String COLUMN_CATEGORY = "CATEGORY";
    public final String COLUMN_POST_TITLE = "POST_TITLE";
    public final String COLUMN_PHOTO_URL = "PHOTO_URL";
    public final String COLUMN_AUTHOR_ID = "AUTHOR_ID";
    public final String COLUMN_DATE_OF_PUBLICATION = "DATE_OF_PUBLICATION";
    public final String COLUMN_COUNT_OF_COMMENTS = "COUNT_OF_COMMENTS";
    public final String COLUMN_SHORT_POST_TEXT = "SHORT_POST_TEXT";
    public final String COLUMN_POST_URL = "POST_URL";

    public final String SHORT_POST_ID = "SHORT_POST_ID";
    public final String COLUMN_FULL_POST_TEXT = "FULL_POST_TEXT";
    public final String COLUMN_POST_COMMENTS = "POST_COMMENTS";

    /****************************************************************************/

    public PostTable(DBHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE_NAME + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT"
                + COLUMN_CATEGORY + "TEXT, "
                + COLUMN_POST_TITLE + "TEXT, "
                + COLUMN_PHOTO_URL + "TEXT, "
                + COLUMN_AUTHOR_ID + "INTEGER, "
                + COLUMN_DATE_OF_PUBLICATION + "TEXT, "
                + COLUMN_COUNT_OF_COMMENTS + "INTEGER, "
                + COLUMN_SHORT_POST_TEXT + "TEXT, "
                + COLUMN_POST_URL + "TEXT, "

                + COLUMN_FULL_POST_TEXT + "TEXT, "
                + COLUMN_POST_COMMENTS + "TEXT"
                + ");";
    }

    @Override
    protected Post getItemFromCursor(Cursor cursor) {
        return new Post(
                PostCategory.valueOf(getStringByColumnName(cursor, COLUMN_CATEGORY)),
                getStringByColumnName(cursor, COLUMN_POST_TITLE),
                getStringByColumnName(cursor, COLUMN_PHOTO_URL),
                getIntByColumnName(cursor, COLUMN_AUTHOR_ID),
                getStringByColumnName(cursor, COLUMN_DATE_OF_PUBLICATION),
                getIntByColumnName(cursor, COLUMN_COUNT_OF_COMMENTS),
                getStringByColumnName(cursor, COLUMN_SHORT_POST_TEXT),
                getStringByColumnName(cursor, COLUMN_POST_URL),
                getStringByColumnName(cursor, COLUMN_FULL_POST_TEXT),
                getStringByColumnName(cursor, COLUMN_POST_COMMENTS)
        );
    }

    @Override
    protected ContentValues getContentValues(Post post) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, post.getPostCategory().toString());
        values.put(COLUMN_POST_TITLE, post.getTitle());
        values.put(COLUMN_PHOTO_URL, post.getPhotoUrl());
        values.put(COLUMN_AUTHOR_ID, post.getAuthorId());
        values.put(COLUMN_DATE_OF_PUBLICATION, post.getDateOfPublication());
        values.put(COLUMN_COUNT_OF_COMMENTS, post.getCountOfComments());
        values.put(COLUMN_SHORT_POST_TEXT, post.getShortPostText());
        values.put(COLUMN_POST_URL, post.getPostUrl());
        values.put(COLUMN_FULL_POST_TEXT, post.getFullPostText());
        values.put(COLUMN_POST_COMMENTS, post.getPostComments());
        return values;
    }

    @Override
    protected String getUniqueColumnName() {
        return COLUMN_POST_URL;
    }

    @Override
    protected String getUniqueColumnValue(Post post) {
        return post.getPostUrl();
    }
}
