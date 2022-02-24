package zw.co.zss.labtest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import zw.co.zss.labtest.enums.EntityStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class BookDto implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private EntityStatus status;

    @JsonProperty("dateCreated")
    private OffsetDateTime dateCreated;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("categoryDto")
    private CategoryDto categoryDto;
}
