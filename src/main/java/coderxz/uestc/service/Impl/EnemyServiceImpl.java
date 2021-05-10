package coderxz.uestc.service.Impl;

import coderxz.uestc.dao.EnemyMapper;
import coderxz.uestc.dto.APFParams;
import coderxz.uestc.entity.Enemy;
import coderxz.uestc.service.EnemyService;
import coderxz.uestc.util.NR;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
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

    @Override
    public String runAPF(APFParams apfParams) {
        String start = apfParams.getStart();
        String end = apfParams.getEnd();
        String obstacles = apfParams.getObstacles().toString().replace("[[","[").replace("]]","]");
        String enemys = apfParams.getEnemys().toString().replace("[[","[").replace("]]","]");
        List<String> res= new LinkedList<>();
        String line;
        try{
            //从第三个参数开始为算法的入参
            String[] comm=new String[]{"D:\\Anaconda3\\python.exe", "F:\\UESTC\\apf_enemy\\RunTheProject.py", start,end,obstacles,enemys};
            Process pr = Runtime.getRuntime().exec(comm);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"GBK"));
            while ((line = in.readLine()) != null) {
                res.add(line);
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        } catch (Exception e){
            e.printStackTrace();
        }

        return NR.r(res);
    }
}
