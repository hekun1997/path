package coderxz.uestc;

import coderxz.uestc.service.EnemyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UestcDemoApplicationTests {
    @Autowired
    private EnemyService enemyService;
    @Test
    public void contextLoads() {
        enemyService.queryEnemy(0,50).forEach(System.out::println);
    }

}
