package net.sinou.file.merger.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class BasicListener implements JobExecutionListener {

	private static final Logger LOG = LoggerFactory.getLogger(BasicListener.class);

	public void afterJob(JobExecution jobExecution) {
		LOG.info("Job done");
	}

	public void beforeJob(JobExecution arg0) {
		// nothing to do
	}
}
