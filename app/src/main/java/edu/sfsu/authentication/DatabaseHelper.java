package edu.sfsu.authentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
// Inter Fragment Communication - Android Programming for Beginners Book page 427
public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * DATABASE => UPPERCASE, Table => Capitalized
     */
    // using constant fields
    private static final String DB_NAME = "AUTOMOBILE"; // capitalize db name, lowercase table name
    private static final int DB_VERSION = 1; // version options 0 - 3
    private static final int USER_VERSION = 0; // version options 0 - 3

    // we can access the string outside the class as well because it's declared as 'public'.
    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_MAKE = "MAKE";
    public static final String TABLE_ROW_DESCRIPTION = "DESCRIPTION";

    /**
     * Public Constructor
     * SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION); // options
    }

    // create a new motorcycle table for downgrade db
    private static void create_new_table(SQLiteDatabase db) {
        Log.i("log", "create_table(SQLiteDatabase db)");
        db.execSQL("CREATE TABLE Motorcycle(_id INTEGER PRIMARY KEY AUTOINCREMENT, MAKE text, DESCRIPTION text, ID integer);");
        // change to motorcycle models and change the descriptions
        insert_item(db, "Harley Davidson", "Chevrolet is an American automotive company.", R.drawable.camaro);
        insert_item(db, "Kawasaki", "Chrysler is an American Automotive Company", R.drawable.chevelle);
        insert_item(db, "Yamaha", "Ford is an American Automotive Company.", R.drawable.mustang);
    }

    // Default Method
    private static void create_table(SQLiteDatabase db) {
        Log.i("log", "create_table(SQLiteDatabase db)");
        db.execSQL("CREATE TABLE Car(_id INTEGER PRIMARY KEY AUTOINCREMENT, MAKE text, DESCRIPTION text, ID integer);");
        insert_item(db, "Chevrolet", "Chevrolet is an American automotive company.", R.drawable.camaro);
        insert_item(db, "Chrysler", "Chrysler is an American Automotive Company", R.drawable.chevelle);
        insert_item(db, "Ford", "Ford is an American Automotive Company.", R.drawable.mustang);
    }

    // DELETE a record in the car table.
    public static void delete_record(SQLiteDatabase db) {
        Log.i("log", "delete_record(SQLiteDatabase db)");
    }

    // Insert an item into the table
    private static void insert_item(SQLiteDatabase db,
                                    String make,
                                    String description,
                                    int resourceId) {

        ContentValues ItemValues = new ContentValues();

        ItemValues.put("MAKE", make);
        ItemValues.put("DESCRIPTION", description);
        ItemValues.put("image_resource_id", resourceId);
        db.insert("Car", null, ItemValues);
    }

    // This should update the table, if I update the DB_VERSION to 2
    public static void update_record(SQLiteDatabase db) { // UPDATE a record in the car table.
        ContentValues UpdateValues = new ContentValues();
        UpdateValues.put("description", "Ford is a Fortune 500 Company and I own stock");
        db.update("car", UpdateValues, "make = ?", new String[] {"Ford"});
    }

    // Update the DB using a switch statement
    private void update_database(SQLiteDatabase db, int user_version, int new_version) {
        switch (user_version) {
            case 0: create_table(db);
                break;
            case 1: update_record(db);
                break;
            case 2: delete_record(db);
                break;
            case 3: db.execSQL("ALTER TABLE car ADD COLUMN make TEXT");
                break;
            default:
                Log.i("log", "user_version default selection");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // mandatory
        Log.i("log", "Opening Database: " + getDatabaseName());
        update_database(sqLiteDatabase, USER_VERSION, DB_VERSION);
    }

    /**
     * If the sqlite helper spots that the database that's installed is out of date,
     * it will call either the onUpgrade() or onDowngrade() method.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int user_version, int new_version) { // mandatory
        update_database(sqLiteDatabase, user_version, new_version);
    }

    // called when the database needs to be downgraded.
    public void onDowngrade(SQLiteDatabase db, int old_version, int new_version) {
        // if the old version is 1, then we're going to revert the database to version 0
        if (old_version == 1) {
            create_table(db);
        } else if (old_version == 2) {
            create_new_table(db);
        }
    }
}