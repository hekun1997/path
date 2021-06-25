package coderxz.uestc.dao;

import coderxz.uestc.entity.Enemy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnemyMapper extends BaseMapper<Enemy> {

    @Insert("insert into enemy(latitude,longitude,type,topography,dist_50,dist_100,num_tank,num_commander,label) " +
            " values(#{latitude},#{longitude},#{type},#{topography},#{dist_50},#{dist_100},#{num_tank},#{num_commande},#{lable})")
    void addEnemy(Enemy enemy);

    @Select("select * from enemy")
    List<Enemy> queryEnemy();
}
