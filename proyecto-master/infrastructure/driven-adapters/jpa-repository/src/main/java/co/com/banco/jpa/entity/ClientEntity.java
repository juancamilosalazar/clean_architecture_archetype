package co.com.banco.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identification_type", nullable = false)
    private String identificationType;

    @Column(name = "identification_number", nullable = false)
    private Long identificationNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "dateOf_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "creation_date", nullable = false)
    private String creationDate;

    @Column(name = "modification_date", nullable = true)
    private String modificationDate;

}
