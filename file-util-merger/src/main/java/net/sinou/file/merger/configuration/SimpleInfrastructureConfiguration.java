package net.sinou.file.merger.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@EnableBatchProcessing
@Profile("default")
@PropertySource("classpath:testData.properties")
public class SimpleInfrastructureConfiguration implements InfrastructureConfiguration {

	@Value("${data.batch.clean}")
	private String batchClean;

	@Value("${data.batch.schema}")
	private String batchSchema;

	@Value("${data.business.reset}")
	private String businessReset;

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder.addScript(batchClean).addScript(batchSchema).addScript(businessReset)
				.setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
