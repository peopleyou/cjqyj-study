package com.cjqyj.study.utils;

import java.util.Collection;

/**
 * Created by yuanyou.zhu on 2015/5/31.
 */
public class CollectionUtils {

    /**
     * 判断两个集合是否包含相同的元素
     * @param collection1
     * @param collection2
     * @return
     */
    public static <T> boolean isCollectionEquals(Collection<T> collection1, Collection<T> collection2){
        if(null != collection1 && null != collection2){
            return collection1.containsAll(collection2) && collection2.containsAll(collection1);
        }

        return true;
    }
}
