package com.pansijing.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by guomaojian on 16/11/19.
 */

public class DBManager {

    public static volatile DBManager Instance;
    private final static String sDBName = "local_db";
    private Context mContext;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static SQLiteDatabase mWriteDatabase;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private DBManager(Context context) {
        this.mContext = context;

        mDevOpenHelper = new LocalDevOpenHelper(mContext, sDBName);
        mWriteDatabase = mDevOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mWriteDatabase);
        mDaoSession = mDaoMaster.newSession();
    }

    public static void createInstance(Context context) {
        if (Instance == null) {
            synchronized (DBManager.class) {
                if (Instance == null)
                    Instance = new DBManager(context);
            }
        }
    }

    public static DBManager getInstance() {
        return Instance;
    }

    private SQLiteDatabase getWriteDatabase() {
        return mWriteDatabase;
    }

    public static SQLiteDatabase getDatabase() {
        return mWriteDatabase;
    }

    public static DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
