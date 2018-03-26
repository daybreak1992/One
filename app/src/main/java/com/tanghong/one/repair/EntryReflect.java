package com.tanghong.one.repair;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tanghong on 2018/3/25.
 */

public class EntryReflect {
    public static final String TAG_REFLECT = "reflect";

    public static void dealConstructors() {
        Class clazz = Entry.class;

        for (Constructor con : clazz.getConstructors()) {
            Log.i(TAG_REFLECT, "Constructor = " + con.toString());
        }

        try {
            Object obj = clazz.newInstance();
            if (obj instanceof Entry) {
                ((Entry) obj).setId(1);
                ((Entry) obj).setName("xiaohua");
                ((Entry) obj).setSex(2);
                ((Entry) obj).setAge(18);
                ((Entry) obj).setDesc("my name is xioahua !");
                ((Entry) obj).setAddress("china shanghai");
                Log.i(TAG_REFLECT, "Entry = " + ((Entry) obj).toString());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void dealFields() {
        Class clazz = Entry.class;

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Log.i(TAG_REFLECT, "filed = " + field.toString());
        }

        try {
            Entry entry = (Entry) clazz.newInstance();
            Field nameField = clazz.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(entry, "xiaoqiang");
            Field ageField = clazz.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(entry, 18);
            Field sexField = clazz.getDeclaredField("sex");
            sexField.setAccessible(true);
            sexField.set(entry, 1);
            Field descField = clazz.getDeclaredField("desc");
            descField.setAccessible(true);
            descField.set(entry, "my name is xiaoqiang !");
            Field addressField = clazz.getDeclaredField("address");
            addressField.setAccessible(true);
            addressField.set(entry, "china beijin");

            Log.i(TAG_REFLECT, "entry = " + entry.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
            Log.i(TAG_REFLECT, "InstantiationException = " + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.i(TAG_REFLECT, "IllegalAccessException = " + e.toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Log.i(TAG_REFLECT, "NoSuchFieldException = " + e.toString());
        }
    }

    public static void dealMethods() {
        Class clazz = Entry.class;

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
//            Log.i(TAG_REFLECT, "method = " + method.toString());
        }

        try {
            Entry entry = (Entry) clazz.newInstance();
            Method getClassName = clazz.getDeclaredMethod("getClassName", String.class);
            getClassName.setAccessible(true);
            String str = (String) getClassName.invoke(entry, "获取类名");
            Log.i(TAG_REFLECT, str);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
