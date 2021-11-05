package coderxz.uestc.service.Impl;

import coderxz.uestc.dao.EnemyMapper;
import coderxz.uestc.dto.APFParams;
import coderxz.uestc.entity.Enemy;
import coderxz.uestc.service.EnemyService;
import coderxz.uestc.util.Response;
import coderxz.uestc.util.Utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class EnemyServiceImpl implements EnemyService {

    @Autowired
    private EnemyMapper enemyMapper;
    @Value("${partialRoutePlan}")
    private String partialFilePath;
    @Value("${createObstacleFromGdal}")
    private String createObstacleFromGdal;
    @Value("${pythonPath}")
    private String pythonPath;

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

    @Override
    public String runAPF(APFParams apfParams) {
        List<String> retVal = Utils.callPython(apfParams, pythonPath, partialFilePath);

        if (Objects.isNull(retVal)){
            return Response.failed(null);
        }
        return Response.success(retVal);
    }

    @Override
    public List<String> getObstacle(APFParams apfParams) {
        List<String> retVal = Utils.callPython(apfParams, pythonPath, createObstacleFromGdal);

        return retVal;
    }
}
