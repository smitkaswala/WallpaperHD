package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase2 extends SQLiteOpenHelper {

    private Context context;
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "FavoriteItem";
    private static String TABLE_NAME = "favorite_Item";
    public static String KEY_ID = "id";
    public static String FULL_IMAGE = "itemImage";
    public static String THUMB_IMAGE = "fStatus";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT PRIMARY KEY," + FULL_IMAGE + " TEXT,"
            + THUMB_IMAGE +" TEXT)";
//+ COL_ID + " TEXT PRIMARY KEY," + COL_FAV_STATUS

    public SqliteDatabase2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (int x = 1; x < 11; x++){
            cv.put(KEY_ID, x);
            cv.put(THUMB_IMAGE, "0");
            db.insert(TABLE_NAME,null,cv);

        }

    }

    //insert data to database
    public void insertInfoTheDatabase(String id, String full, String thumb){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, id);
        cv.put(FULL_IMAGE, full);
        cv.put(THUMB_IMAGE, thumb);

        long i = db.insert(TABLE_NAME,null, cv);

    }

    public String getStatus(String imageId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + KEY_ID + "=" + imageId, null, null);
        if (cursor != null){

            cursor.moveToFirst();

            return "1";
        }else {
            return "0";
        }
    }

    public  boolean checkIsDataAlreadyInDBorNot(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String Query = "Select * from " + TABLE_NAME + " where " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean checkIfUserExit(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID +" LIKE '%"+id+"%'";
        Cursor c = db.query(true, TABLE_NAME, null,
                where, null, null, null, null, null);
        if(c.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean deletedata(String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        String q = "Select *  from "+TABLE_NAME +" where"+ KEY_ID+" = ?";
        Cursor cursor = DB.rawQuery("Select *  from   favorite_Image where id = ?", new String[] {id});
        if (cursor.getCount() > 0)
        {
            long result = DB.delete(TABLE_NAME, "name=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public boolean deletedataa(String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select *  from favorite_Image where id = ?", new String[] {id});
        if (cursor.getCount() > 0)
        {
            long result = DB.delete("favorite_Image", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    //read all data
    public Cursor read_all_data(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + "where" + KEY_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }

    //remove line from database
    public void remove_fav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ THUMB_IMAGE +" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
    }

    //select all favorite list

    public Cursor select_all_favorite_list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql =  "SELECT * FROM "+TABLE_NAME+" WHERE "+ THUMB_IMAGE +" ='1'";
        return db.rawQuery(sql,null,null);

    }
//    Cursor cursor = DB.rawQuery("Select *  from Userdetails", null);

    public List<FavoriteItem> favoriteItemList() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<FavoriteItem> favModelList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                FavoriteItem favoriteImage = new FavoriteItem();
                favoriteImage.setKey_id(cursor.getString(0));
                favoriteImage.setSmall_image(cursor.getString(1));
                favoriteImage.setFull_image(cursor.getString(2));
                favModelList.add(favoriteImage);
            } while (cursor.moveToNext());
        }else {
            favModelList = null;
        }

        return favModelList;

    }

}
