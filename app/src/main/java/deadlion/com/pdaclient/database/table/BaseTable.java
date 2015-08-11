package deadlion.com.pdaclient.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import deadlion.com.pdaclient.database.DbHelper;

public abstract class BaseTable<TModel> {

    protected final String LOG_TAG = "Db_table_logs";

    protected DbHelper dbHelper;
    protected final String tableName;

    public BaseTable(DbHelper dbHelper, String tableName) {
        this.dbHelper = dbHelper;
        this.tableName = tableName;
    }

    /**
     * Переопределение данного метода обязано возвращать строку запроса на создание таблицы
     * переопределенной сущности.
     * @return Строка запроса создания таблицы
     */
    protected abstract String getCreateTableQuery();

    /**
     * Переопределение данного метода обязано получать экземпляр модели, извлеченный из курсора.
     * Данный метод получает только <u>один</u> элемент из курсора, прокручивать курсор нужно самостоятельно.
     * @param cursor Курсор, из которого будет извлечен элемент
     * @return Экземпляр элемента, извлеченного из курсора
     */
    protected abstract TModel getItemFromCursor(Cursor cursor);

    /**
     * Переопределение данного метода обязано получать элемент {@link ContentValues} из параметра
     * model.
     * @param model Модель, из которого будет получен {@link ContentValues}
     * @return Элемент {@link ContentValues}, полученный из модели
     */
    protected abstract ContentValues getContentValues(TModel model);

    /**
     * Переопределение данного метода обязано возвращать название колонки, позволяющую идентифицировать
     * различные строки в таблице. Значения такой колонки <u>не должны быть сгенерированы автоматически</u>
     * @return Название колонки с уникальными значениями
     */
    protected abstract String getUniqueColumnName();

    /**
     * Переопределение данного метода обязано возвращать строковое значение колонки, задаваемое методом
     * {@link BaseTable#getUniqueColumnName()}, полученное из параметра model.
     * @param model Сущность, из которой будет извлечено значение уникальной колонки
     * @return Строковое представление значения уникальной колонки
     */
    protected abstract String getUniqueColumnValue(TModel model);



    /***************** Сокращенные методы *****************/

    protected String getStringByColumnName(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    protected int getIntByColumnName(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    /***************** Safe CRUD *****************/

    public void createTable() {
        create();
    }

    public TModel getItemById(int id) {
        return getBy("ID=?", new String[]{String.valueOf(id)}, null, null, null, null).get(0);
    }

    public ArrayList<TModel> getAll() {
        return getBy(null, null, null, null, null, null);
    }

    public long insertItem(TModel model) {
        return insert(model);
    }

    public int updateItem(TModel model) {
        return update(model, getUniqueColumnName() + "=?", new String[]{getUniqueColumnValue(model)});
    }

    public int deleteItem(TModel model) {
        return delete(getUniqueColumnName() + "=?", new String[]{getUniqueColumnValue(model)});
    }

    public int deleteAll() {
        return delete(null, null);
    }


    /***************** CRUD *****************/

    private void create() {
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.execSQL(getCreateTableQuery());
        }
        catch (Exception ex) {
            Log.d(LOG_TAG, ex.getLocalizedMessage());
        }
        finally {
            if (db != null) {
                // db.endTransaction();
                db.close();
            }
        }
    }

    private ArrayList<TModel> getBy(String selection, String[] selectionArgs, String groupBy, String having,
                           String orderBy, String limit) {

        SQLiteDatabase db = null;
        Cursor cursor = null;
        ArrayList<TModel> items = new ArrayList<>();

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(tableName, null, selection, selectionArgs, groupBy, having, orderBy, limit);

            if (cursor.moveToFirst()) {
                do {
                    items.add(getItemFromCursor(cursor));
                } while (cursor.moveToNext());
            }
        }
        catch (Exception ex) {
            items = null;
            Log.d(LOG_TAG, ex.getLocalizedMessage());
        }
        finally {
            if (cursor != null)
                cursor.close();

            if (db != null) {
                // db.endTransaction();
                db.close();
            }
        }

        return items;
    }

    private long insert(TModel model) {
        SQLiteDatabase db = null;
        long result = -1;

        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = getContentValues(model);
            result = db.insert(tableName, null, values);
        }
        catch (Exception ex) {
            Log.d(LOG_TAG, ex.getLocalizedMessage());
        }
        finally {
            if (db != null) {
                // db.endTransaction();
                db.close();
            }
        }

        return result;
    }

    private int update(TModel model, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = null;
        int result = 0;

        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = getContentValues(model);
            result = db.update(tableName, values, whereClause, whereArgs);
        }
        catch (Exception ex) {
            Log.d(LOG_TAG, ex.getLocalizedMessage());
        }
        finally {
            if (db != null) {
                // db.endTransaction();
                db.close();
            }
        }

        return result;
    }

    private int update(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = null;
        int result = 0;

        try {
            db = dbHelper.getWritableDatabase();
            result = db.update(tableName, values, whereClause, whereArgs);
        }
        catch (Exception ex) {
            Log.d(LOG_TAG, ex.getLocalizedMessage());
        }
        finally {
            if (db != null) {
                // db.endTransaction();
                db.close();
            }
        }

        return result;
    }

    private int delete(String whereClause, String[] whereArgs) {
        SQLiteDatabase db = null;
        int result = 0;

        try {
            db = dbHelper.getWritableDatabase();
            result = db.delete(tableName, whereClause, whereArgs);
        }
        catch (Exception ex) {
            Log.d(LOG_TAG, ex.getLocalizedMessage());
        }
        finally {
            if (db != null) {
                // db.endTransaction();
                db.close();
            }
        }

        return result;
    }
}
