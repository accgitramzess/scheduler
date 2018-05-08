package scheduler.framework.configuration;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by LilG2pac on 30.04.2018.
 */
@Data
@ConfigurationProperties(prefix = "quartz")
public class QuartzProperties {

    private SchedulerProperties scheduler;

    private QuartzThreadPoolProperties threadPool;

    private Map<String, JobProperties> jobsProperties;

    @Data
    public class SchedulerProperties {

        private String instanceName = "myapp_quartz";

        private String instanceId = "AUTO";

        private boolean skipUpdateCheck = true;
    }

    @Data
    public class QuartzThreadPoolProperties {

        private String className = "org.quartz.simpl.SimpleThreadPool";

        private String threadCount = "5";

        private int threadPriority = 5;
    }
}
