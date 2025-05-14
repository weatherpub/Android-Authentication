package edu.sfsu.authentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "automobile";
    private static final int DB_VERSION = 1; // upgrade or downgrade db version

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static void alter_table(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE car ADD COLUMN make TEXT");
    }

    private static void create_table(SQLiteDatabase db) {
        Log.i("log", "create_table(SQLiteDatabase db)");
        db.execSQL("CREATE TABLE car(_id integer PRIMARY KEY AUTOINCREMENT, "
                + "make text, "
                + "description text, "
                + "image_resource_id integer);");
        insert_item(db, "Chevrolet", "Chevrolet is an American automotive company.", R.drawable.Chevelle);
        insert_item(db, "Chrysler", "Chrysler is an American Automotive Company", R.drawable.Mustang);
        insert_item(db, "Ford", "Ford is an American Automotive Company.", R.drawable.Camary);
    }

    // DELETE a record in the car table.
    public static void delete_record(SQLiteDatabase db) {

    }

    // This should update the table, if I update the DB_VERSION to 2
    public static void update_record(SQLiteDatabase db) { // UPDATE a record in the car table.
        ContentValues UpdateValues = new ContentValues();
        UpdateValues.put("description", "Ford is a Fortune 500 Company and I own stock");
        db.update("car", UpdateValues, "make = ?", new String[] {"Ford"});
    }

    private static void insert_item(SQLiteDatabase db,
                                    String make,
                                    String description,
                                    int resourceId) {

        ContentValues ItemValues = new ContentValues();

        ItemValues.put("make", make);
        ItemValues.put("description", description);
        ItemValues.put("image_resource_id", resourceId);
        db.insert("car", null, ItemValues);
    }

    private void update_database(SQLiteDatabase db, int user_version, int new_version) {
        /*
        if(user_version == 0) { // if version of db is 0
            Log.i("log", "Database Connection is");
            db.execSQL("CREATE TABLE car(_id integer PRIMARY KEY AUTOINCREMENT, "
                    + "make text, "
                    + "description text, "
                    + "image_resource_id integer);");
            insert_item(db, "Chevrolet", "Chevrolet is an American automotive company.", R.drawable.Chevelle);
            insert_item(db, "Chrysler", "Chrysler is an American Automotive Company", R.drawable.Mustang);
            insert_item(db, "Ford", "Ford is an American Automotive Company.", R.drawable.Camary);
        } else if(user_version == 1) {
            Log.i("log", "user_version = 1, update_records()");
            update_record(db); // update a record in the table
        } else if(user_version == 2) {
            delete_record(db);
        } else if(user_version == 3) {
            db.execSQL("ALTER TABLE car ADD COLUMN make TEXT");
        }
        */

        switch (user_version) {
            case 0: create_table(db);
                break;
            case 1: update_record(db);
                break;
            case 2: delete_record(db);
                break;
            case 3: alter_table(db);
                break;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // mandatory
        update_database(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // mandatory
        update_database(sqLiteDatabase, oldVersion, newVersion);
    }
}