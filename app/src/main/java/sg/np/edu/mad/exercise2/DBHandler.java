package sg.np.edu.mad.exercise2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_USERS = "Users";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_ID = "UId";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_FOLLOWED = "Followed";
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory facrory, int version){
        super(context, DATABASE_NAME, facrory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME+ " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_FOLLOWED + " BOOLEAN)";
        db.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    //Implementing addUser()
    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("UId",user.Id );
        values.put("Username",user.name);
        values.put("Description",user.description);
        values.put("Followed",user.followed);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Users", null, values);
        db.close();
    }

    //Implementing updateUser
    public boolean UpdateUser(User user)
    {
        String username=user.name;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FOLLOWED",String.valueOf(user.followed));
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE NAME=?", new String[]{username});
        if(cursor.getCount()>0){
            db.update("Users",values,"NAME=?",new String[]{username});
        }
        return user.followed;
    }

    // Implementing getUser
    public ArrayList<User> getUsers(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " +TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.getCount()== 0 ){
            return userList;}
        while (c.moveToNext()){
            User newUser = new User();
            newUser.setUId(c.getInt(0));
            newUser.setUsername(c.getString(1));
            newUser.setDescription(c.getString(2));
            int boolVal = c.getInt(3);
            if (boolVal == 1){
                newUser.setFollowed(true);
                userList.add(newUser);
            }
            else if (boolVal == 0) {
                newUser.setFollowed(false);
                userList.add(newUser);
            }
        }
        return userList;
    }
}
