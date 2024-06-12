package com.example.demo.client;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public void addNewClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findClientsByEmail(client.getEmail());

        if (clientOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        clientRepository.save(client);

        System.out.println(client);
    }

    public void deleteClient(Long clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new IllegalStateException("Client with Id" + clientId + " does not exists");
        }
        clientRepository.deleteById(clientId);
    }

@Transactional
    public void updateClient(Long clientId, String name, String email) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("Client with id" + clientId + " does not exist"));

    if (name != null && !name.isEmpty() && !Objects.equals(client.getName(), name)){
        client.setName(name);
    }
    if (name != null && !name.isEmpty() && !Objects.equals(client.getEmail(), email)){
        Optional<Client> clientOptional = clientRepository.findClientsByEmail(email);
        if (clientOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        client.setEmail(email);
    }
    }

    public boolean clientExists(Long clientId) {
        boolean exists = clientRepository.findById(clientId).isPresent();
        if (!exists) {
            throw new IllegalStateException("Client with Id" + clientId + " does not exists");
        }
        return exists;
    }
}