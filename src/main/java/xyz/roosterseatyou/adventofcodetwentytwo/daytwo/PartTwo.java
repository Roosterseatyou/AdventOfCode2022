package xyz.roosterseatyou.adventofcodetwentytwo.daytwo;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class PartTwo {
    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);

        int score = 0;
        for (String line : lines) {
            String[] splitLine = line.split(" ");

            String choice = selectChoice(splitLine[0], splitLine[1]);
            if(PartOne.selectWinner(splitLine[0], choice).equals(choice)) score += 6 + PartOne.evaluateChoice(choice);
            else if(PartOne.selectWinner(splitLine[0], choice).equals("draw")) score += 3 + PartOne.evaluateChoice(choice);
            else score += PartOne.evaluateChoice(choice);
        }
        return score;
    }

    public static String selectChoice(String x, String y) {
        if(Objects.equals(x, "A")) {
            if(Objects.equals(y, "X")) return "Z";
            if(Objects.equals(y, "Y")) return "X";
            if(Objects.equals(y, "Z")) return "Y";
        } else if(Objects.equals(x, "B")) {
            if(Objects.equals(y, "X")) return "X";
            if(Objects.equals(y, "Y")) return "Y";
            if(Objects.equals(y, "Z")) return "Z";
        } else if(Objects.equals(x, "C")) {
            if(Objects.equals(y, "X")) return "Y";
            if(Objects.equals(y, "Y")) return "Z";
            if(Objects.equals(y, "Z")) return "X";
        }
        return null;
    }
}
