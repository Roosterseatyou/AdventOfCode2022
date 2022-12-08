package xyz.roosterseatyou.adventofcodetwentytwo.daysix;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;

public class PartOne {
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
            for (int j = 0; j < 4; j++) {
                group.append(combined.charAt(i + j));
            }
            countedChars = i + 4;
            if (!hasDuplicates(group.toString())) {
                break;
            }

        }
        return countedChars;
    }

    public static int test(String s) {
        int countedChars = 0;
        for(int i = 0; i < s.length(); i++) {
            StringBuilder group = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                group.append(s.charAt(i + j));
            }
            countedChars = i + 4;
            System.out.println(group.toString());
            if (!hasDuplicates(group.toString())) {
                break;
            }

        }
        return countedChars;
    }

    public static boolean hasDuplicates(String s) {
        for(int i = 0; i < s.length(); i++) {
            for(int j = i+1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
