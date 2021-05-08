package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputProductDto {
    private Long id;
    private Double amount;
    private Float price;

    private Long productId;
    private Long outputId;
}
