package coderxz.uestc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APFParams {
    private String start;
    private String end;
    private List<String> obstacles;
    private List<String> enemys;
}
