package models.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Counts {
    @Builder.Default
    private int cases = 0;
    @Builder.Default
    private int suites = 0;
    @Builder.Default
    private int milestones = 0;
    private Runs runs;

}
