package xyz.roosterseatyou.adventofcodetwentytwo.dayone;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;

public class PartTwo {
    public static int[] findTopThree(File file) {
        int[] topThree = new int[3];
        ArrayList<String> lines = Utils.readFile(file);
        int largestSum = 0;
        int secondLargestSum = 0;
        int thirdLargestSum = 0;

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
                thirdLargestSum = secondLargestSum;
                secondLargestSum = largestSum;
                largestSum = currentSum;
            } else if(currentSum > secondLargestSum) {
                thirdLargestSum = secondLargestSum;
                secondLargestSum = currentSum;
            } else if(currentSum > thirdLargestSum) {
                thirdLargestSum = currentSum;
            }
        }
        topThree[0] = largestSum;
        topThree[1] = secondLargestSum;
        topThree[2] = thirdLargestSum;
        return topThree;
    }
}
