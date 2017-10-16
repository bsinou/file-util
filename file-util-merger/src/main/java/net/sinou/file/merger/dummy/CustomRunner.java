package net.sinou.file.merger.dummy;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;

import net.sinou.file.merger.jobs.ImportFileInfoBatchConfiguration;

public class CustomRunner {

	public static void main(String[] args) throws Exception {
		CommandLineJobRunner
				.main(new String[] { ImportFileInfoBatchConfiguration.class.getName(), "readDirectoryContentJob" });
	}
}
