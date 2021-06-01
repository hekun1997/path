package coderxz.uestc;

import coderxz.uestc.config.PythonConfig;
import coderxz.uestc.dto.APFParams;
import coderxz.uestc.service.EnemyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UestcDemoApplicationTests {

    @Autowired
    private EnemyService enemyService;

    @Autowired
    private PythonConfig pythonConfig;

    @Test
    public void contextLoads() {
        enemyService.queryEnemy(0,50).forEach(System.out::println);
    }

    @Test
    public void testAPF(){
        APFParams apfParams = new APFParams();
        apfParams.setStart("(103.93066,31.32343)");
        apfParams.setEnd("(103.99006,31.31375)");
        apfParams.setObstacles(Arrays.asList("(103.9548644, 31.29281989)", "(103.9551422, 31.29254211)"));//[(103.9548644, 31.29281989), (103.9551422, 31.29254211)]
        apfParams.setEnemys(Arrays.asList("[(103.90542, 31.33254211)]"));

        String start = apfParams.getStart();
        String end = apfParams.getEnd();
        String obstacles = apfParams.getObstacles().toString().replace("[[","[").replace("]]","]");
        String enemys = apfParams.getEnemys().toString().replace("[[","[").replace("]]","]");
        List<String> res= new LinkedList<>();

        String line;
        try{
            //从第三个参数开始为算法的入参
            // String[] comm = new String[]{"C:\\ProgramData\\Anaconda3\\python.exe", "C:\\D-drive-37093\\PycharmWorkSpace\\apf_enemy\\RunTheProject.py", start,end,obstacles,enemys};
            String comm = "python C:\\D-drive-37093\\PycharmWorkSpace\\apf_enemy\\RunTheProject.py \"" + start + "\" "
                    + "\"" + end + "\" " + "\"" + obstacles + "\" " + "\"" + enemys + "\" ";
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
        System.out.println(res);
    }

    @Test
    public void testPythonConfig(){
        System.out.println(pythonConfig.getProjectPath());
        System.out.println(pythonConfig.getPath());
    }
}
