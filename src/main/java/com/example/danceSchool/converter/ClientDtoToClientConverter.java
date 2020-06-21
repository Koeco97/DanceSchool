package com.example.danceSchool.converter;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.entity.Client;
import com.example.danceSchool.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoToClientConverter implements Converter<ClientDto, Client> {
    private final ConversionService conversionService;

    @Autowired
    public ClientDtoToClientConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Client convert(ClientDto clientDto) {
        Client target = new Client();
        target.setFirstName(clientDto.getFirstName());
        target.setSecondName(clientDto.getSecondName());
        target.setLastName(clientDto.getLastName());
        target.setBirthday(clientDto.getBirthday());
        target.setSex(clientDto.getSex());
        target.setEmail(clientDto.getEmail());
        target.setPhoneNumber(clientDto.getPhoneNumber());
        target.setLogin(clientDto.getLogin());
        target.setPassword(clientDto.getPassword());
        target.setGroup(conversionService.convert(clientDto.getGroup(), Group.class));
        return target;
    }
}
