package ca.terrylockett.day2b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DeepDarkNavigator {

    private static final String DIRECTION_ENTRY = "(\\w+)\\s(\\d+)";
    private static final Pattern DIRECTION_ENTRY_PATTERN = Pattern.compile(DIRECTION_ENTRY);

    private final String fileName;
    private Submarine submarine;

    private enum Direction {
        FORWARD,
        UP,
        DOWN
    }

    public DeepDarkNavigator(String fileName) {
        this.fileName = fileName;
    }

    private void navigateCourse() throws IOException {
        this.submarine = new Submarine();

        try (Stream<String> stream = Files.lines(Paths.get(this.getFile()))) {
            stream.map(String::toUpperCase).forEach(this::processCommand);
        }
    }

    private void processCommand(String s) {
        var m = DIRECTION_ENTRY_PATTERN.matcher(s);
        if(m.find()){
            var value = Integer.parseInt(m.group(2));

            switch (Direction.valueOf(m.group(1))) {
                case FORWARD:
                    this.submarine.move(value);
                    break;
                case UP:
                    this.submarine.modifyAim(value);
                    break;
                case DOWN:
                    this.submarine.modifyAim(-value);
                    break;
                default:
                    break;
            }
        }
    }

    private String getFile() {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getFile();
    }

    public int run() {
        try {
            navigateCourse();
        }
        catch (Exception e) {
            return 0;
        }

        return this.submarine.getTotal();
    }
}
