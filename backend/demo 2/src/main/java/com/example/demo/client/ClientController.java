package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "client")
@CrossOrigin
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController (ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @PostMapping
    public void registerNewClient(@RequestBody Client  client){
        clientService.addNewClient(client);
    }

    @DeleteMapping(path = "{clientId}")
    public void deleteClient(@PathVariable ("clientId") Long clientId){
        clientService.deleteClient(clientId);
    }
    @PutMapping(path = "{clientId}")
    public void updateClient(
        @PathVariable ("clientId") Long clientId,
        @RequestParam(required = false) String name,
                @RequestParam(required = false) String email){
        clientService.updateClient(clientId, name, email);

    }
    @PostMapping("/client/check")
    public ResponseEntity<?> checkClientId(@RequestBody ClientIdRequest request) {
        if (request.getClientId() == null) {
            return ResponseEntity.badRequest().body("Client ID is required.");
        }
        boolean exists = clientService.clientExists(request.getClientId());
        return ResponseEntity.ok().body(new ClientExistsResponse(exists));
    }

    // DTOs for request and response
    public static class ClientIdRequest {
        private Long clientId;

        public Long getClientId() {
            return clientId;
        }

        public void setClientId(Long clientId) {
            this.clientId = clientId;
        }
    }

    public static class ClientExistsResponse {
        private boolean exists;

        public ClientExistsResponse(boolean exists) {
            this.exists = exists;
        }

        public boolean isExists() {
            return exists;
        }

        public void setExists(boolean exists) {
            this.exists = exists;
        }
    }
}

