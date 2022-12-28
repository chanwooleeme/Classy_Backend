package com.classylab.youtubeapi.conifg;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.classylab")
public class WebConfig {
}
