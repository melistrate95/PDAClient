package deadlion.com.pdaclient.database.table;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import deadlion.com.pdaclient.database.DbHelper;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.PostCategory;

public class PostTable extends BaseTable<Post> {

    private final String COLUMN_CATEGORY = "CATEGORY";
    private final String COLUMN_PHOTO_URL = "PHOTO_URL";

    private final String COLUMN_AUTHOR_NICKNAME = "AUTHOR_NICKNAME";
    private final String COLUMN_AUTHOR_MEMBER_ID = "AUTHOR_MEMBER_ID";

    private final String COLUMN_DATE_OF_PUBLICATION = "DATE_OF_PUBLICATION";
    private final String COLUMN_COUNT_OF_COMMENTS = "COUNT_OF_COMMENTS";
    private final String COLUMN_URL = "URL";

    private final String COLUMN_TITLE = "TITLE";
    private final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private final String COLUMN_TEXT = "TEXT";
    private final String COLUMN_COMMENTS = "COMMENTS";

    /****************************************************************************/

    public ArrayList<Post> get(PostCategory category) {
        return getBy(COLUMN_CATEGORY + "=?", new String[]{category.toString()}, null, null, null, null);
    }

    public int delete(PostCategory category) {
        return deleteBy(COLUMN_CATEGORY + "=?", new String[]{category.toString()});
    }

    /****************************************************************************/

    public PostTable(DbHelper dbHelper) {
        super(dbHelper, "POST_TABLE");
    }

    public PostTable(DbHelper dbHelper, String tableName) {
        super(dbHelper, tableName);
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE " + tableName + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATEGORY + " TEXT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_PHOTO_URL + " TEXT, "
                + COLUMN_AUTHOR_NICKNAME + " TEXT, "
                + COLUMN_AUTHOR_MEMBER_ID + " TEXT, "
                + COLUMN_DATE_OF_PUBLICATION + " TEXT, "
                + COLUMN_COUNT_OF_COMMENTS + " INTEGER, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_URL + " TEXT, "

                + COLUMN_TEXT + " TEXT, "
                + COLUMN_COMMENTS + " TEXT"
                + ");";
    }

    @Override
    protected Post getItemFromCursor(Cursor cursor) {
        return new Post(
                getStringByColumnName(cursor, COLUMN_TITLE),
                getStringByColumnName(cursor, COLUMN_DESCRIPTION),
                getStringByColumnName(cursor, COLUMN_TEXT),
                getStringByColumnName(cursor, COLUMN_COMMENTS),
                PostCategory.valueOf(getStringByColumnName(cursor, COLUMN_CATEGORY)),
                getStringByColumnName(cursor, COLUMN_URL),
                getStringByColumnName(cursor, COLUMN_PHOTO_URL),
                getStringByColumnName(cursor, COLUMN_DATE_OF_PUBLICATION),
                getIntByColumnName(cursor, COLUMN_COUNT_OF_COMMENTS),
                new Author(
                        getStringByColumnName(cursor, COLUMN_AUTHOR_NICKNAME),
                        getStringByColumnName(cursor, COLUMN_AUTHOR_MEMBER_ID)
                )
        );
    }

    @Override
    protected ContentValues getContentValues(Post post) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, post.getCategory().toString());
        values.put(COLUMN_TITLE, post.getTitle());
        values.put(COLUMN_PHOTO_URL, post.getPhotoUrl());
        values.put(COLUMN_AUTHOR_NICKNAME, post.getAuthor().getName());
        values.put(COLUMN_AUTHOR_MEMBER_ID, post.getAuthor().getMemberId());
        values.put(COLUMN_DATE_OF_PUBLICATION, post.getDateOfPublication());
        values.put(COLUMN_COUNT_OF_COMMENTS, post.getCountOfComments());
        values.put(COLUMN_DESCRIPTION, post.getDescription());
        values.put(COLUMN_URL, post.getUrl());
        values.put(COLUMN_TEXT, post.getText());
        values.put(COLUMN_COMMENTS, post.getComments());
        return values;
    }

    @Override
    protected String getUniqueColumnName() {
        return COLUMN_URL;
    }

    @Override
    protected String getUniqueColumnValue(Post post) {
        return post.getUrl();
    }
}
