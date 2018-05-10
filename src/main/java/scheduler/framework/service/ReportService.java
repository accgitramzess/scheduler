package scheduler.framework.service;

import org.springframework.stereotype.Component;

@Component
public class ReportService {

    public ReportService() {
        System.out.println("Create ReportService...");
    }

    public void save() {
        System.out.println("Save reports to DB...");
    }
}
