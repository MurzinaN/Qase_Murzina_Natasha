package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Defect {
    private String defectTitle;
    private String actualResult;
}
