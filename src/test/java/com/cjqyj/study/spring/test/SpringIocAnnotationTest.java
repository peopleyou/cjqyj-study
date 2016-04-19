package com.cjqyj.study.spring.test;

import com.cjqyj.study.spring.*;
import com.cjqyj.study.sys.spring.SpringContextHolder;
import com.cjqyj.study.test.BaseTest;
import com.cjqyj.study.utils.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyou.zhu on 2015/6/1.
 */
public class SpringIocAnnotationTest extends BaseTest {

    @Autowired
    private SpringIocAnnotation springIocAnnotation;

    @Test
    public void testPropertyIoc() {
        Assert.assertEquals("com.mysql.jdbc.Driver", springIocAnnotation.getTestString());
    }

    @Test
    public void testBeanIoc() {
        Assert.assertNotNull(springIocAnnotation.getIocTestBean1());
    }

    @Test
    public void testBeanListIoc() {

        List<IocTestBaseBean> expectedTestBeanList = new ArrayList<>();

        expectedTestBeanList.add(SpringContextHolder.getBean(IocTestBean1.class));
        expectedTestBeanList.add(SpringContextHolder.getBean(IocTestBean2.class));
        expectedTestBeanList.add(SpringContextHolder.getBean(IocTestBean3.class));

        Assert.assertTrue(CollectionUtils.isCollectionEquals(springIocAnnotation.getTestBeanList(), expectedTestBeanList));

    }

}
