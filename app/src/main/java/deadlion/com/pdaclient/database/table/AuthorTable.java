package deadlion.com.pdaclient.database.table;

import android.content.ContentValues;
import android.database.Cursor;

import deadlion.com.pdaclient.database.DbHelper;
import deadlion.com.pdaclient.model.Author;

public class AuthorTable extends BaseTable<Author> {

    public final String COLUMN_NICKNAME = "NICKNAME";
    public final String COLUMN_MEMBER_ID = "MEMBER_ID";
    public final String COLUMN_LAST_POST_ID = "LAST_POST_ID";

    /****************************************************************************/

    public AuthorTable(DbHelper dbHelper) {
        super(dbHelper, "AUTHOR_TABLE");
    }

    public AuthorTable(DbHelper dbHelper, String tableName) {
        super(dbHelper, tableName);
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE " + tableName + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NICKNAME + " TEXT,"
                + COLUMN_MEMBER_ID + " TEXT,"
                + COLUMN_LAST_POST_ID + " INTEGER"
                + ");";
    }

    @Override
    protected Author getItemFromCursor(Cursor cursor) {
        return new Author(
                getStringByColumnName(cursor, COLUMN_NICKNAME),
                getStringByColumnName(cursor, COLUMN_MEMBER_ID),
                getIntByColumnName(cursor, COLUMN_LAST_POST_ID)
        );
    }

    @Override
    protected ContentValues getContentValues(Author author) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NICKNAME, author.getName());
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
