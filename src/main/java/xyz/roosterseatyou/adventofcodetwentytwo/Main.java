package xyz.roosterseatyou.adventofcodetwentytwo;

import xyz.roosterseatyou.adventofcodetwentytwo.dayone.PartOne;
import xyz.roosterseatyou.adventofcodetwentytwo.dayone.PartTwo;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int score = xyz.roosterseatyou.adventofcodetwentytwo.daytwo.PartTwo.solve(Utils.getFile("src/main/resources/daytwo.txt"));
        System.out.println(score);
    }
}
