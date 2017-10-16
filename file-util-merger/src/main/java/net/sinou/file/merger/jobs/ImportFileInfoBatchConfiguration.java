package net.sinou.file.merger.jobs;

import java.io.File;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.sinou.file.merger.configuration.InfrastructureConfiguration;
import net.sinou.file.merger.configuration.TestInfrastructureConfiguration;
import net.sinou.file.merger.domain.FileInfo;
import net.sinou.file.merger.item.DirReader;
import net.sinou.file.merger.item.FileItemProcessor;
import net.sinou.file.merger.listener.BasicListener;

@Configuration
@Import({ TestInfrastructureConfiguration.class })
public class ImportFileInfoBatchConfiguration {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String basePath = System.getProperty("user.home") + "/tmp";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public InfrastructureConfiguration infrastructureConfiguration;

	@Bean
	public ItemReader<File> reader() {
		return new DirReader(Paths.get(basePath));
	}

	@Bean
	public FileItemProcessor processor() {
		return new FileItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<FileInfo> writer() {
		JdbcBatchItemWriter<FileInfo> writer = new JdbcBatchItemWriter<FileInfo>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<FileInfo>());
		writer.setSql("INSERT INTO FILE_INFO (file_name, file_path) VALUES (:fileName, :filePath)");
		writer.setDataSource(infrastructureConfiguration.dataSource());
		return writer;
	}

	@Bean
	public Job readDirectoryContentJob(JobExecutionListener listener) {
		return jobBuilderFactory.get("readDirectoryContentJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<File, FileInfo>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	@Bean
	public BasicListener basicListener() {
		return new BasicListener();
	}
}
