package com.cmcj.gmj.localapp.database.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by guomaojian on 16/11/19.
 */
@Entity
public class User {

    @Id
    private long id;
    private String userName;
    private int age;
    private String sex;

    @Generated(hash = 685799103)
    public User(long id, String userName, int age, String sex) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
