package coderxz.uestc.service.Impl;

import coderxz.uestc.config.PythonConfig;
import coderxz.uestc.dto.APFParams;
import coderxz.uestc.service.InvokeService;
import coderxz.uestc.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvokeServiceImpl implements InvokeService {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(InvokeService.class);

    @Autowired
    private PythonConfig pythonConfig;

    @Override
    public String pathPlanningWithRoad(APFParams apfParams) {
        String start = apfParams.getStart();
        String end = apfParams.getEnd();

        List<String> retVal = new ArrayList<>();
        String line;
        try{
            String filePath = pythonConfig.getProjectPath() + "\\RunTheProject.py";
            String[] comm=new String[]{pythonConfig.getPath(), filePath, start, end};
            Process pr = Runtime.getRuntime().exec(comm);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"GBK"));
            while ((line = in.readLine()) != null) {
                retVal.add(line);
                log.info(line);
            }
            in.close();
            pr.waitFor();
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return Response.success(retVal);
    }
}
