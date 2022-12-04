package xyz.roosterseatyou.adventofcodetwentytwo.daythree;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;

public class PartOne {
    public static void main(String[] args) {
        int sum = solve(Utils.getFile("src/main/resources/dayThree.txt"));
        System.out.println(sum);
    }

    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        int sum = 0;

        for (String line : lines) {
            String[] halves = {line.substring(0, line.length() / 2), line.substring(line.length() / 2)};
            System.out.println(halves[0] + "   |   " + halves[1]);
            String duplicate = checkDuplicateChars(halves[0], halves[1]);
            if (duplicate != null) {
                sum += getPriority(duplicate);
                System.out.println("Found duplicate: " + duplicate + " with priority " + getPriority(duplicate));
                System.out.println("New sum: " + sum);
            } else {
                System.out.println("No duplicate found!!!!!!!!!!!!!!!!");
                return -1;
            }
        }
        return sum;
    }

    public static String checkDuplicateChars(String x, String y) {
        String[] splitX = x.split("");
        String[] splitY = y.split("");
        for (String s : splitX) {
            for (String s1 : splitY) {
                if (s.equals(s1)) return s;
            }
        }
        return null;
    }

    public static int getPriority(String x) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetUpper = alphabet.toUpperCase();

        int index = alphabet.indexOf(x)+1;
        if(index == 0) index = (alphabetUpper.indexOf(x)+1)+26;
        return index;
    }
}
