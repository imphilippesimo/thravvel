/**
 * 
 */
package com.thravvel.core.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.thravvel.core.dao")
public class SpringDaoConfig {

}
