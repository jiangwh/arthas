/*
 * Copyright (c) 2020.
 * jiangwh
 */

package com.taobao.arthas.core.view;

public class Test {
	public static void main(String[] args) {
		System.out.println(1>>1);
	}

	public static int hammingWeight(int n) {
		int count = 0;
		while (n > 0) {
			if (1 == (n & 0x1)) {
				count++;
			}
			n = n >> 1;

		}
		return count;
	}
}
