package ca.terrylockett.day2a;

public class DeepDarkNavigatorRunner {

    public static void main(String args[]) {
        DeepDarkNavigator navigator  =
                new DeepDarkNavigator("DeepDarkDirections.txt");

        int result = navigator.run();
        System.out.println("result: " + result);
    }
}
