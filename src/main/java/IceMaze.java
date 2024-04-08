import java.util.ArrayDeque;
import java.util.Arrays;
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
        prettyPrintMaze(maze);
        int x = olaf[1];
        int y = olaf[0];
        res[y][x] = 0;
        for(Direction r : Direction.values())
            nodesToExplore.add(new Node(x, y, r, 1));

        while(!nodesToExplore.isEmpty()){
            var n = nodesToExplore.remove();
            if( 0 > n.y || n.y > Ymax-1 || 0 > n.x || n.x > Xmax-1 || res[n.y][n.x] != null)
                continue;
            switch(maze[n.y][n.x]){
                case "#" -> {
                    res[n.y][n.x] = -1;
                    continue;
                }
                case "." -> {
                    res[n.y][n.x] = n.score;
                    for(Direction r : Direction.values())
                        nodesToExplore.add(new Node(n.x, n.y, r, n.score+1));
                }
                case "_" -> {
                    int score;
                    int[] next = Node.nextCoord(n.x, n.y, n.d);
                    if(maze[next[1]][next[0]] == "_") {
                        String s = "Cas _ suivi d'un _";
                        score = n.score;
                    }else {
                        score= n.score;
                        String s = "Cas _ suivi d'un # .";
                    }
                    res[n.y][n.x] = score;
                    nodesToExplore.add(new Node(n.x, n.y, n.d, score));

                    for(Direction d : Direction.getSideDirections(n.d)) {
                        nodesToExplore.add(new Node(n.x, n.y, d, score+1));
                    }

                }
            }
            prettyPrintMaze(res);
        }
        for(int i=0; i<Ymax; i++){
            for(int j=0; j<Xmax; j++){
                if(res[i][j] == null)
                    res[i][j] = -1;
            }
        }
        prettyPrintMaze(res);
    }



//    Version 1
//    public void stepTowards(String dir, int score, int y, int x){
//        prettyPrintRes();
//        System.out.println();
//        if(res[y][x] == 0)
//            return;
//        switch(maze[y][x]){
//            case "#" -> {
//                res[y][x] = -1;
//            }
//            case "." -> {
//                res[y][x] = score;
//                stepTowards("N", score+1, y-1, x);
//                stepTowards("E", score+1, y, x+1);
//                stepTowards("S", score+1, y+1, x);
//                stepTowards("W", score+1, y, x-1);
//            }
//            case "_" -> {
//                res[y][x] = score;
//                switch(dir){
//                    case "N" -> stepTowards("N", score, y-1, x);
//                    case "E" -> stepTowards("E", score, y, x+1);
//                    case "S" -> stepTowards("S", score, y+1, x);
//                    case "W" -> stepTowards("W", score, y, x-1);
//                }
//            }
//        }
//    }






    public void prettyPrintMaze(Object[][] array){
        for(Object[] row : array){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
    void prettyPrintRes(int[][] res){
        for(int[] row : res){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

