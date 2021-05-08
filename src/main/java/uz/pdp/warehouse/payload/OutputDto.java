package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputDto {
    private Long id;
    private Timestamp date;
    private String factureNumber;
    private String code;

    private Long wareHouseId;
    private Long clientId;
    private Long currencyId;
}