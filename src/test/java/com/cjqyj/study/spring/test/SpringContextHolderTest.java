package com.cjqyj.study.spring.test;

import com.cjqyj.study.helloworld.controller.HelloWorldController;
import com.cjqyj.study.sys.spring.SpringContextHolder;
import com.cjqyj.study.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yuanyou.zhu on 2015/7/28.
 */
public class SpringContextHolderTest extends BaseTest {

    @Test
    public void testGetBean() {
        HelloWorldController controller = SpringContextHolder.getBean(HelloWorldController.class);
        Assert.assertNotNull(controller);
    }

}
