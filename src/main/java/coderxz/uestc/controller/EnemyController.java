package coderxz.uestc.controller;

import coderxz.uestc.entity.Enemy;
import coderxz.uestc.service.EnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enemy")
public class EnemyController {

    @Autowired
    private EnemyService enemyService;

    @RequestMapping("/findAll.do")
    public List<Enemy> findAll(){
        return enemyService.queryEnemy(2,2);
    }
}
