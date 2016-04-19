/**
 * Copyright 2015 英睿信息科技有限公司 (http://www.enways.com). All rights reserved.
 * File Name: com.enways.test.modules.base.BaseDaoTest.java.java
 * Encoding UTF-8
 * Version: 1.0
 * Date: 上午12:05:43
 * History:	
 */
package com.cjqyj.study.test;

import com.cjqyj.study.sys.spring.SpringContextHolder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 与数据库操作无关的测试基类
 * Created by yuanyou.zhu on 2015/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class BaseTest {

	private static final Log LOG = LogFactory.getLog(BaseTest.class);
	
	private static final String encoding = "UTF-8";

    @Autowired
    private SpringContextHolder springContextHolder;

	/**
	 * 将 classpath 下面的文件资源转换为 InputStream
	 */
	protected InputStream loadFileStream(String classpath) throws IOException {

        PathMatchingResourcePatternResolver loader = new PathMatchingResourcePatternResolver();

        try {
            Resource[] resources = loader.getResources("classpath:**/" + classpath);
            InputStream is = resources[0].getInputStream();

            return is;
        } catch (Exception e) {
            LOG.debug(e);
        }

        return null;
	}
	
	protected Reader loadFileReader(String classpath) {

		BufferedReader reader = null;
		try {
		    InputStream is = loadFileStream(classpath);
			reader = new BufferedReader(new InputStreamReader(is, encoding));
		} catch (Exception e) {
			LOG.debug("读取文件时出错", e);
		}
		
		return reader;
	}
	
	/**
	 * 将 classpath 下面的文件资源转换为 string
	 */
	protected String loadFileContent(String classpath) {

		try {
		    InputStream is = loadFileStream(classpath);
			return IOUtils.toString(is, Charset.forName(encoding));
		} catch (Exception e) {
			LOG.debug(e);
		}
		
		return "";
	}
}