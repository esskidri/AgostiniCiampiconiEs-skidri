package com.clericettideveloper.travlendarapplication.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by Lorenzo on 11/6/2017.
 */

@Entity
public class User {
    @Id
    private int id;
    @Property
    private String eMail;
    @ToOne(joinProperty = "")
    private Calendar calendar;

    public User(int id, String eMail, Calendar calendar) {
        this.id = id;
        this.eMail = eMail;
        this.calendar = calendar;
    }
}
