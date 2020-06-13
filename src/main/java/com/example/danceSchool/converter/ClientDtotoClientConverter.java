package com.example.danceSchool.converter;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.entity.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientDtotoClientConverter implements Converter<ClientDto, Client> {

    @Override
    public Client convert(ClientDto clientDto) {
        Client target = new Client();
        target.setPersonId(clientDto.getPersonId());
        target.setGroupId(clientDto.getGroupId());
        return target;
    }
}
