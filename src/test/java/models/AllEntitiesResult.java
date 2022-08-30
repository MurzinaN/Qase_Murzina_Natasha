package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AllEntitiesResult<T> {
    private List<T> entities;
    private int total;
    private int filtered;
    private int count;
}
