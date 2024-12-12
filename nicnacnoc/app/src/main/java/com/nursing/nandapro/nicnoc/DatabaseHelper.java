package com.nursing.nandapro.nicnoc;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private String DB_PATH;
    private static String DB_NAME;
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    public final static int DATABASE_VERSION = 12;

    public DatabaseHelper(Context context, String namedb) {
        super(context, namedb, null, DATABASE_VERSION);
        DB_NAME = namedb;
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases";
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        //File databasePath = mContext.getDatabasePath(DB_NAME); return databasePath.exists();

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        //File outFile = new File(Environment.getDataDirectory();
        // outFile.setWritable(true);
        // myDataBase = SQLiteDatabase.openDatabase(outFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, columns, selection, selectionArgs, null, null, null);
    }

    public Cursor rawquery(String query) {
        return myDataBase.rawQuery(query, null);
    }

    public void insert(String query) {
        myDataBase.execSQL(query);
    }


}
