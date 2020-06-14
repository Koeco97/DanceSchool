package com.example.danceSchool.controller;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.service.DanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public DanceDto findDanceById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders){
        return danceService.findDanceById(id);
    }
    @GetMapping
    public List<DanceDto> getAll(){
        return danceService.getAll();
    }
    @GetMapping("/map")
    public Map<Long, DanceDto> getMap(){
        return danceService.getAll().stream().collect(Collectors.toMap(DanceDto::getId, Function.identity()));
    }
    @PostMapping
    public DanceDto createDance(@RequestBody DanceDto danceDto){
        return danceService.createDance(danceDto);
    }
    @PutMapping
    public DanceDto updateDance(DanceDto danceDto){
        return danceService.updateDance(danceDto);
    }
    @DeleteMapping
    public void deleteDance (Long id){
        danceService.deleteDance(id);
    }
}
