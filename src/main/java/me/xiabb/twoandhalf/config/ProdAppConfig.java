package me.xiabb.twoandhalf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jie on 16-7-12.
 */
@Profile("prod")
@Configuration
@PropertySource("prod.application.properties")
public class ProdAppConfig extends AppConfig {
}
