package ca.terrylockett;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class DeepDarkDepthMeasuring {


    public static void main(String[] args) throws IOException {

        InputStream is = DeepDarkDepthMeasuring.class.getClassLoader().getResourceAsStream("DeepDepthsLog.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        char[] list = new char[0];
        while(true) {
            char tmp;
            if ( (tmp = (char)reader.read()) == (char)-1) {
                break;
            }
            if(tmp == 0xd) {
                continue;
            }
            char[] tmpArr = new char[list.length+1];

            for(int i=0; i< list.length; i++) {
                tmpArr[i] = list[i];
            }
            tmpArr[list.length] = tmp;
            list = tmpArr;
        }
        int numNewLines = 0, count = 0;

        for(int i=0; i< list.length; i++){
            if(list[i] == 0xa) { numNewLines++;}
        }

        char[][] nums = new char[numNewLines][];
        char[] num = new char[0];

        for(var i=0; i < list.length; i++) {
            if(list[i] == 0xa) {

                nums[count] = new char[num.length];

                for(var ass=0; ass<num.length; ass++) {
                    nums[count][ass] = num[ass];
                }

                num = new char[0];
                count++;
                continue;
            }
            char[] tmpArr = new char[num.length+1];

            for(int j=0; j< num.length; j++) {
                tmpArr[j] = num[j];
            }
            tmpArr[num.length] = list[i];
            num = tmpArr;
        }


        int larger = 0, index = 1;
        do {
            char[] previous = nums[index-1];
            char[] modern = nums[index];
            index++;

            if(modern.length > previous.length) {
                larger++;
                continue;
            }
            else {
                for (var i = 0; i < modern.length; i++) {
                    if (modern[i] > previous[i]) {
                        larger++;
                        break;
                    }
                    else if(modern[i] < previous[i]) {
                        break;
                    }
                }
            }


        } while(index < nums.length);

        System.out.println("larger:" + (larger));
    }
}
