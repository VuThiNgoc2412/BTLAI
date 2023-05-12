package maze.pacman;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Test extends Application {

    private static final int MAZE_SIZE = 10;
    private static final int CELL_SIZE = 50;
    private static final int[][] MOVES = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private ArrayList<ArrayList<Integer>> maze;
    private int[] playerPos;

    public Test() {
        Random rand = new Random();
        maze = new ArrayList<>();
        for (int i = 0; i < MAZE_SIZE; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < MAZE_SIZE; j++) {
                if (rand.nextInt(5) == 0) {
                    row.add(-1);
                } else {
                    row.add(0);
                }
            }
            maze.add(row);
        }

        playerPos = new int[]{0, 0};
        maze.get(playerPos[0]).set(playerPos[1], 1);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                Label cell = new Label();
                cell.setMinSize(CELL_SIZE, CELL_SIZE);
                cell.setMaxSize(CELL_SIZE, CELL_SIZE);
                cell.setStyle("-fx-border-color: black; -fx-background-color: white;");
                if (maze.get(i).get(j) == -1) {
                    cell.setStyle("-fx-border-color: black; -fx-background-color: black;");
                } else if (maze.get(i).get(j) == 1) {
                    cell.setStyle("-fx-border-color: black; -fx-background-color: blue;");
                }
                grid.add(cell, j, i);
            }
        }

        Scene scene = new Scene(grid, (MAZE_SIZE * CELL_SIZE) + ((MAZE_SIZE + 1) * 5), (MAZE_SIZE * CELL_SIZE) + ((MAZE_SIZE + 1) * 5));
        primaryStage.setTitle("Maze Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
