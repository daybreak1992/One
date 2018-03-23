package com.tanghong.one.repair;

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DBColumnManager {

    public static void createColumn(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            DBColumn dbColumn = clazz.getAnnotation(DBColumn.class);
            dbColumn.name();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
