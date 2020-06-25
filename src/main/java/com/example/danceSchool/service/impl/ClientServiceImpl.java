package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Client;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.exception.ClientException;
import com.example.danceSchool.repository.ClientRepository;
import com.example.danceSchool.repository.GroupRepository;
import com.example.danceSchool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final GroupRepository groupRepository;
    private final ConversionService conversionService;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, GroupRepository groupRepository, ConversionService conversionService) {
        this.clientRepository = clientRepository;
        this.groupRepository = groupRepository;
        this.conversionService = conversionService;
    }

    @Override
    public ClientDto findClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientException("Client is not found"));
        return conversionService.convert(client, ClientDto.class);
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = conversionService.convert(clientDto, Client.class);
        return conversionService.convert(clientRepository.save(client), ClientDto.class);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientException("Client is not found"));
        client.setFirstName(clientDto.getFirstName());
        client.setSecondName(clientDto.getSecondName());
        client.setLastName(clientDto.getLastName());
        client.setBirthday(clientDto.getBirthday());
        client.setSex(clientDto.getSex());
        client.setEmail(clientDto.getEmail());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setLogin(clientDto.getLogin());
        client.setPassword(clientDto.getPassword());
        GroupDto groupDto = clientDto.getGroup();
        if (Objects.nonNull(groupDto)) {
            Long groupId = groupDto.getId();
            Group group = Objects.isNull(groupId) ?
                    conversionService.convert(groupDto, Group.class) :
                    groupRepository.getOne(groupId);
            groupRepository.save(group);
            client.setGroup(group);
        }
        return conversionService.convert(clientRepository.save(client), ClientDto.class);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientException("Client is not found"));
        clientRepository.delete(client);
    }

    @Override
    public List<ClientDto> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(client -> conversionService.convert(client, ClientDto.class)).collect(Collectors.toList());
    }
}
