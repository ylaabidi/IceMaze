import java.util.Arrays;

public class Node {
    int x;
    int y;

    Direction d;
    int score;
    public Node(int x, int y, Direction d, int score){
        int[] a = nextCoord(x, y, d);
        this.x = a[0];
        this.y = a[1];
        this.d = d;
        this.score = score;
    }


    public static int[] nextCoord(int x, int y, Direction d){

        switch(d){
            case Up -> y-=1;
            case Down -> y+=1;
            case Left -> x-=1;
            case Right -> x+=1;
        }
        return new int[]{x, y};
    }
}
