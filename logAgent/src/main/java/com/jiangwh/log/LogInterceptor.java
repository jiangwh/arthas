/*
 * Copyright (c) 2021.
 * jiangwh
 */

package com.jiangwh.log;

import net.bytebuddy.asm.Advice;

public class LogInterceptor {

	@Advice.OnMethodExit(onThrowable = Throwable.class)
	public static void methodExit(@Advice.Origin String method,
								  @Advice.Enter long startTime,
								  @Advice.Thrown Throwable throwable) {
		System.out.println("#### jiangwh " + method);
		System.out.println("---- " + throwable);
		System.out.println("time cost :" + (startTime - System.currentTimeMillis()) + " ms");
	}

	@Advice.OnMethodEnter
	public static long methodEnter(@Advice.Origin String method, @Advice.AllArguments Object[] args) {
		return System.currentTimeMillis();
	}


}
