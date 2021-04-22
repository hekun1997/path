package coderxz.uestc.controller;

import coderxz.uestc.entity.Enemy;
import coderxz.uestc.service.EnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AgentController {
    @Autowired
    private EnemyService enemyService;
    @RequestMapping("/areaInfo")
    public /*List<Enemy>*/Enemy findEnemyByArea(Integer x,Integer y){
        /*System.out.println(x);
        System.out.println(y);*/
        List<Enemy> enemies = enemyService.queryEnemyByArea(x, y);
        return enemies.get(0);
    }
}
