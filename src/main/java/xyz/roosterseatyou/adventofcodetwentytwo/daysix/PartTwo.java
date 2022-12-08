package xyz.roosterseatyou.adventofcodetwentytwo.daysix;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;

public class PartTwo {
    public static void main(String[] args) {
        System.out.println(solve(Utils.getFile("src/main/resources/daySix.txt")));
    }

    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        StringBuilder combined = new StringBuilder();
        for (String line : lines) {
            combined.append(line);
        }

        int countedChars = 0;
        for(int i = 0; i < combined.length(); i++) {
            StringBuilder group = new StringBuilder();
            for (int j = 0; j < 14; j++) {
                group.append(combined.charAt(i + j));
            }
            countedChars = i + 14;
            if (!PartOne.hasDuplicates(group.toString())) {
                break;
            }

        }
        return countedChars;
    }
}
