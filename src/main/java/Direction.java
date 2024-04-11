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

    Direction(int index){
        this.index = index;
    }

    private static Direction valueOfIndex(int index){
        return BY_INDEX.get(index);
    }

    public static Direction getOppositeDirection(Direction d){
        return Direction.valueOfIndex((d.index+2)% Direction.values().length);
    }

}