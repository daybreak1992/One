package com.tanghong.one.repair;

/**
 * Created by tanghong on 2018/3/25.
 */

public class Entry {
    private int id;
    private String name;
    private int age;
    private int sex;
    private String desc;
    private String address;

    public Entry() {
    }

    public Entry(int id, String name, int age, int sex, String desc, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.desc = desc;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", desc='" + desc + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    private String getClassName(String tag) {
        return tag + " = " + Entry.class.getSimpleName();
    }
}
