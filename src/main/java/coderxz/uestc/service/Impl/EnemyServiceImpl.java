package coderxz.uestc.service.Impl;

import coderxz.uestc.dao.EnemyMapper;
import coderxz.uestc.entity.Enemy;
import coderxz.uestc.service.EnemyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnemyServiceImpl implements EnemyService {
    @Autowired
    private EnemyMapper enemyMapper;
    @Override
    public List<Enemy> queryEnemy(Integer page, Integer size) {
        System.out.println(page);
        System.out.println(size);
        Page<Enemy> page1 = new Page<>(page, size);
        Page<Enemy> enemies = enemyMapper.selectPage(page1, null);
        return enemies.getRecords();
    }

    @Override
    public List<Enemy> queryEnemyByArea(Integer x, Integer y) {
        QueryWrapper<Enemy> wrapper = new QueryWrapper<>();
        //条件查询，找到范围在X~y直接的enemy
        wrapper.le("latitude",x).le("longitude",y);
        wrapper.ge("latitude",0).ge("longitude",0);
        List<Enemy> enemies = enemyMapper.selectList(wrapper);
        return enemies;
    }
}
