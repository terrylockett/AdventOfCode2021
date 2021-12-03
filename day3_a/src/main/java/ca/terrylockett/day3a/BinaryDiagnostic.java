package ca.terrylockett.day3a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class BinaryDiagnostic {

    short[] bitTrueCount;
    short totalDiagnosticEntries;

    public long processDiagnosticReport(String fileName) {
        // this wrapper is my odd way to remove catch blocks from processDiagnosticReportImp..
        try {
            return processDiagnosticReportImp(fileName);
        }
        catch(IOException ignored) {
            /*comment to hide empty catch block stank. */
        }

        return 0L;
    }

    private long processDiagnosticReportImp(String fileName) throws IOException {
        this.totalDiagnosticEntries = 0;
        String filePath = this.getFile(fileName);

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            String s = stream.findFirst().orElse(""); //.orElse("") makes sonarqube shut up.
            this.bitTrueCount = new short[(short)s.length()];
        }

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(this::processDiagEntry);
        }

        var gammaArr = new char[this.bitTrueCount.length];

        for(var i=0; i< this.bitTrueCount.length; i++){
            if(this.bitTrueCount[i] > this.totalDiagnosticEntries /2){
                gammaArr[i] = 1;
                continue;
            }
            gammaArr[i] = 0;
        }

        var gamma = binaryToShort(gammaArr);
        var epsilon = inverseBits(gamma, (short)this.bitTrueCount.length);

        return (long) gamma*epsilon;
    }


    private void processDiagEntry(String s) {
        this.totalDiagnosticEntries++;
        char[] c = s.toCharArray();

        for( var i=0; i< s.length(); i++) {
            if(c[i] == 0x31) // hex for 1
                this.bitTrueCount[i]++;
        }
    }

    private short binaryToShort(char[] c) {
        var digitPlace = 1;
        short returnVal = 0;

        for(var i= c.length-1; i>=0; i--){
            returnVal += (c[i] * digitPlace);
            digitPlace *= 2;
        }

        return returnVal;
    }

    private short inverseBits(short number, short bitCount){
        return (short) Math.round( (Math.pow(2,bitCount)) -1 -number);
    }

    private String getFile(String fileName) {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getFile();
    }

}
