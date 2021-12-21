package application.controller;

import application.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDelete {

    @Autowired
    private PointService pointService;

    @Scheduled(fixedRate = 10000)
    public void deleteDeadRows() {
        pointService.deleteDeadRows();
    }
}