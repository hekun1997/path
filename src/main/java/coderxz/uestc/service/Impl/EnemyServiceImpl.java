package coderxz.uestc.service.Impl;

import coderxz.uestc.dao.EnemyMapper;
import coderxz.uestc.entity.Enemy;
import coderxz.uestc.service.EnemyService;
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
    public List<String> runAPF() {
        List<String> res= new LinkedList<>();
        String line;
        try{
            //从第三个参数开始为算法的入参
            String[] comm=new String[]{"D:\\Anaconda3\\python.exe", "F:\\UESTC\\apf_enemy\\RunTheProject.py", "(1,1)","(22,22)","[(103.90542, 31.33254211)]","[(103.90542, 31.33254211)]"};
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

        return res;
    }
}
