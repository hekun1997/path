package coderxz.uestc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jade.content.AgentAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("enemy")
public class Enemy implements AgentAction {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String latitude;
    private String longitude;
    private Integer type;
    private Integer topography;
    private Integer dist_50;
    private Integer dist_100;
    private Integer num_tank;
    private  Integer num_commander;
    private Integer label;



}
