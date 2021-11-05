package coderxz.uestc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapParams {
    private int xSize;
    private int ySize;
    private double maxLng;
    private double minLng;
    private double maxLat;
    private double minLat;
}
