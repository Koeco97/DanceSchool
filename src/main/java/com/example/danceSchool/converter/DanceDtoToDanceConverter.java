package com.example.danceSchool.converter;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.entity.Dance;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DanceDtoToDanceConverter implements Converter<DanceDto, Dance> {

    @Override
    public Dance convert(DanceDto danceDto) {
        Dance target = new Dance();
        target.setName(danceDto.getName());
        return target;
    }
}
