package net.webapp.tmp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("dev")
public class StandaloneDatasourceConfig {

	/*
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:com/bank/config/sql/schema.sql")
				.addScript("classpath:com/bank/config/sql/test-data.sql").build();
	}
	*/
}
