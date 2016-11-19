package com.cmcj.gmj.localapp.base.database;

import android.content.Context;

import com.cmcj.gmj.localapp.database.table.DaoMaster;
import com.cmcj.gmj.localapp.utils.LogUtils;

import org.greenrobot.greendao.database.Database;

/**
 * Created by guomaojian on 16/11/19.
 */

public class LocalDevOpenHelper extends DaoMaster.DevOpenHelper {

    public LocalDevOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        LogUtils.i("greenDAO", "oldVersion " + oldVersion + "  newVersion " + newVersion);
    }
}
