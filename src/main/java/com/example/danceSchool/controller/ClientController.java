package com.example.danceSchool.controller;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/{id}")
    public ClientDto findClientById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return clientService.findClientById(id);
    }

    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, ClientDto> getMap() {
        return clientService.getAll().stream().collect(Collectors.toMap(ClientDto::getId, Function.identity()));
    }

    @PostMapping
    public ClientDto createClient(@RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @PutMapping(value = "/{id}")
    public ClientDto updateClient(@PathVariable("id") Long id, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(clientDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/shedule/sortByBegin")
    public List<SheduleReport> getLessonsSortedByBegin() {
        return clientService.getLessonsSortedByBegin();
    }

    @GetMapping("/shedule/sortByEnd")
    public List<SheduleReport> getLessonsSortedByEnd() {
        return clientService.getLessonsSortedByEnd();
    }

    @GetMapping("/shedule/sortByLength")
    public List<SheduleReport> getLessonsSortedByLength() {
        return clientService.getLessonsSortedByLength();
    }

    @GetMapping("/shedule/sortByDance")
    public List<SheduleReport> getLessonsSortedByDance() {
        return clientService.getLessonsSortedByType();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{clientId}/join/{groupId}")
    public void updateClient(@PathVariable("clientId") Long clientId, @PathVariable("groupId") Long groupId) {
        clientService.joinGroup(clientId, groupId);
    }


}
