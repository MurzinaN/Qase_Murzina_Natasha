package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Defect {
    private String title;
    private String actual_result;
    int severity;
}
