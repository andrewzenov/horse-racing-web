package by.training.java.grodno.az.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TestUtil {

	public String getRandomString(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	public double getRandomDouble() {
		Random r = new Random();
		double d = -10.0 + r.nextDouble() * 20.0;
		return d;
	}

	public int getRandomInteger() {
		Random r = new Random();
		int d = -10 + r.nextInt() * 20;
		return d;
	}

}
