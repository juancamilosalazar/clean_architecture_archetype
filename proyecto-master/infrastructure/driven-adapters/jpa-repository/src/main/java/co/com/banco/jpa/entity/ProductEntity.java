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
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "exempt_fromgmf", nullable = false)
    private Boolean exemptFromGMF;

    @Column(name = "creation_date", nullable = false)
    private String creationDate;

    @Column(name = "modification_date")
    private String modificationDate;

    @JoinColumn(name = "client")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ClientEntity client;
}
