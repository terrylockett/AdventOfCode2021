package ca.terrylockett.day1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeepDepthsFloorAverager {

    /**
     *
     * @param args - ARRRRRGG ima pirate
     * @throws IOException
     *
     * the goal of this bitch is to take in the GIANT list of numbers from DeepDepthsLog.txt
     * and then do shit...
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                DeepDepthsFloorAverager.class.getClassLoader()
                                        .getResourceAsStream("DeepDepthsLog.txt")));

        int result = 0;
        int[] sums = new int[3];
        int previousValue = 0;

        sums[0] += Integer.parseInt(reader.readLine());
        sums[1] += Integer.parseInt(reader.readLine());
        sums[0] += sums[1];

        var idx = 3;
        String line = null;
        while( null != (line = reader.readLine()) ) {

            for(var i=0; i<sums.length; i++){
                sums[i] += Integer.parseInt(line);
            }

            if(sums[idx%3] > previousValue && previousValue != 0)
                result++;
            previousValue = sums[idx%3];
            sums[idx%3] = 0;

            idx++;
        }

        System.out.println("results: "+ result);


    }



}
