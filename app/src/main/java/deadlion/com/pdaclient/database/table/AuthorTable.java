package deadlion.com.pdaclient.database.table;

import android.content.ContentValues;
import android.database.Cursor;

import deadlion.com.pdaclient.database.DBHelper;
import deadlion.com.pdaclient.model.Author;

public class AuthorTable extends BaseTable<Author> {

    public static final String TABLE_NAME = "AUTHOR_TABLE";

    public static final String COLUMN_AUTHOR_NAME = "COLUMN_AUTHOR_NAME";
    public static final String COLUMN_MEMBER_ID = "MEMBER_ID";
    public static final String COLUMN_LAST_POST_ID = "LAST_POST_ID";

    /****************************************************************************/

    public AuthorTable(DBHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE_NAME + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_AUTHOR_NAME + " TEXT,"
                + COLUMN_MEMBER_ID + " TEXT,"
                + COLUMN_LAST_POST_ID + " INTEGER"
                + ");";
    }

    @Override
    protected Author getItemFromCursor(Cursor cursor) {
        return new Author(
                getStringByColumnName(cursor, COLUMN_AUTHOR_NAME),
                getStringByColumnName(cursor, COLUMN_MEMBER_ID),
                getIntByColumnName(cursor, COLUMN_LAST_POST_ID)
        );
    }

    @Override
    protected ContentValues getContentValues(Author author) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_AUTHOR_NAME, author.getName());
        values.put(COLUMN_MEMBER_ID, author.getMemberId());
        values.put(COLUMN_LAST_POST_ID, author.getLastLoadedPostId());

        return values;
    }

    @Override
    protected String getUniqueColumnName() {
        return COLUMN_MEMBER_ID;
    }

    @Override
    protected String getUniqueColumnValue(Author author) {
        return author.getMemberId();
    }
}
