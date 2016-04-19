package com.cjqyj.study.spring;

import java.io.Serializable;
import java.util.*;

/**
 * Created by yuanyou.zhu on 2015/5/31.
 */
public class SpringIocXML implements Serializable {

    private List<String> testList = new ArrayList<String>();
    private Set<String> testSet = new HashSet<String>();
    private Map<String, String> testMap = new HashMap<String, String>();
    private List<IocTestBaseBean> testBeanList = new ArrayList<IocTestBaseBean>();

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }

    public Set<String> getTestSet() {
        return testSet;
    }

    public void setTestSet(Set<String> testSet) {
        this.testSet = testSet;
    }

    public Map<String, String> getTestMap() {
        return testMap;
    }

    public void setTestMap(Map<String, String> testMap) {
        this.testMap = testMap;
    }

    public List<IocTestBaseBean> getTestBeanList() {
        return testBeanList;
    }

    public void setTestBeanList(List<IocTestBaseBean> testBeanList) {
        this.testBeanList = testBeanList;
    }
}
