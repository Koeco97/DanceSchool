package com.example.danceSchool.converter;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.example.danceSchool.converter.PersonToPersonDtoConverter.copyProperties;


@Component
public class ClientToClientDtoConverter implements Converter<Client, ClientDto> {
    private final ConversionService conversionService;

    @Autowired
    public ClientToClientDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ClientDto convert(Client client) {
        ClientDto target = new ClientDto();
        copyProperties(client, target);
        target.setGroup(conversionService.convert(client.getGroup(), GroupDto.class));
        return target;
    }
}
