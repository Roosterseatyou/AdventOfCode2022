package xyz.roosterseatyou.adventofcodetwentytwo.dayone;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;

public class PartOne {
    public static int findSum(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        int largestSum = 0;

        for(int i = 0; i < lines.size()-1; i++) {
            int currentSum = 0;
            if(!lines.get(i).equals("")) {
                for(int j = i; j < lines.size()-1; j++) {
                    if(!lines.get(j).equals("")) {
                        currentSum += Integer.parseInt(lines.get(j));
                    } else {
                        i = j;
                        break;
                    }
                }
            } else {
                continue;
            }
            if(currentSum > largestSum) {
                largestSum = currentSum;
            }
        }
        return largestSum;
    }


}
