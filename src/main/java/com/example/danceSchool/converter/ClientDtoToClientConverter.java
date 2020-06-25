package com.example.danceSchool.converter;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Client;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.danceSchool.converter.PersonDtoToPersonConverter.copyProperties;


@Component
public class ClientDtoToClientConverter implements Converter<ClientDto, Client> {
    private final ConversionService conversionService;
    private final GroupRepository groupRepository;

    @Autowired
    public ClientDtoToClientConverter(ConversionService conversionService, GroupRepository groupRepository) {
        this.conversionService = conversionService;
        this.groupRepository = groupRepository;
    }

    @Override
    public Client convert(ClientDto clientDto) {
        Client target = new Client();
        copyProperties(clientDto, target);

        GroupDto groupDto = clientDto.getGroup();
        if (Objects.nonNull(groupDto)) {
            Long groupId = groupDto.getId();
            Group group = Objects.isNull(groupId) ?
                    conversionService.convert(groupDto, Group.class) :
                    groupRepository.getOne(groupId);
            groupRepository.save(group);
            target.setGroup(group);
        }
        return target;
    }
}
