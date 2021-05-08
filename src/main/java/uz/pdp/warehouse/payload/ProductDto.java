package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String code;
    private Long categoryId;
    private Long attachmentId;
    private Long measurementId;
    private boolean active = true;
}
