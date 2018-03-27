package com.tanghong.one.designpattern.factory;

/**
 * Created by tanghong on 2018/3/26.
 */
public class DataStoreFactory {

    public static IDataStore returnData(boolean isRemote) {
        IDataStore dataStore = null;
        if (isRemote) {
            dataStore = new DataStoreRemote();
        } else {
            dataStore = new DataStoreLocal();
        }
        return dataStore;
    }
}
