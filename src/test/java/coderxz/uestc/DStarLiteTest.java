package coderxz.uestc;

import coderxz.uestc.dstarlite.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DStarLiteTest {
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
        drawEnv(dStarLite.getShortestPath());

        start = new Position(4, 5);
        gridProblem.setStart(start.getX(), start.getY());
        gridProblem.setWallAndChange(5, 5);
        walls.add(new Position(5, 5));

        drawEnv(dStarLite.getShortestPath());
    }

    public static String checkPos(int x, int y){
        if (x == start.getX() && y == start.getY()){
            return "s ";
        }
        else if (x == goal.getX() && y == goal.getY()){
            return "g ";
        }
        else if (walls.contains(new Position(x, y))){
            return "w ";
        }
        else if (path.contains(new Position(x, y))){
            return "- ";
        }
        return "  ";
    }

    public static void drawEnv(List<State> states){
        if (!path.isEmpty()){
            path.clear();
        }

        for(State state : states){
            Cell cell = (Cell)state;
            path.add(new Position(cell.getX(), cell.getY()));
        }

        for(int _y = MAX_Y - 1; _y > -1; _y --){
            StringBuilder buffer = new StringBuilder(_y + " ");
            for (int _x = 0; _x < MAX_X; _x ++){

                buffer.append(checkPos(_x, _y));

            }
            System.out.println(buffer);
        }
        System.out.print("  ");
        for (int _x = 0; _x < MAX_X; _x ++){
            System.out.print(_x + " ");
        }
        System.out.println("\n");
    }
}
