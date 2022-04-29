package coderxz.uestc.service.Impl;

import coderxz.uestc.dstarlite.Position;
import coderxz.uestc.dto.APFParams;
import coderxz.uestc.dto.GeoPosition;
import coderxz.uestc.dto.MapParams;
import coderxz.uestc.dto.Obstacle;
import coderxz.uestc.service.EnemyService;
import coderxz.uestc.service.RoutePlanService;
import coderxz.uestc.util.Response;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import coderxz.uestc.util.Utils;

@Service
public class RoutePlanServiceImpl implements RoutePlanService {

    private EnemyService enemyService;

    @Value("${pythonPath}")
    private String pythonPath;

    @Autowired
    private RoutePlanServiceImpl(EnemyService enemyService){
        this.enemyService = enemyService;
    }

    @Override
    public String global(APFParams apfParams) {
        //        X - Longitude - lng
        //        Y - Latitude - lat
        //传进来都是 (Lng, Lat)
        Map<String, Object> startAndGoal = Utils.convertStartAndGoal(apfParams.getStart(), apfParams.getEnd());

        GeoPosition startGeo = (GeoPosition) startAndGoal.getOrDefault("start_geo", null);
        GeoPosition goalGeo = (GeoPosition) startAndGoal.getOrDefault("goal_geo", null);

        MapParams mapParams = Utils.createMapParams(startGeo, goalGeo);

        Position start = Utils.lngLat2xy(startGeo, mapParams);
        Position goal = Utils.lngLat2xy(goalGeo, mapParams);

        List<String> obstacleStr = enemyService.getObstacle(apfParams);
        System.out.println("extract obstacle----------->>>>>>>>>>" + obstacleStr);
        //convert enemies
        apfParams.getObstacles().addAll(apfParams.getEnemys());
        List<Obstacle> inputObstacles = Utils.convertInputObstacles(apfParams.getObstacles(), mapParams);
        List<Obstacle> obstacles = Utils.convertStr2List(obstacleStr, mapParams);
        obstacles.addAll(inputObstacles);

        List<Position> path = Utils.runDStartLite(start, goal, obstacles, mapParams);
        if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
            this.drawAll(start, goal, obstacles.stream().map(o -> new Position(o.getPosition().getX(), o.getPosition().getY())).collect(Collectors.toList()), path);
        }
        String retVal =  Utils.assembleGeoPath(path, mapParams).toString();
        return Response.success(Arrays.asList(retVal));
    }

    @SneakyThrows
    private void drawAll(Position start, Position goal, List<Position> walls, List<Position> path){
        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig(pythonPath));

        plt.plot().add(Arrays.asList((double)start.getX()), Arrays.asList((double)start.getY()), "or").label("start");
        plt.plot().add(Arrays.asList((double)goal.getX()), Arrays.asList((double)goal.getY()), "*r").label("goal");

        plt.plot().add(walls.stream().map(w -> (double)w.getX()).collect(Collectors.toList()),
                        walls.stream().map(w -> (double)w.getY()).collect(Collectors.toList()), "xk")
                .label("obstacle");

        plt.plot().add(path.stream().map(p -> (double)p.getX()).collect(Collectors.toList()),
                        path.stream().map(p -> (double)p.getY()).collect(Collectors.toList()))
                .label("path").linestyle("--");

        plt.xlabel("X");
        plt.ylabel("Y");
        plt.title("Route Plan");
        plt.legend();
        plt.show();
    }
}
