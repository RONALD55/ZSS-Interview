package zw.co.zss.labtest.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Category extends AbstractEntity {
    private String title;
}
