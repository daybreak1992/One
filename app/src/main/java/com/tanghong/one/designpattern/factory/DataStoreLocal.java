package com.tanghong.one.designpattern.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghong on 2018/3/26.
 */
public class DataStoreLocal implements IDataStore {

    @Override
    public List<String> returnData() {
        List<String> localData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            localData.add("local:" + (i + 1));
        }
        return localData;
    }
}
