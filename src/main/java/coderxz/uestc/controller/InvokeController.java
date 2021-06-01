package coderxz.uestc.controller;

import coderxz.uestc.dto.APFParams;
import coderxz.uestc.service.InvokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/invoke")
public class InvokeController {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(InvokeController.class);

    @Autowired
    private InvokeService invokeService;

    @RequestMapping(value = "/planningPath", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String runAPF1(@RequestBody APFParams apfParams){
        log.info(String.valueOf(apfParams));
        return invokeService.pathPlanningWithRoad(apfParams);
    }
}
