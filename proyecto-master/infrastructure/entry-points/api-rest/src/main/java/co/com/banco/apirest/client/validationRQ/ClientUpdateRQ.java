package co.com.banco.apirest.client.validationRQ;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateRQ {

    private Long id;
    @Valid
    @NotNull
    private String identificationType;
    private Long identificationNumber;
    @Valid
    @NotNull
    @Size(min=2,max=20)
    private String firstName;
    @Valid
    @NotNull
    @Size(min=2,max=20)
    private String lastName;
    @Valid
    @NotNull
    @Email
    private String emailAddress;
    private String dateOfBirth;
    private String creationDate;
    private String modificationDate;
}
