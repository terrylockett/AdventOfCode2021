package ca.terrylockett.day2a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DeepDarkNavigator {

    static final String DIRECTION_ENTRY = "(\\w+)\\s(\\d+)";
    static final Pattern DIRECTION_ENTRY_PATTERN = Pattern.compile(DIRECTION_ENTRY);

    private final String fileName;
    private final Map<Direction, Integer> directionsMap;

    private enum Direction {
        FORWARD,
        UP,
        DOWN
    }


    DeepDarkNavigator(String fileName) {
        this.fileName = fileName;

        this.directionsMap = new EnumMap<>(Map.of(
                Direction.FORWARD, 0,
                Direction.UP, 0,
                Direction.DOWN, 0
        ));
    }


    private void populateDirectionsMap() throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get(
                Objects.requireNonNull(
                        this.getClass().getClassLoader()
                                .getResource(fileName)).getFile()))) {

            stream.map(String::toUpperCase).forEach(this::processDirectionEntry);

        }
    }

    void processDirectionEntry(String s) {
        var m = DIRECTION_ENTRY_PATTERN.matcher(s);
        if(m.find()){
            for(Direction dir: Direction.values()){
                if(m.group(1).equals(dir.name())) {
                    this.directionsMap.put(dir, directionsMap.get(dir)+Integer.parseInt(m.group(2)));
                }
            }
        }
    }


    int run() {
        try {
            populateDirectionsMap();

            return (this.directionsMap.get(Direction.FORWARD) *
                    (this.directionsMap.get(Direction.DOWN) - this.directionsMap.get(Direction.UP))
            );
        }
        catch (Exception e) {
            return 0;
        }
    }



}
