package coderxz.uestc;

import coderxz.uestc.dstarlite.*;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DStarLiteTest {
    private static final String PYTHON_PATH = "D:\\Anaconda\\envs\\tensorflow1.8\\python.exe";

    static Position start = new Position(0, 0);
    static Position goal = new Position(100, 100);
    static List<Position> walls = new ArrayList<Position>();
    static List<Position> path = new ArrayList<Position>();
    static final int MAX_X = 101;
    static final int MAX_Y = 101;

    @Test
    public void path_planning_1(){
        long start_time = System.currentTimeMillis();
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
        double time = (System.currentTimeMillis() - start_time) / (1000.0);
        System.out.println(time + "s");
        drawAll(start, goal, walls, path);

    }

    @Test
    public void path_planning_2(){
        long start_time = System.currentTimeMillis();

        Position start = new Position(50, 20);
        Position goal = new Position(50, 42);
        List<Position> walls = new ArrayList<Position>();
        //List<Position> path = new ArrayList<Position>();
        final int MAX_X = 61;
        final int MAX_Y = 61;

        int x = 40, y = 20;
        int len = 20;
        for (int i = 0; i < len; i++){
            walls.add(new Position(x, y + i));
        }

        y += len;
        for (int i = 0; i < len; i++){
            walls.add(new Position(x + i, y));
        }

        x += len;
        for (int i = 0; i < len; i++){
            walls.add(new Position(x, y - i));
        }

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
        double time = (System.currentTimeMillis() - start_time) / (1000.0);
        System.out.println(time + "s");
        drawAll(start, goal, walls, path);
    }

    @Test
    public void path_planning_3(){
        long start_time = System.currentTimeMillis();

        Position start = new Position(0, 0);
        Position goal = new Position(20, 20);
        List<Position> walls = new ArrayList<Position>();
        //List<Position> path = new ArrayList<Position>();
        final int MAX_X = 21;
        final int MAX_Y = 21;

        Random random = new Random();
        for (int i = 0; i < 80; i++){
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            walls.add(new Position(x, y));
        }

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
        double time = (System.currentTimeMillis() - start_time) / (1000.0);
        System.out.println(time + "s");
        System.out.println(path);
        drawAll(start, goal, walls, path);
    }

    @Test
    public void path_planning_4(){
        long start_time = System.currentTimeMillis();
        int maxX = 60;
        Position start = new Position(0, 0);
        Position goal = new Position(maxX, maxX);
        List<Position> walls = new ArrayList<Position>();
        //List<Position> path = new ArrayList<Position>();
        final int MAX_X = maxX + 1;
        final int MAX_Y = maxX + 1;

        int x = 20;
        int y = 30;

        for (int i = 0; i < 30; i++){
            walls.add(new Position(x + i, y));
        }
//        x = 10;
//        y = 10;
//        for (int i = 0; i < 40; i++){
//            walls.add(new Position(x, y + i));
//        }
//
//        x = 40;
//        y = 30;
//        for (int i = 0; i < 20; i++){
//            walls.add(new Position(x, y + i));
//        }
//
//        x = 30;
//        y = 35;
//        for (int i = 0; i < 20; i++){
//            walls.add(new Position(x, y + i));
//        }
//
//        x = 35;
//        y = 0;
//        for (int i = 0; i < 30; i++){
//            walls.add(new Position(x, y + i));
//        }
//
//        x = 45;
//        y = 45;
//        for (int i = 0; i < 15; i++){
//            walls.add(new Position(x, y + i));
//        }

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
        double time = (System.currentTimeMillis() - start_time) / (1000.0);
        System.out.println(time + "s");
        System.out.println(path);
        drawAll(start, goal, walls, path);
    }

    @Test
    public void path_planning_5(){
        long start_time = System.currentTimeMillis();
        int maxX = 60;
        Position start = new Position(0, 0);
        Position goal = new Position(maxX, maxX);
        List<Position> walls = new ArrayList<Position>();
        //List<Position> path = new ArrayList<Position>();
        final int MAX_X = maxX + 1;
        final int MAX_Y = maxX + 1;

        int x = 10;
        int y = 10;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++) {
                walls.add(new Position(x + j, y));
            }
            y += 1;
        }

        x = 20;
        y = 30;
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++) {
                walls.add(new Position(x + j, y));
            }
            y += 1;
        }

        x = 40;
        y = 0;
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++) {
                walls.add(new Position(x + j, y));
            }
            y += 1;
        }

        x = 35;
        y = 25;
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++) {
                walls.add(new Position(x + j, y));
            }
            y += 1;
        }

        x = 5;
        y = 30;
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++) {
                walls.add(new Position(x + j, y));
            }
            y += 1;
        }

        x = 50;
        y = 45;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++) {
                walls.add(new Position(x + j, y));
            }
            y += 1;
        }

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
        double time = (System.currentTimeMillis() - start_time) / (1000.0);
        System.out.println(time + "s");
        System.out.println(path);
        drawAll(start, goal, walls, path);
    }

    @Test
    public void random(){
        Random random = new Random();
        System.out.println(random.nextInt(20));
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
