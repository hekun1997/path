package coderxz.uestc.util;

import coderxz.uestc.dstarlite.*;
import coderxz.uestc.dto.APFParams;
import coderxz.uestc.dto.GeoPosition;
import coderxz.uestc.dto.MapParams;
import coderxz.uestc.dto.Obstacle;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Slf4j
public class Utils {

    private static final int distanceBetweenPoints = 30;  // m

    //X - Lng
    //Y - Lat
    public static Position lngLat2xy(GeoPosition geoPosition, MapParams mapParams){
        int x = (int) Math.round((geoPosition.getLongitude() - mapParams.getMinLng()) / (mapParams.getMaxLng() - mapParams.getMinLng()) * mapParams.getXSize());
        int y = (int) Math.round((geoPosition.getLatitude() - mapParams.getMinLat()) / (mapParams.getMaxLat() - mapParams.getMinLat()) * mapParams.getYSize());
        return new Position(x, y);
    }

    //X - Lng
    //Y - Lat
    public static GeoPosition xy2lngLat(Position position, MapParams mapParams){
        double lng = (position.getX() * 1.0d / mapParams.getXSize()) * (mapParams.getMaxLng() - mapParams.getMinLng()) + mapParams.getMinLng();
        double lat = (position.getY() * 1.0d / mapParams.getYSize()) * (mapParams.getMaxLat() - mapParams.getMinLat()) + mapParams.getMinLat();
        return new GeoPosition(lat, lng);
    }

    //通过经纬度计算两点之间距离--start
    private static final  double EARTH_RADIUS = 6378137;//赤道半径

    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    //todo 距离计算有问题
    public static double geoDistance(GeoPosition p1, GeoPosition p2) {
        double lon1 = p1.getLongitude();
        double lat1 = p1.getLatitude();
        double lon2 = p2.getLongitude();
        double lat2 =p2.getLatitude();

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        return s;//单位米
    }
    //--end

    @SuppressWarnings("unchecked")
    public static List<Obstacle> convertStr2List(List<String> obstacleStrList, MapParams mapParams){
        //[(103.91586, 31.25188), (103.91618114285714, 31.252155714285713), (103.91650228571429, 31.252155714285713)]
        List<Obstacle> obstacles = new ArrayList<>();
        //obstacleStrList 一般情况下只有一行
        for (String obstacleStr: obstacleStrList){
            obstacleStr = obstacleStr.replaceAll("\\[", "")
                    .replaceAll("]", "").replaceAll(" ", "")
                    .replaceAll("\\(", "");
            obstacleStr = obstacleStr.substring(0, obstacleStr.length() - 1);
            for (String s : obstacleStr.split("\\),")){
                Obstacle obstacle = new Obstacle();
                GeoPosition geoPosition = new GeoPosition(Double.parseDouble(s.split(",")[1]), Double.parseDouble(s.split(",")[0]));
                obstacle.setPosition(lngLat2xy(geoPosition, mapParams));

                obstacles.add(obstacle);
            }
        }

        return obstacles;
    }

    public static List<Obstacle> convertInputObstacles(List<String> obstacleList, MapParams mapParams){
        List<Obstacle> obstacles = new ArrayList<>(obstacleList.size());

        for (String obstacleStr : obstacleList){
            obstacleStr = obstacleStr.replaceAll("\\(", "").replaceAll("\\)", "")
                    .replaceAll(" ", "");
            String[] geoObstacle = obstacleStr.split(",");
            GeoPosition geoPosition = new GeoPosition();
            geoPosition.setLongitude(Double.parseDouble(geoObstacle[0]));
            geoPosition.setLatitude(Double.parseDouble(geoObstacle[1]));

            Position position = lngLat2xy(geoPosition, mapParams);

            Obstacle obstacle = new Obstacle(position, 1);
            obstacles.add(obstacle);
        }

        return obstacles;
    }

