package uz.pdp.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;

    @Column(unique = true, nullable = false)
    private String code;
    private String factureNumber;

    @ManyToOne
    private WareHouse wareHouse;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Currency currency;


}
