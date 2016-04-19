package com.cjqyj.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyou.zhu on 2015/5/31.
 */
@Component
public class SpringIocAnnotation implements Serializable {

    private String testString;
    private List<IocTestBaseBean> testBeanList = new ArrayList<IocTestBaseBean>();
    private IocTestBean1 iocTestBean1;

    public String getTestString() {
        return testString;
    }

    @Value("${db.driver}")
    public void setTestString(String testString) {
        this.testString = testString;
    }

    public List<IocTestBaseBean> getTestBeanList() {
        return testBeanList;
    }

    @Autowired
    public void setTestBeanList(List<IocTestBaseBean> testBeanList) {
        this.testBeanList = testBeanList;
    }

    public IocTestBean1 getIocTestBean1() {
        return iocTestBean1;
    }

    @Autowired
    public void setIocTestBean1(IocTestBean1 iocTestBean1) {
        this.iocTestBean1 = iocTestBean1;
    }
}
