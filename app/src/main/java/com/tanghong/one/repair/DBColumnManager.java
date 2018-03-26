package com.tanghong.one.repair;

import java.lang.reflect.Constructor;

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
            Constructor<?> c = clazz.getConstructor();
            Constructor<?>[] cs = clazz.getConstructors();
            Object instance = clazz.newInstance();
            if(instance instanceof DBColumn) {
                String name = ((DBColumn) instance).name();
            }
            DBColumn dbColumn = clazz.getAnnotation(DBColumn.class);
            dbColumn.name();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
