package maze.pacman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class BFS {
	// TODO Auto-generated constructor stub
	 private static final int[] entitySTART = {1, 0};
	    private int[] end;
	    private int[] playerSTART;
	    private ArrayList<ArrayList<Integer>> maze;

	    public BFS() {
	    }

	    public int[] getEnd() {
	        return end;
	    }

	    public int[] getPlayerSTART() {
	        return playerSTART;
	    }

	    public void setEnd(int[] end) {
	        this.end = end;
	    }

	    public ArrayList<ArrayList<Integer>> getMaze() {
	        return maze;
	    }
	    
	    public void setMaze(Scanner scanner) {
	        // Khởi tạo ArrayList để lưu trữ ma trận
	        this.maze = new ArrayList<>();

	        // Đọc từng dòng và tách các giá trị bằng khoảng trắng
	        while (scanner.hasNextLine()) {
	            ArrayList<Integer> lineList = new ArrayList<>();
	            String line = scanner.nextLine();
	            String[] values = line.split("\\s+");

	            // Lưu trữ các giá trị vào ma trận
	            for (String value : values) {
	                int intValue = Integer.parseInt(value);
	                lineList.add(intValue);
	                if (intValue == 3) {
	                    end = new int[]{this.maze.size(), lineList.size() - 1};
	                }
	                if (intValue == -1) {
	                    playerSTART = new int[]{this.maze.size(), lineList.size() - 1};
	                }
	            }
	            this.maze.add(lineList);
	        }
	    }
	 // Tìm đường đi từ vị trí start đến vị trí end bằng thuật toán BFS
	    public ArrayList<int[]> bfs() {
	        int[][] visited = new int[maze.size()][maze.get(0).size()];
	        Queue<int[]> queue = new LinkedList<>();
	        queue.offer(entitySTART);

	        // Khởi tạo đỉnh đầu tiên trong đường đi
	        ArrayList<int[]> path = new ArrayList<>();
	        path.add(entitySTART);

	        while (!queue.isEmpty()) {
	            int[] current = queue.poll();
	            int x = current[0];
	            int y = current[1];
	            visited[x][y] = 1;
	            if (x == end[0] && y == end[1]) {
	                // Tìm thấy đích, trả về đường đi
	            	for (int [] m :path) 
	            		System.out.println(m[0] + " " + m[1]);
	            	return path;
	            }
	 
	            // Tìm các ô lân cận chưa được thăm
	            if (x > 0 && maze.get(x - 1).get(y) != 1 && visited[x - 1][y] == 0) {
	                int[] neighbor = new int[]{x - 1, y};
	                queue.offer(neighbor);
	                path.add(neighbor);
	                visited[x - 1][y] = 1;
	            }
	            if (y > 0 && maze.get(x).get(y - 1) != 1 && visited[x][y - 1] == 0  ) {
	                int[] neighbor = new int[]{x, y - 1};
	                queue.offer(neighbor);
	                path.add(neighbor);
	                visited[x][y - 1] = 1;
	            }
	            if (x < maze.size() - 1 && maze.get(x + 1).get(y) != 1 && visited[x + 1][y] == 0  ) {
	                int[] neighbor = new int[]{x + 1, y};
	                queue.offer(neighbor);
	                path.add(neighbor);
	                visited[x + 1][y] = 1;
	            }
	            if (y < maze.get(0).size() - 1 && maze.get(x).get(y + 1) != 1 && visited[x][y + 1] == 0  ) {
	                int[] neighbor = new int[]{x, y + 1};
	                queue.offer(neighbor);
	                path.add(neighbor);
	                visited[x][y + 1] = 1;
	            }
	        }

	        // Không tìm thấy đường đi, trả về null
	        return null;
	    }

}
