package scheduler.framework.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import scheduler.framework.TestBean;

/**
 * Created by LilG2pac on 06.05.2018.
 */
@Configuration
@EnableConfigurationProperties(QuartzProperties.class)
public class Test {

    @Autowired
    private QuartzProperties quartzProperties;

    @Bean
    public TestBean createTestBean() {
        return new TestBean(quartzProperties);
    }
}
