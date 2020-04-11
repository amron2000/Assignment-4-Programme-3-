
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class charFile {

    public static void main(String args[]) {

        try {
            Files.lines(Paths.get("new.txt"), StandardCharsets.UTF_8)
                    .map(String::toLowerCase)
                    .flatMapToInt(String::chars)
                    .mapToObj(c -> (char)c)
                    .filter(Character::isLetter)
                    .collect(Collectors.groupingBy(c -> c, TreeMap::new, Collectors.counting()))
                    .forEach((k, v) -> System.out.println(k + ": " + v));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
