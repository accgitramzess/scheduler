package scheduler.framework.core;

import org.quartz.Job;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import scheduler.framework.core.annotation.QuartzJob;

import java.util.*;

/**
 * Created by LilG2pac on 05.05.2018.
 */
public class QuartzJobDetector {

    public Map<String, Class<? extends Job>> detect(String packageName) {
        assert packageName == null || packageName.isEmpty();
        return detect2(packageName);
    }

    private Map<String, Class<? extends Job>> detect2(String packageName) {
        ClassPathScanningCandidateComponentProvider scanner = createClassPathScanningCandidateComponentProvider();
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(packageName);
        Map<String, Class<? extends Job>> clasess = new HashMap<>();
        for (BeanDefinition bean : candidateComponents) {
            try {
                Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(bean.getBeanClassName());
                String keyJobClass = jobClass.getField("JOB_KEY").get(String.class).toString();
                clasess.put(keyJobClass, jobClass);
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }
        return clasess;
    }

    private ClassPathScanningCandidateComponentProvider createClassPathScanningCandidateComponentProvider() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(QuartzJob.class));
        return scanner;
    }

    public static void main(String[] args) {
        QuartzJobDetector quartzJobDetector = new QuartzJobDetector();
        Map<String, Class<? extends Job>> classes = quartzJobDetector.detect("scheduler.framework.job");
        classes.forEach((key, value) -> System.out.println(key + ", " + value.getCanonicalName()));
    }
}
