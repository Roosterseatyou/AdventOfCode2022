package xyz.roosterseatyou.adventofcodetwentytwo;

import xyz.roosterseatyou.adventofcodetwentytwo.dayone.PartOne;

public class Main {
    public static void main(String[] args) {
        int largestSum = PartOne.findSum(Utils.getFile("src/main/resources/dayOnePtOne.txt"));
        System.out.println(largestSum);
    }
}
