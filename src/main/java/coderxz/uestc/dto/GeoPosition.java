package coderxz.uestc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoPosition {
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
//        return "GeoPosition{" +
//                "latitude=" + latitude +
//                ", longitude=" + longitude +
//                '}';
        return "(" + longitude + ", " + latitude + ")";
    }
}
