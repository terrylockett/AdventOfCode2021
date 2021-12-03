package ca.terrylockett.day3a;

public class BinaryDiagnosticRunner {

    private static final String FILE_NAME = "BinaryDiagnosticReport.log";

    public static void main(String[] args) {

        var binaryDiag = new BinaryDiagnostic();

        System.out.println("POWER: " + binaryDiag.processDiagnosticReport(FILE_NAME));
    }

}
