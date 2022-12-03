package xyz.roosterseatyou.adventofcodetwentytwo.daytwo;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class PartOne {
    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        int score = 0;
        for(String line : lines) {
            System.out.println(line + " " + score);
            String[] splitLine = line.split(" ");
            if(Objects.equals(selectWinner(splitLine[0], splitLine[1]), "draw")) score += 3 + evaluateChoice(splitLine[1]);
            if(Objects.equals(selectWinner(splitLine[0], splitLine[1]), splitLine[0])) score += evaluateChoice(splitLine[1]);
            if(Objects.equals(selectWinner(splitLine[0], splitLine[1]), splitLine[1])) score += 6 + evaluateChoice(splitLine[1]);
            System.out.println("New score: " + score);
        }
        return score;
    }

    public static int  evaluateChoice(String x) {
        if(Objects.equals(x, "X") || Objects.equals(x, "A")) return 1;
        if(Objects.equals(x, "Y") || Objects.equals(x, "B")) return 2;
        if(Objects.equals(x, "Z") || Objects.equals(x, "C")) return 3;
        return 0;
    }

    public static String selectWinner(String x, String y){
        if(Objects.equals(x, "A")) {
            if(y.equals("X")) return "draw";
            else if(y.equals("Y")) return y;
            else if (y.equals("Z")) return x;
        } else if(Objects.equals(x, "B")) {
            if(y.equals("X")) return x;
            else if(y.equals("Y")) return "draw";
            else if (y.equals("Z")) return y;
        } else if(Objects.equals(x, "C")) {
            if(y.equals("X")) return y;
            else if(y.equals("Y")) return x;
            else if (y.equals("Z")) return "draw";
        }
        return null;
    }
}
