package net.sinou.file.merger.dummy;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConfig {
	private static Logger log = LoggerFactory.getLogger(TestConfig.class);

	public static void main(String[] args) {
		log.info("Starting test...");

		log.info("ClassPath: ");
		Arrays.stream(System.getProperty("java.class.path").split(":")).forEach(System.out::println);
	}
}
