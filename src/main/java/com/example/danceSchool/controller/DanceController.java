package com.example.danceSchool.controller;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.service.DanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/dances")
public class DanceController {
    private final DanceService danceService;

    @Autowired
    public DanceController(DanceService danceService) {
        this.danceService = danceService;
    }

    @GetMapping(value = "/{id}")
    public DanceDto findDanceById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return danceService.findDanceById(id);
    }

    @GetMapping
    public List<DanceDto> getAll() {
        return danceService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, DanceDto> getMap() {
        return danceService.getAll().stream().collect(Collectors.toMap(DanceDto::getId, Function.identity()));
    }

    @PostMapping
    public DanceDto createDance(@RequestBody DanceDto danceDto) {
        return danceService.createDance(danceDto);
    }

    @PutMapping(value = "/{id}")
    public DanceDto updateDance(@PathVariable("id") Long id, @RequestBody DanceDto danceDto) {
        return danceService.updateDance(danceDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDance(@PathVariable("id") Long id) {
        danceService.deleteDance(id);
    }
}
