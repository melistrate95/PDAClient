package deadlion.com.pdaclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import deadlion.com.pdaclient.database.table.PostTable;

public class DbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private PostTable postTable;

    public DbHelper(Context context) {
        super(context, "PostDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        postTable = new PostTable(this);
        postTable.createTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public PostTable getPostTable() {
        return postTable;
    }
}

