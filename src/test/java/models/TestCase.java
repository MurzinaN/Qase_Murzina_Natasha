package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {
    private String title;
    private String description;
    private String preConditions;
    private String postConditions;
}
