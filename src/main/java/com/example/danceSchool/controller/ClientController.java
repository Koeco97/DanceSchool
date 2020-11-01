package com.example.danceSchool.controller;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{email}/join/{groupId}")
    public void updateClient(@PathVariable("email") String email, @PathVariable("groupId") Long groupId) {
        clientService.joinGroup(email, groupId);
    }
}
