package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGreenDaoGen {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.clericettideveloper.travlendarapplication.db");
        schema.enableKeepSectionsByDefault();


    }
}
