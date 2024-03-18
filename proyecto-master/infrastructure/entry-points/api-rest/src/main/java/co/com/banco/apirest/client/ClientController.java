package co.com.banco.apirest.client;

import co.com.banco.apirest.client.validationRQ.ClientRQ;
import co.com.banco.apirest.client.validationRQ.ClientUpdateRQ;
import co.com.banco.model.client.Client;
import co.com.banco.usecase.ClientUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientController {

    private final ClientUseCase clientUseCase;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "/save")
    public ResponseEntity<Client> add(@Valid @RequestBody ClientRQ clientRQ) {

        return ResponseEntity.ok().body(clientUseCase.save(objectMapper.convertValue(clientRQ,Client.class)));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Client> update(@Valid @RequestBody ClientUpdateRQ clientUpdateRQ) {

        return ResponseEntity.ok().body(clientUseCase.update(objectMapper.convertValue(clientUpdateRQ,Client.class)));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> delete(@RequestParam("id") String id) {

        clientUseCase.delete(Long.parseLong(id));
        return ResponseEntity.ok().body("Cliente: ".concat(id).concat(" eliminado correctamente"));
    }

}
