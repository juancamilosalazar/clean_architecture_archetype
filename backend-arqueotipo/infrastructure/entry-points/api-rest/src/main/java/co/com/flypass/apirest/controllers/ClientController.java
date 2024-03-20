package co.com.flypass.apirest.controllers;

import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;
import co.com.flypass.apirest.handlers.IClientHandler;
import co.com.flypass.constants.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private final IClientHandler clientHandler;

    public ClientController(IClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }


    @PostMapping(path = "/save")
    public ResponseEntity<ClientResponseDTO> add(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {

        return ResponseEntity.ok().body(clientHandler.save(clientRequestDTO));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ClientResponseDTO> update(@Valid @RequestBody ClientUpdateRequestDTO clientUpdateRequestDTO) {

        return ResponseEntity.ok().body(clientHandler.update(clientUpdateRequestDTO));
    }

    @DeleteMapping("{clientId}")
    public ResponseEntity<Map<String,String>> deleteUserById(@PathVariable Long clientId){
        clientHandler.deleteClientById(clientId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.CLIENT_DELETED_MESSAGE));
    }

}
