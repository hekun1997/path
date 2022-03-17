package coderxz.uestc.controller;

import coderxz.uestc.dto.APFParams;
import coderxz.uestc.entity.Enemy;
import coderxz.uestc.service.EnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/runAPF", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String runAPF1(@RequestBody APFParams apfParams){
        System.out.println(apfParams);
        String result = enemyService.runAPF(apfParams);
        System.out.println(result);
        return result;
    }
}
