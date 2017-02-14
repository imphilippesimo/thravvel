/**
 * 
 */
package com.thravvel.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.thravvel.core.dao.SpringDaoConfig;
import com.thravvel.core.data.SpringPersistenceConfig;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Configuration
@EnableTransactionManagement
// @ComponentScan({ "com.thravvel.core.dao", "com.thravvel.core.service",
// "com.thravvel.core.test" })
@Import({ SpringPersistenceConfig.class,
		SpringDaoConfig.class, /* SpringServiceConfig.class */ })

public class SpringGlobalConfig {

}
