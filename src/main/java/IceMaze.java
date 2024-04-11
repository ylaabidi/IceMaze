import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

public class IceMaze {
    String[][] maze;
    Integer[][] res;
    int[] olaf;
    int Xmax ;
    int Ymax ;
    Queue<Node> nodesToExplore = new ArrayDeque<>();

    public IceMaze(String filename){
        var mr = new MazeReader(filename);
        maze = mr.maze;
        res = new Integer[maze.length][maze[0].length];
        olaf = mr.olaf;
        Xmax = maze[0].length;
        Ymax = maze.length;
    }

    public void explore(){
        int x = olaf[1];
        int y = olaf[0];
        res[y][x] = 0;
        for(Direction d : Direction.values())
            nodesToExplore.add(new Node(x, y, d, 1));

        while(!nodesToExplore.isEmpty()){
            var n = nodesToExplore.remove();
            if( 0 > n.y || n.y > Ymax-1 || 0 > n.x || n.x > Xmax-1)
                continue;
            if(res[n.y][n.x] != null) {
                if(Objects.equals(maze[n.y][n.x], "_")) {
                    nodesToExplore.add(new Node(n.x, n.y, n.d, n.score));
                }
                continue;
            }

            switch(maze[n.y][n.x]){
                case "#" -> {
                    res[n.y][n.x] = -1;
                }
                case "." -> {
                    res[n.y][n.x] = n.score;
                    for(Direction r : Direction.values())
                        if(r != Direction.getOppositeDirection(n.d))
                            nodesToExplore.add(new Node(n.x, n.y, r, n.score+1));
                }
                case "_" -> {
                    res[n.y][n.x] = n.score;
                    for(Direction dir : Direction.values()) {
                        if(dir == Direction.getOppositeDirection(n.d))
                            continue;
                        int[] next = Node.nextCoord(n.x, n.y, dir);
                        if(dir == n.d){
                            nodesToExplore.add(new Node(n.x, n.y, n.d, n.score));
                        }else{
                            if(Objects.equals(maze[next[1]][next[0]], "#")){
                                Direction opDir = Direction.getOppositeDirection(dir);
                                nodesToExplore.add(new Node(n.x, n.y, opDir, n.score + 1));
                            }
                        }
                    }
                }
            }
//            prettyPrintMaze(res);
        }
        for(int i=0; i<Ymax; i++){
            for(int j=0; j<Xmax; j++){
                if(res[i][j] == null)
                    res[i][j] = -1;
            }
        }
        prettyPrintMaze(res);
    }



    public void prettyPrintMaze(Object[][] array){
        for(Object[] row : array){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

