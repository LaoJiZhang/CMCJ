package com.cmcj.gmj.localapp.database.dao;

import com.cmcj.gmj.localapp.base.database.DBManager;
import com.cmcj.gmj.localapp.database.table.User;

/**
 * Created by guomaojian on 16/11/19.
 */

public class UserDao {


    public void insertUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User(i, "name" + i, 18 + i, "男");
            DBManager.getDaoSession().getUserDao().insert(user);
        }
    }
}
