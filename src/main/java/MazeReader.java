import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MazeReader {

    String[][] maze;
    int[] olaf; // Y, X

    public MazeReader(String filename){
        olaf = new int[2];
        try(BufferedReader br = Files.newBufferedReader(Paths.get(this.getClass().getResource(filename).toURI()))) {
            String[] l = br.readLine().split(" ");
            maze = new String[Integer.parseInt(l[1])][Integer.parseInt(l[0])];
            for(int i = 0; i<Integer.parseInt(l[1]); i++){
                var line = br.readLine();
                if(line.contains("O")){
                    olaf[0] = i;
                    olaf[1] = line.indexOf("O");
                }
                maze[i] = line.split("");
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
