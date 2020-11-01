package com.example.danceSchool.service;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.entity.Client;

import java.util.List;

public interface ClientService {
    ClientDto findClientById(Long id);

    ClientDto createClient(ClientDto clientDto);

    ClientDto updateClient(ClientDto clientDto, Long id);

    void deleteClient(Long id);

    public List<ClientDto> getAll();

    public List<SheduleReport> getLessonsSortedByBegin();

    public List<SheduleReport> getLessonsSortedByEnd();

    public List<SheduleReport> getLessonsSortedByLength();

    public List<SheduleReport> getLessonsSortedByType();

    public void joinGroup(String email, Long groupId);

    public Client findByLogin(String login);

    public Client findByLoginAndPassword(String login, String password);

    public List<SheduleReport> getLessonsSortedByTeacher();
}
