package edu.sfsu.authentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Inter Fragment Communication - Android Programming for Beginners Book page 427
public class DatabaseHelper extends SQLiteOpenHelper {
    // DATABASE => UPPERCASE, Table => Capitalized

    // using constant fields
    /**
     * The app will start the database version at 1.
     * Meaning the first time a user downloads the app,
     * they will be using version 1 of the database.
     * If we need to modify the database for any reason,
     * we can just increment NEW_VERSION by 1, this will
     * update the database to the next version, in this case
     * version 2.
     * When updating an "EXISTING DATABASE", NEW_VERSION and EXISTING_VERSION
     * will both need to increment by one. They should toggle.
     */
    private static final String DB_NAME = "AUTOMOTIVE";
    private static final int NEW_VERSION = 1; // version options 2 - 3
    private static final int EXISTING_VERSION = 0; // version options 1 - 2

    /**
     * Public Constructor
     * SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, NEW_VERSION); // options
        Log.i("log", "DatabaseHelper 1");
    }

    /**
     * onCreate() method gets called when the database first gets created on the device.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) { // mandatory
        Log.i("log", "DatabaseHelper 2");
        update_database(db, EXISTING_VERSION, NEW_VERSION);
    }

    /**
     * insert_record - insert an record into the table.
     * @param db
     * @param make
     * @param model
     * @param color
     * @param price
     * @param description
     */
    private static void insert_record(SQLiteDatabase db,
                                      int id,
                                      String color,
                                      String make,
                                      String model,
                                      String price,
                                      String description,
                                      int resource) {

        ContentValues value = new ContentValues();

        value.put("ID", id);
        value.put("COLOR", color);
        value.put("MAKE", make);
        value.put("MODEL", model);
        value.put("PRICE", price);
        value.put("DESCRIPTION", description);
        value.put("RESOURCE", resource);

        db.insert("Car", null, value);

        Log.i("log", "[ insert_record(...) - value object has been inserted correctly. ]");
    }

    /**
     * Create a new motorcycle table for downgrade db
     * @param db
     */

    /**
     * create_car_table - create a new Car table.
     * @param db
     */
    private static void update_table(SQLiteDatabase db) {
        Log.i("log", "create_automobile_table(SQLiteDatabase db)");
        db.execSQL("CREATE TABLE Car("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "COLOR text,"
                + "MAKE text,"
                + "MODEL text,"
                + "PRICE text,"
                + "DESCRIPTION text,"
                + "RESOURCE integer);");
        insert_record(db, 0, "Green", "Chevrolet", "Camaro", "$17,999", "Camaro is made in Detroit.", R.drawable.camaro);
        insert_record(db, 1, "Red", "Ford", "Pinto", "$3,999", "Ford is an American auto company.", R.drawable.mustang);
        insert_record(db, 2, "Blue", "Chevrolet", "Chevelle", "$42,999", "Chevelle is an icon.", R.drawable.chevelle);
    }

    /**
     * Create a default table: ID, COLOR, MAKE, MODEL, RESOURCE, DESCRIPTION.
     * @param db
     */
    private static void create_table(SQLiteDatabase db) {
        Log.i("log", "Create Table");
        db.execSQL("CREATE TABLE Car("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "COLOR TEXT,"
                + "MAKE TEXT,"
                + "MODEL TEXT,"
                + "PRICE TEXT,"
                + "DESCRIPTION TEXT,"
                + "RESOURCE INTEGER);");
        insert_record(db,0, "Green", "Chevrolet", "Monte Carlo", "5,999", "The Monte-Carlo is a classic.", R.drawable.camaro);
        insert_record(db,1, "Red", "Ford", "Mustang", "15,999", "Ford is an American auto company.", R.drawable.mustang);
        insert_record(db,2, "Blue", "Chevrolet", "Chevelle", "22,999", "Chevelle is an icon and pure muscle.", R.drawable.chevelle);
    }

    // DELETE a record in the car table.
    public static void delete_record(SQLiteDatabase db) {
        Log.i("log", "delete_record(SQLiteDatabase db)");
    }

    // This should update the table, if I update the NEW_VERSION to 2
    public static void update_record(SQLiteDatabase db) { // UPDATE a record in the car table.
        ContentValues update_value = new ContentValues();
        update_value.put("description", "Ford is a Fortune 500 Company and I own stock in it.");
        db.update("Car", update_value, "MAKE = ?", new String[] {"Tesla"});
    }

    /**
     * Update the DB using a switch statement
     * @param db
     * @param existing_version
     * @param new_version
     */
    private void update_database(SQLiteDatabase db, int existing_version, int new_version) {
        // Create a default table (version 0).
        if(existing_version < new_version) {
            create_table(db);
            Log.i("log", "update_database - Create Table");
        }

        // This code will run if the user already has version 1 of the database.
        if (existing_version == new_version){
            Log.i("log", "Upgrade Table");
            update_table(db);
        }
        /*
        switch (user_version) {
            case 1: create_car_table(db);
                break;
            case 2: update_record(db);
                break;
            case 3: delete_record(db);
                break;
            case 4: db.execSQL("ALTER TABLE car ADD COLUMN make TEXT");
                break;
            default:
                Log.i("log", "user_version default selection");
        }
         */
    }

    /**
     * If the sqlite helper spots that the database that's installed is out of date,
     * it will call either the onUpgrade() or onDowngrade() method.
     * (onUpgrade method gets called when the database needs to be upgraded).
     * @param sqLiteDatabase
     * @param users_existing_version
     * @param new_version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int users_existing_version, int new_version) { // mandatory
        update_database(sqLiteDatabase, users_existing_version, new_version);
    }

    /**
     * Called when the Database needs to be downgraded.
     * @param db
     * @param old_version
     * @param new_version
     */
    public void onDowngrade(SQLiteDatabase db, int old_version, int new_version) {
        // if the old version is 1, then we're going to revert the database to version 0
        if (old_version < 1) {
            // run the default code, with no changes
            create_table(db);
        } else if (old_version == 2) {
            // create_motorcycle_table(db);
        }
    }

    /*
    @Override
    public void onCreate(SQLiteDatabase db) { // mandatory
        Log.i("log", "Opening Database: " + getDatabaseName());
        update_database(db, EXISTING_VERSION, NEW_VERSION);
    }
     */
}