package uz.pdp.warehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class WareHouse extends AbsEntity {

}
