package com.tanghong.one.designpattern.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghong on 2018/3/26.
 */
public class DataStoreRemote implements IDataStore {

    @Override
    public List<String> returnData() {
        List<String> remoteData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            remoteData.add("remote:" + (i + 1));
        }
        return remoteData;
    }
}
