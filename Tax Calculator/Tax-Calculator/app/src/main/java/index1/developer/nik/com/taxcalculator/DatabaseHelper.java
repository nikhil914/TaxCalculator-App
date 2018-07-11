package index1.developer.nik.com.taxcalculator;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME ="GST.db";
    private SQLiteDatabase mDataBase;
    private final Context mContext;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);

        this.mContext = context;
        DB_PATH = mContext.getDatabasePath(DB_NAME).toString();
    }

    public void createDatabase () throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){

        }else{


            this.getWritableDatabase();
            this.close();

            try {

                copyDataBase();

            }
            catch (IOException e) {

                throw new Error("Error copying database");

            }

        }

    }



    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLException e){
            Log.i("SQLite Error", "database does't exist yet.");

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }


    private void copyDataBase() throws IOException{

        InputStream myInput = mContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException
    {
        String myPath = DB_PATH;
        mDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close()
    {
        if(mDataBase != null)
            mDataBase.close();

        super.close();

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
