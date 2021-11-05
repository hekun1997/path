package coderxz.uestc.dto;

import coderxz.uestc.dstarlite.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obstacle {
    //private String shape;
    private Position position;
    private double circle = 1;
}
