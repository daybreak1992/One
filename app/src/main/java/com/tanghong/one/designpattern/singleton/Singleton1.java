package com.tanghong.one.designpattern.singleton;

/**
 * Created by tanghong on 2018/3/26.
 * <p>
 * 双重检查加锁的单例模式
 */
public class Singleton1 {

    //对保存实例的变量添加volitile的修饰
    private static volatile Singleton1 instance = null;

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
