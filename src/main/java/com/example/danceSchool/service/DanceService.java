package com.example.danceSchool.service;

import com.example.danceSchool.dto.DanceDto;

import java.util.List;

public interface DanceService {
    DanceDto findDanceById(Long id);
    DanceDto createDance(DanceDto danceDto);
    DanceDto updateDance(DanceDto danceDto);
    void deleteDance (Long id);
    public List<DanceDto> getAll();
}
