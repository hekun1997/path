package coderxz.uestc;

import coderxz.uestc.dstarlite.*;
import coderxz.uestc.dto.GeoPosition;
import coderxz.uestc.dto.MapParams;
import coderxz.uestc.util.Utils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import lombok.SneakyThrows;
import net.sf.geographiclib.Geodesic;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PltTest {

    @SneakyThrows
    @Test
    public void draw_path(){
        Position start = new Position(0, 0);
        Position goal = new Position(9, 9);
        List<Position> walls = new ArrayList<>();
        final int MAX_X = 10;
        final int MAX_Y = 10;

        walls.add(new Position(1, 1));
        walls.add(new Position(1, 2));
        walls.add(new Position(2, 2));
        walls.add(new Position(3, 2));
        walls.add(new Position(5, 6));
        walls.add(new Position(5, 7));
        walls.add(new Position(5, 8));
        walls.add(new Position(3, 4));
        walls.add(new Position(4, 4));
        walls.add(new Position(5, 4));
        walls.add(new Position(6, 4));
        walls.add(new Position(7, 4));
        walls.add(new Position(8, 4));
        walls.add(new Position(3, 5));
        walls.add(new Position(5, 9));

        GridProblem gridProblem = new GridProblem(MAX_X, MAX_Y);
        gridProblem.setStart(start.getX(), start.getY());
        gridProblem.setGoal(goal.getX(), goal.getY());

        for(Position wall : walls){
            gridProblem.setWall(wall.getX(), wall.getY());
        }

        DStarLite dStarLite = new DStarLite(gridProblem);
        List<State> states = dStarLite.getShortestPath();

        List<Position> path = new ArrayList<>(states.size());

        for(State state : states){
            Cell cell = (Cell) state;
            path.add(new Position(cell.getX(), cell.getY()));
        }

        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("D:\\Anaconda\\envs\\tensorflow1.8\\python.exe"));

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

    @SneakyThrows
    @Test
    public void draw_env(){
//        List<Double> x = Arrays.asList(1D, 1D);
//        List<Double> y = Arrays.asList(2D, 3D);
//
//        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("D:\\Anaconda\\envs\\tensorflow1.8\\python.exe"));
//        plt.plot().add(x, y, "o").label("sin");
//        plt.legend().loc("upper right");
//        plt.title("scatter");
//        plt.show();
        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig("D:\\Anaconda\\envs\\tensorflow1.8\\python.exe"));
        plt.plot()
                .add(Arrays.asList(1.3, 2))
                .label("label")
                .linestyle("--");
        plt.plot().add(Arrays.asList(0, 1), Arrays.asList(1, 2), ".r");
        plt.xlabel("xlabel");
        plt.ylabel("ylabel");
        plt.text(0.5, 0.2, "text");
        plt.title("Title!");
        plt.legend();
        plt.show();

    }

    @Test
    public void stringTest(){
        //Geodesic geodesic
        MapParams mapParams = new MapParams();
        mapParams.setXSize(74);
        mapParams.setYSize(115);
        mapParams.setMaxLng(103.97761d);
        mapParams.setMinLng(103.94114d);
        mapParams.setMaxLat(31.31209d);
        mapParams.setMinLat(31.29207d);

        GeoPosition start = new GeoPosition();
        start.setLongitude(103.94114);
        start.setLatitude(31.31209);

        GeoPosition goal = new GeoPosition();
        goal.setLongitude(103.97761);
        goal.setLatitude(31.29207);

        Position start_ = Utils.lngLat2xy(start, mapParams);
        System.out.println(start_);

        Position goal_ = Utils.lngLat2xy(goal, mapParams);
        System.out.println(goal_);

        System.out.println(Utils.xy2lngLat(start_, mapParams) + "----" + start);
        System.out.println(Utils.xy2lngLat(goal_, mapParams)+ "----" + goal);
    }

    @Test
    public void matrix(){

    }
}
