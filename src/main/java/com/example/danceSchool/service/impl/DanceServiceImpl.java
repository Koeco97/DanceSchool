package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.entity.Dance;
import com.example.danceSchool.exception.DanceException;
import com.example.danceSchool.repository.DanceRepository;
import com.example.danceSchool.service.DanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DanceServiceImpl implements DanceService {
    private final DanceRepository danceRepository;
    private final ConversionService conversionService;

    @Autowired
    public DanceServiceImpl(DanceRepository danceRepository, ConversionService conversionService) {
        this.danceRepository = danceRepository;
        this.conversionService = conversionService;
    }

    @Override
    public DanceDto findDanceById(Long id) {
        Dance dance = danceRepository.findById(id).orElseThrow(()->new DanceException("Dance is not found"));
        return conversionService.convert(dance, DanceDto.class);
    }

    @Override
    public DanceDto createDance(DanceDto danceDto) {
        Dance dance = conversionService.convert(danceDto, Dance.class);
        return conversionService.convert(danceRepository.save(dance), DanceDto.class);
    }

    @Override
    public DanceDto updateDance(DanceDto danceDto, Long id) {
        Dance dance = danceRepository.findById(id).orElseThrow(() -> new DanceException("Dance is not found"));
        dance.setName(danceDto.getName());
        return conversionService.convert(danceRepository.save(dance), DanceDto.class);
    }

    @Override
    public void deleteDance(Long id) {
        Dance dance = danceRepository.findById(id).orElseThrow(()->new DanceException("Dance is not found"));
        danceRepository.delete(dance);
    }

    @Override
    public List<DanceDto> getAll() {
        List <Dance> dances = danceRepository.findAll();
        return dances.stream().map(dance->conversionService.convert(dance, DanceDto.class)).collect(Collectors.toList());
    }
}
