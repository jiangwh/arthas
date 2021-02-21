/*
 * Copyright (c) 2021.
 * jiangwh
 */
package com.jiangwh.log;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class LogAgent {

	public static void premain(String args, Instrumentation inst) {
		main(args, inst);

	}


	public static void agentmain(String args, Instrumentation inst) {
		main(args, inst);
	}

	private static synchronized void main(String args, final Instrumentation inst) {
		new AgentBuilder.Default()
				.with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
				.type((ElementMatchers.any()))
				.transform(
						(builder, typeDescription, classLoader, module) ->
								builder.method(ElementMatchers.any())
										.intercept(Advice.to(LogInterceptor.class))
				).installOn(inst);
	}


	public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		Object o = new ByteBuddy().subclass(Object.class).name("com.jiangwh.HelloWorld")
				.defineField("name", Integer.TYPE, Modifier.PUBLIC)
				.defineMethod("getName", String.class, Modifier.PUBLIC)
				.withParameter(String.class, "arg0")
				.intercept(FixedValue.argument(0))
				.make()
				.load(Thread.currentThread().getContextClassLoader()).getLoaded()
				.newInstance();
		Method method = o.getClass().getMethod("getName", String.class);
		System.out.println(method.invoke(o, "HelloWorld"));
	}
}
