/**
 * Copyright 2015 英睿信息科技有限公司 (http://www.enways.com). All rights reserved.
 * File Name: com.enways.test.modules.base.BaseDaoTest.java.java
 * Encoding UTF-8
 * Version: 1.0
 * Date: 上午12:05:43
 * History:	
 */
package com.cjqyj.study.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据库相关操作测试基类
 * Created by yuanyou.zhu on 2015/5/2.
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class BaseDaoTest extends BaseTest {

	private static final Log LOG = LogFactory.getLog(BaseDaoTest.class);
}