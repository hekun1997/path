package coderxz.uestc.service;

import coderxz.uestc.dto.APFParams;
import coderxz.uestc.entity.Enemy;

import java.util.List;

public interface EnemyService {

    /**
     * 分页查询所有的实体
     * @param page
     * @param size
     * @return
     */
    List<Enemy>queryEnemy(Integer page,Integer size);

    /**
     * 查询某个区域内的所有enemy对象
     * @param x
     * @param y
     * @return
     */
    List<Enemy>queryEnemyByArea(Integer x,Integer y);

    String runAPF(APFParams apfParams);

    /**
     * 只是调用python读取高程数据，判断不可达点。后期可能会寻找python实现d star lite的方法。
     * @param apfParams
     * @return
     */
    List<String> getObstacle(APFParams apfParams);
}
