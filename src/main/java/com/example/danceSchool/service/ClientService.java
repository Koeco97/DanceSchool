package com.example.danceSchool.service;

import com.example.danceSchool.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto findClientById(Long id);

    ClientDto createClient(ClientDto clientDto);

    ClientDto updateClient(ClientDto clientDto, Long id);

    void deleteClient(Long id);

    public List<ClientDto> getAll();
}
