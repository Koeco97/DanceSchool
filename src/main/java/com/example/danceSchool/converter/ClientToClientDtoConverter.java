package com.example.danceSchool.converter;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.entity.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientToClientDtoConverter implements Converter<Client, ClientDto> {

    @Override
    public ClientDto convert(Client client) {
        ClientDto target = new ClientDto();
        target.setId(client.getId());
        return target;
    }
}
