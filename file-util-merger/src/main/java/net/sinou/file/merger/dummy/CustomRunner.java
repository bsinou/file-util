package net.sinou.file.merger.dummy;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;

import net.sinou.file.merger.jobs.ImportFileInfoBatchConfiguration;

/**
 * Wraps the basic batch command line runner to ease launching from within the
 * Eclipse IDE at dev time
 */
public class CustomRunner {

	public static void main(String[] args) throws Exception {
		CommandLineJobRunner
				.main(new String[] { ImportFileInfoBatchConfiguration.class.getName(), "readDirectoryContentJob" });
	}
}
