package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGreenDaoGen {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.clericettideveloper.travlendarapplication.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);
    }


    private static Entity addUserEntities(final Schema schema) {
        Entity user = schema.addEntity("User");

        return user;
    }


    /*private static Entity addCalendarEntities(final Schema schema) {

    }*/
}
