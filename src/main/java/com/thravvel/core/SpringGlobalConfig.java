/**
 * 
 */
package com.thravvel.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.thravvel.core.dao.SpringDaoConfig;
import com.thravvel.core.data.SpringPersistenceConfig;
import com.thravvel.core.service.SpringServiceConfig;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({ "com.thravvel.core.dao", "com.thravvel.core.service", "com.thravvel.core.test",
		"com.thravvel.core.controller" })
@Import({ SpringPersistenceConfig.class, SpringDaoConfig.class, SpringServiceConfig.class })

public class SpringGlobalConfig {

}
