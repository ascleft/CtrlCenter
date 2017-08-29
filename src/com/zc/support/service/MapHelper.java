package com.zc.support.service;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MapHelper {
	/**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
	
	static class MapKeyComparator implements Comparator<String>{

	    @Override
	    public int compare(String str1, String str2) {

	        return str1.compareTo(str2);
	    }
	}
}
