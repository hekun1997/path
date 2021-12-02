package coderxz.uestc;

import coderxz.uestc.dstarlite.*;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DStarLiteTest {
    private static final String PYTHON_PATH = "D:\\Anaconda\\envs\\tensorflow1.8\\python.exe";

    static Position start = new Position(0, 0);
    static Position goal = new Position(9, 9);
    static List<Position> walls = new ArrayList<Position>();
    static List<Position> path = new ArrayList<Position>();
    static final int MAX_X = 10;
    static final int MAX_Y = 10;

    @Test
    public void path_planning_1(){
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
        //       List<State> states = dStarLite.getShortestPath();
        List<Position> path = new ArrayList<>();

        for(State state : dStarLite.getShortestPath()){
            Cell cell = (Cell)state;
            path.add(new Position(cell.getX(), cell.getY()));
        }
        drawAll(start, goal, walls, path);
    }

    @SneakyThrows
    private void drawAll(Position start, Position goal, List<Position> walls, List<Position> path){
        Plot plt = Plot.create(PythonConfig.pythonBinPathConfig(PYTHON_PATH));

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
