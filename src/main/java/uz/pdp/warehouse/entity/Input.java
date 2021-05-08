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
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;
    private String factureNumber;

    @Column(unique = true,nullable = false)
    private String code;

    @ManyToOne
    private WareHouse wareHouse;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Currency currency;
}
