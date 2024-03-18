package co.com.banco.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String identificationType;
    private Long identificationNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String dateOfBirth;
    private String creationDate;
    private String modificationDate;
}
