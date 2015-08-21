package deadlion.com.pdaclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import deadlion.com.pdaclient.database.table.PostTable;

public class DbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private PostTable postTable;

    public DbHelper(Context context) {
        super(context, "PdaClientDb", null, 1);

        postTable = new PostTable(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        postTable.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public PostTable getPostTable() {
        return postTable;
    }
}

