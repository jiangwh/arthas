/*
 * Copyright (c) 2020.
 * jiangwh
 */

package com.taobao.arthas.core.util;

import org.junit.Test;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class ThreadUtilTest {

	@Test
	public void getThreads() {

		ThreadGroup group = Thread.currentThread().getThreadGroup();
		ThreadGroup parent;
		while ((parent = group.getParent()) != null) {
			group = parent;
		}

		ThreadGroup root =group;
		Thread[] threads = new Thread[root.activeCount()];
		while (root.enumerate(threads, true) == threads.length) {
			threads = new Thread[threads.length * 2];
		}
		SortedMap<String, Thread> map = new TreeMap<String, Thread>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		for (Thread thread : threads) {
			if (thread != null) {
				System.out.println((thread.getName() + "-" + thread.getId() +" ------>"+ thread));
			}
		}
	}
}