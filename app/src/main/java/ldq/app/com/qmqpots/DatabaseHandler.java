package ldq.app.com.qmqpots;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Mayank on 30-03-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "PotsManager";
    public static final String TB_NAME1 = "Pot1";
    public static final String TB_NAME2 = "Pot2";
    public static final String TB_NAME3 = "Pot3";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_POTS = "pots";

    private static final String CREATE_TB1 = "Create table if not exists " + TB_NAME1 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT NOT NULL," + KEY_POTS + " TEXT" + ")";

    private static final String CREATE_TB2 = "Create table if not exists " + TB_NAME2 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT NOT NULL," + KEY_POTS + " TEXT" + ")";

    private static final String CREATE_TB3 = "Create table if not exists " + TB_NAME3 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT NOT NULL," + KEY_POTS + " TEXT" + ")";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TB1);
        db.execSQL(CREATE_TB2);
        db.execSQL(CREATE_TB3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TB_NAME1);

        onCreate(db);
    }

    public void addDetails(Details details, Context context){

        SQLiteDatabase sqldb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME,details.getName());
        values.put(KEY_POTS,details.getPots());

        if(details.getPots().toString().equals("Pot 1")) {
            sqldb.insert(TB_NAME1, null, values);
            sqldb.close();
        }

        else if (details.getPots().toString().equals("Pot 2")){
            sqldb.insert(TB_NAME2,null,values);
            sqldb.close();
        }

        else if (details.getPots().toString().equals("Pot 3")){
            sqldb.insert(TB_NAME3, null, values);
            sqldb.close();
        }
    }

    public Details getDetails(String tb_name,int id){

        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor cursor = sqldb.query(tb_name,new String[]{KEY_ID,KEY_NAME,KEY_POTS},KEY_ID + "=?", new String[] {String.valueOf(id)}
        ,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Details details = new Details(cursor.getString(1),cursor.getString(2));

        return details;
    }

    public int updateDetails(String tb_name, Details details){

        SQLiteDatabase sqldb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME,details.getName());
        values.put(KEY_POTS,details.getPots());

        return sqldb.update(tb_name,values, KEY_ID + "=?", new String[] {String.valueOf(details.getID())});
    }

    public void deleteDetails(String tb_name, Details details){

        SQLiteDatabase sqldb = this.getWritableDatabase();

        sqldb.delete(tb_name,KEY_ID + "=?",new String[] {String.valueOf(details.getID())});

        sqldb.close();
    }
}
