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
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "date", nullable = false)
    private String date;

    @JoinColumn(name = "source_product")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ProductEntity sourceProduct;

    @JoinColumn(name = "destination_product")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ProductEntity destinationProduct;
}
