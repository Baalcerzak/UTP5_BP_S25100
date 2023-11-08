package zad2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Stream;

public class Futil implements FileVisitor<Path> {
    public static void processDir(String dirName, String resultFileName)  {

        Charset cs = StandardCharsets.UTF_8;
        Charset cs1 = Charset.forName("CP1250");

        Path path= Paths.get(dirName);
        Path path1= Paths.get(resultFileName);

        try(Stream<Path> walk = Files.walk(path)){
            FileWriter fileWriter = new FileWriter(resultFileName);
            Stream<Path> walk2 = walk.filter(Files::isRegularFile);
            walk2.forEach(path2->{
                try {
                    List<String> allLines = Files.readAllLines(path2, cs1);
                    Files.write(path1, allLines, cs, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
