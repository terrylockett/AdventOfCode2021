package ca.terrylockett.day2b;

public class DeepDarkNavigatorRunner {

    private static final String FILE_NAME= "DeepDarkDirections.txt";
    private static final String RESULTS_MSG_HEADER = "result: ";

    public static void main(String[] args) {
        var navigator = new DeepDarkNavigator(FILE_NAME);

        System.out.println(RESULTS_MSG_HEADER + navigator.run());
    }
}