    public static Map<String, Object> convertStartAndGoal(String start_str, String goal_str){
        Map<String, Object> retVal = new HashMap<>(2);

        String[] start_arr = start_str.replace('(', '\0').replace(')', '\0').split(",");
        String[] goal_arr = goal_str.replace('(', '\0').replace(')', '\0').split(",");

        retVal.put("start_geo", new GeoPosition(Double.parseDouble(start_arr[1]), Double.parseDouble(start_arr[0])));
        retVal.put("goal_geo", new GeoPosition(Double.parseDouble(goal_arr[1]), Double.parseDouble(goal_arr[0])));

        return retVal;
    }

    public static MapParams createMapParams(GeoPosition startGeo, GeoPosition goalGeo){
        MapParams mapParams  = new MapParams();

        double minLat = Math.min(startGeo.getLatitude(), goalGeo.getLatitude());
        double maxLat = Math.max(startGeo.getLatitude(), goalGeo.getLatitude());
        double minLng = Math.min(startGeo.getLongitude(), goalGeo.getLongitude());
        double maxLng = Math.max(startGeo.getLongitude(), goalGeo.getLongitude());

        double xDistance = geoDistance(new GeoPosition(minLat, minLng), new GeoPosition(maxLat, minLng));
        double yDistance = geoDistance(new GeoPosition(minLat, minLng), new GeoPosition(minLat, maxLng));

        mapParams.setXSize((int)Math.floor(xDistance / distanceBetweenPoints));
        mapParams.setYSize((int)Math.floor(yDistance / distanceBetweenPoints));

        mapParams.setMinLat(minLat);
        mapParams.setMaxLat(maxLat);
        mapParams.setMinLng(minLng);
        mapParams.setMaxLng(maxLng);

        return mapParams;
    }

    public static List<Position> runDStartLite(Position start, Position goal, List<Obstacle> obstacles, MapParams mapParams){
        int MAX_X = mapParams.getXSize() + 1;
        int MAX_Y = mapParams.getYSize() + 1;
        GridProblem gridProblem = new GridProblem(MAX_X, MAX_Y);
        gridProblem.setStart(start.getX(), start.getY());
        gridProblem.setGoal(goal.getX(), goal.getY());

        for(Obstacle obstacle : obstacles){
            int x = obstacle.getPosition().getX();
            int y = obstacle.getPosition().getY();
            if (x >=0 && x <= MAX_X && y >= 0 && y <= MAX_Y){
                gridProblem.setWall(x, y);
            }
        }

        DStarLite dStarLite = new DStarLite(gridProblem);

        List<State> states = dStarLite.getShortestPath();
        List<Position> path = new ArrayList<>();

        for (State state : states){
            Cell cell = (Cell)state;
            path.add(new Position(cell.getX(), cell.getY()));
        }

        return path;
    }

    public static List<String> callPython(APFParams apfParams, String pythonPath, String pythonFilePython){
        String start = apfParams.getStart();
        String end = apfParams.getEnd();
        String obstacles = apfParams.getObstacles().toString().replace("[[","[").replace("]]","]");
        String enemys = apfParams.getEnemys().toString().replace("[[","[").replace("]]","]");

        List<String> retVal= new LinkedList<>();
        String line;

        try{
            //从第三个参数开始为算法的入参
            String[] comm = new String[]{pythonPath, pythonFilePython, start, end, obstacles, enemys};
            Process pr = Runtime.getRuntime().exec(comm);
//for debug
//            BufferedReader err = new BufferedReader(new InputStreamReader(pr.getErrorStream(),"GBK"));
//            while ((line = err.readLine()) != null) {
//                retVal.add(line);
//                log.error(line);
//            }
//            err.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"GBK"));
            while ((line = in.readLine()) != null) {
                retVal.add(line);
                log.info(line);
            }
            in.close();
            pr.waitFor();
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
        return retVal;
    }

    public static List<GeoPosition> assembleGeoPath(List<Position> path, MapParams mapParams){
        List<GeoPosition> geoPath = new ArrayList<>();
        for (Position position : path){
            GeoPosition geoPosition = xy2lngLat(position, mapParams);
            geoPath.add(geoPosition);
        }
        return geoPath;
    }
}
