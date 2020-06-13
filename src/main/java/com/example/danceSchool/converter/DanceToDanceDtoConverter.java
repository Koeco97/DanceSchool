package com.example.danceSchool.converter;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.entity.Dance;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DanceToDanceDtoConverter implements Converter<Dance, DanceDto> {

    @Override
    public DanceDto convert(Dance dance) {
        DanceDto target = new DanceDto();
        target.setId(dance.getId());
        target.setName(dance.getName());
        return target;
    }
}
