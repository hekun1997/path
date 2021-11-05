package coderxz.uestc.controller;

import coderxz.uestc.dto.APFParams;
import coderxz.uestc.service.RoutePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path")
public class RoutePlanController {
    @Autowired
    private RoutePlanService routePlanService;

    @RequestMapping(value = "/global", produces = "application/json; charset=utf-8")
    public String globalPathPlanning(@RequestBody APFParams apfParams){
        System.out.println(apfParams);
        return routePlanService.global(apfParams);
    }
}
