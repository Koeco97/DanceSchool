package com.example.danceSchool.controller;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping(value = "/(id)")
    public ClientDto findClientById(@PathParam("id") Long id, @RequestHeader HttpHeaders httpHeaders){
        return clientService.findClientById(id);
    }
    @GetMapping
    public List <ClientDto> getAll(){
        return clientService.getAll();
    }
    @GetMapping("/map")
    public Map<Long, ClientDto> getMap(){
        return clientService.getAll().stream().collect(Collectors.toMap(ClientDto::getId, Function.identity()));
    }
    @PostMapping
    public ClientDto createClient (ClientDto clientDto){
        return clientService.createClient(clientDto);
    }
    @PutMapping
    public ClientDto updateClient (ClientDto clientDto){
        return clientService.updateClient(clientDto);
    }
    @DeleteMapping
    public void deleteClient (Long id){
        clientService.deleteClient(id);
    }
}
