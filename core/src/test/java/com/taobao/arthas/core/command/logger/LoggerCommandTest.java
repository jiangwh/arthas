/*
 * Copyright (c) 2020.
 * jiangwh
 */

package com.taobao.arthas.core.command.logger;


import com.taobao.arthas.core.util.ClassLoaderUtils;
import org.junit.Test;

import java.lang.reflect.Method;

public class LoggerCommandTest {

	@Test
	public void test() throws ClassNotFoundException, NoSuchMethodException {
		String classLoaderHash = ClassLoaderUtils.classLoaderHash(ClassLoader.getSystemClassLoader());
		String className = Log4jHelper.class.getName();
		// if want to debug, change to return className
		String className2 =  className + ClassLoaderUtils
				.classLoaderHash(LoggerCommand.class.getClassLoader()) + classLoaderHash;
		System.out.println(">>>>>"+ ClassLoaderUtils
				.classLoaderHash(LoggerCommand.class.getClassLoader()) );
		System.out.println(">>>>" + classLoaderHash);
		System.out.println(className2);
		Class klazz = ClassLoader.getSystemClassLoader().loadClass(className2);
		System.out.println(klazz);
		Method updateLevelMethod = klazz.getMethod("updateLevel", new Class<?>[] { String.class, String.class });
	}

}
