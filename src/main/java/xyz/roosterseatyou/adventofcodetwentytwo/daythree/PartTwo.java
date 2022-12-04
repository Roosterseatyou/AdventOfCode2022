package xyz.roosterseatyou.adventofcodetwentytwo.daythree;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class PartTwo {
    public static void main(String[] args) {
        int score = solve(Utils.getFile("src/main/resources/dayThree.txt"));
        System.out.println(score);
    }

    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        ArrayList<String[]> groups = splitIntoGroups(lines);
        int sum = 0;
        for(String[] group : groups) {
            String duplicate = findDuplicateCharacter(group);
            sum += getPriority(duplicate);
            System.out.println("Group" + Arrays.toString(group) + ", found duplicate: " + duplicate + " with priority " + getPriority(duplicate));
            System.out.println("New sum: " + sum);
        }
        return sum;
    }

    public static ArrayList<String[]> splitIntoGroups(ArrayList<String> lines) {
        ArrayList<String[]> groups = new ArrayList<>();
        System.out.println(lines.size());
        for(int i = 0; i < lines.size(); i++) {
            String[] currentGroup = new String[3];
            for(int j = 0; j < 3; j++) {
                System.out.println("i: " + i + ", j: " + j);
                currentGroup[j] = lines.get(i+j);
            }
            i += 2;
            groups.add(currentGroup);
            System.out.println("Added group: " + Arrays.toString(currentGroup));
        }
        return groups;
    }

    public static String findDuplicateCharacter(String[] lines) {
        String duplicate = null;
        for(int i = 0; i < lines[0].length(); i++) {
            for(int j = 0; j < lines[1].length(); j++) {
                for(int k = 0; k < lines[2].length(); k++) {
                    if (lines[0].charAt(i) == lines[1].charAt(j) && lines[1].charAt(j) == lines[2].charAt(k)) {
                        duplicate = String.valueOf(lines[0].charAt(i));
                        break;
                    }
                }
            }
        }
        return duplicate;
    }

    public static int getPriority(String x) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetUpper = alphabet.toUpperCase();

        int index = alphabet.indexOf(x)+1;
        if(index == 0) index = (alphabetUpper.indexOf(x)+1)+26;
        return index;
    }
}
