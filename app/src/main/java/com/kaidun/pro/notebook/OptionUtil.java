package com.kaidun.pro.notebook;

import java.util.HashMap;

/**
 * @author Yunr
 * @date 2018/01/30 17:53
 */
public class OptionUtil {

    public static HashMap<String, Integer> hashMap;

    public static int check(String option) {
        if (hashMap == null) {
            initHashMap();
        }

        int count = hashMap.get(option);
        return count;
    }

    private static void initHashMap() {
        hashMap = new HashMap<>();
        hashMap.put("A+", 6);
        hashMap.put("A", 5);
        hashMap.put("A-", 4);
        hashMap.put("B", 3);
        hashMap.put("C", 2);
        hashMap.put("D", 1);
    }

}
