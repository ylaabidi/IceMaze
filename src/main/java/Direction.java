import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Direction{
    Up(0), Right(1), Down(2), Left(3);

    public final int index;
    private static final Map<Integer, Direction> BY_INDEX = new HashMap<>();

    static {
        for(Direction d : values()){
            BY_INDEX.put(d.index, d);
        }
    }

    private Direction(int index){
        this.index = index;
    }

    public static Direction valueOfIndex(int index){
        return BY_INDEX.get(index);
    }

    public static List<Direction> getSideDirections(Direction d){
        var x =Direction.values().length;
        var i = (d.index+1) % 4;
        var j = (d.index+3) % 4;
        var k = 5%4;
        var k2 = 4%4;
        return List.of(

                Direction.valueOfIndex(i),
                Direction.valueOfIndex(j)
        );
    }

}