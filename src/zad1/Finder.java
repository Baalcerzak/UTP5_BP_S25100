/**
 *
 *  @author Balcerzak Piotr S25100
 *
 */

package zad1;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Finder {

    private final String fname;
    private int counter = 0;

    public Finder(String fname) {
        this.fname = fname;
    }

    public int getIfCount() {
        return readFile("ifs");
    }

    public int getStringCount(String wariant) {
        return readFile("wariant");
    }

    private int readFile(String option){
        Charset cs = StandardCharsets.UTF_8;
        Path path= Paths.get(fname);
        counter = 0;

        try(Stream<Path> walk = Files.walk(path)){
            Stream<Path> walk2 = walk.filter(Files::isRegularFile);
            if(option.equals("wariant")){
                counter = readWariant(walk2, cs);
            }else{
                counter = readIfs(walk2,cs);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }
    private int readWariant(Stream<Path> walk2, Charset cs){
        walk2.forEach(path2->{
            try {
                List<String> allLines = Files.readAllLines(path2, cs);
                for(String line : allLines){
                    checkWar(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        return counter;
    }
    private int readIfs(Stream<Path> walk2, Charset cs){
        walk2.forEach(path2->{
            try {
                List<String> allLines = Files.readAllLines(path2, cs);
                for (String line : allLines) {
                    checkIf(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        return counter;
    }
    private void checkIf(String line){
        String line2 = line.replaceAll("\\s+","");
        if(line2.contains("if(") && !line2.trim().startsWith("//") && !line2.contains("\"if")) {
            String l = line2.replaceFirst("if\\(", "");
            counter++;
            checkIf(l);
        }
    }
    private void checkWar(String line){
        if(line.contains("wariant")) {
            String l1 = line.replaceFirst("wariant", "");
            counter++;
            checkWar(l1);
        }
    }

}
