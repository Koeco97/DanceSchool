package com.example.danceSchool.converter;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.entity.Dance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DanceToDanceDtoConverter implements Converter<Dance, DanceDto> {
    private final ConversionService conversionService;

    @Autowired
    public DanceToDanceDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public DanceDto convert(Dance dance) {
        DanceDto target = new DanceDto();
        target.setId(dance.getId());
        target.setName(dance.getName());
        return target;
    }
}
