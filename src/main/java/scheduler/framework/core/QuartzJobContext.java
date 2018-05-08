package scheduler.framework.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * Created by LilG2pac on 30.04.2018.
 */
@Data
@AllArgsConstructor
public class QuartzJobContext {

    private JobDetail jobDetail;

    private Trigger trigger;
}
