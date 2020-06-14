package com.example.danceSchool.converter;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.entity.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoToClientConverter implements Converter<ClientDto, Client> {

    @Override
    public Client convert(ClientDto clientDto) {
        Client target = new Client();
        target.setGroupId(clientDto.getGroup().getId());
        return target;
    }
}
