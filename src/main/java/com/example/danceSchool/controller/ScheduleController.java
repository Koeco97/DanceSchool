package com.example.danceSchool.controller;

import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "schedule")
public class ScheduleController {
    private final ClientService clientService;

    @Autowired
    public ScheduleController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/sortByBegin")
    public List<SheduleReport> getLessonsSortedByBegin() {
        return clientService.getLessonsSortedByBegin();
    }

    @GetMapping("/sortByEnd")
    public List<SheduleReport> getLessonsSortedByEnd() {
        return clientService.getLessonsSortedByEnd();
    }

    @GetMapping("/sortByLength")
    public List<SheduleReport> getLessonsSortedByLength() {
        return clientService.getLessonsSortedByLength();
    }

    @GetMapping("/sortByDance")
    public List<SheduleReport> getLessonsSortedByDance() {
        return clientService.getLessonsSortedByType();
    }

    @GetMapping("/sortByTeacher")
    public List<SheduleReport> getLessonsSortedByTeacher() {
        return clientService.getLessonsSortedByTeacher();
    }
}
