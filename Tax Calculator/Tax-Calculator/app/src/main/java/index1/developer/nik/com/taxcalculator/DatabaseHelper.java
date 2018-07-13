package index1.developer.nik.com.taxcalculator;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.*;




public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME ="GST.db";
    private SQLiteDatabase database;
    private final Context cotxt;

/*
    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }
*/
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);

        this.cotxt = context;
        DB_PATH = cotxt.getDatabasePath(DB_NAME).toString();
    }
/*
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }
*/

// checking if database is already exists
    public void createDatabase () throws IOException {

        boolean dbExist = olddb();

        if(dbExist){

        }else{


            this.getWritableDatabase();
            this.close();

            try {

                cloneA();

            }
            catch (IOException e) {

                throw new Error("Error copying database");
            }

        }

    }
/*
    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
                    Log.i("SQLite Error", "db error.");

    }
*/
    private boolean olddb(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLException e){
            Log.i("SQLite Error", "No database found.");

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }


    private void cloneA() throws IOException{

        InputStream myInput = cotxt.getAssets().open(DB_NAME);

        String outFileName = DB_PATH;

        OutputStream os = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            os.write(buffer, 0, length);
        }

        os.flush();
        os.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException
    {
        String myPath = DB_PATH;
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close()
    {
        if(database != null)
            database.close();

        super.close();

    }
    public  void onDestroy()
    {
        Log.i("SQLite Error", "Destroy method.");
        
    }    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
            try {
                cloneA();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
