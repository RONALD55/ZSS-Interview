package zw.co.zss.labtest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import zw.co.zss.labtest.enums.EntityStatus;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class CategoryDto implements Serializable {
    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private EntityStatus status;

    @JsonProperty("dateCreated")
    private OffsetDateTime dateCreated;

    @JsonProperty("title")
    private String title;
}
