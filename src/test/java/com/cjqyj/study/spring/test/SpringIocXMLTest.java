package com.cjqyj.study.spring.test;

import com.cjqyj.study.spring.*;
import com.cjqyj.study.sys.spring.SpringContextHolder;
import com.cjqyj.study.test.BaseTest;
import com.cjqyj.study.utils.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by yuanyou.zhu on 2015/5/31.
 */
public class SpringIocXMLTest extends BaseTest {

    @Autowired
    private SpringIocXML springIoc;

    @Test
    public void testListIoc() {

        List<String> expectedTestList = new ArrayList<String>();

        expectedTestList.add("list_first");
        expectedTestList.add("list_second");
        expectedTestList.add("list_third");

        Assert.assertTrue(CollectionUtils.isCollectionEquals(springIoc.getTestList(), expectedTestList));
    }

    @Test
    public void testSetIoc() {

        Set<String> expectedTestSet = new HashSet<String>();

        expectedTestSet.add("set_first");
        expectedTestSet.add("set_second");
        expectedTestSet.add("set_third");

        Assert.assertTrue(CollectionUtils.isCollectionEquals(springIoc.getTestSet(), expectedTestSet));
    }

    @Test
    public void testMapIoc() {

        Map<String, String> expectedTestMap = new HashMap<String, String>();

        expectedTestMap.put("key1", "value1");
        expectedTestMap.put("key2", "value2");

        Assert.assertTrue(springIoc.getTestMap().equals(expectedTestMap));
    }

    @Test
    public void testBeanListIoc() {

        List<IocTestBaseBean> expectedTestBeanList = new ArrayList<>();

        expectedTestBeanList.add(SpringContextHolder.getBean(IocTestBean1.class));
        expectedTestBeanList.add(SpringContextHolder.getBean(IocTestBean2.class));
        expectedTestBeanList.add(SpringContextHolder.getBean(IocTestBean3.class));

        Assert.assertTrue(CollectionUtils.isCollectionEquals(springIoc.getTestBeanList(), expectedTestBeanList));
    }
}
