package models.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Runs {
    @Builder.Default
    private int total = 0;
    @Builder.Default
    private int active = 0;
}
