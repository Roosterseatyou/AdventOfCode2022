package xyz.roosterseatyou.adventofcodetwentytwo.dayfive;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class PartTwo {
    public static void main(String[] args) {
        System.out.println(solve(Utils.getFile("src/main/resources/dayFive.txt")));
    }

    public static String solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        ArrayList<Character>[] cargoGrid = getCargoGrid(lines, 8);

        for(int i = 10; i < lines.size(); i++) {
            System.out.println("i: " + i);
            processInstruction(cargoGrid, lines.get(i));
        }
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Character> characters : cargoGrid) {
            sb.append(characters.get(characters.size() - 1));
        }
        return sb.toString();
    }

    public static void processInstruction(ArrayList<Character>[] cargoGrid, String instruction) {
        String[] instructionParts = instruction.split(" ");
        int amt;
        int from;
        int to;

        System.out.println("----------------------");
        System.out.println("Instruction: " + instruction);
        try {
            amt = Integer.parseInt(instructionParts[1]);
            from = Integer.parseInt(instructionParts[3]);
            to = Integer.parseInt(instructionParts[5]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid instruction: " + instruction);
            return;
        }
        System.out.println("FROM_LINE: " + cargoGrid[from-1]);
        System.out.println("TO_LINE: " + cargoGrid[to-1]);

        char[] cargoMoved = new char[amt];
        for(int i = 0; i < amt; i++) {
            cargoMoved[i] = cargoGrid[from-1].get(cargoGrid[from-1].size()-amt+i);
        }

        for(int i = 0; i < amt; i++) {
            cargoGrid[from-1].remove(cargoGrid[from-1].size()-1);
        }

        for(int i = 0; i < amt; i++) {
            cargoGrid[to-1].add(cargoMoved[i]);
        }

        System.out.println("CARGO MOVED: " + Arrays.toString(cargoMoved));
        System.out.println("Moved " + amt + " cargo from " + from + " to " + to);
        System.out.println("FROM_LINE: " + cargoGrid[from-1]);
        System.out.println("TO_LINE: " + cargoGrid[to-1]);
        System.out.println("----------------------");
    }

    public static ArrayList<Character>[] getCargoGrid(ArrayList<String> lines, int widthLineIndex) {
        String widthLine = lines.get(widthLineIndex);
        int width = 0;
        for(int i = 1; i < widthLine.length(); i += 4) {
            width++;
        }
        ArrayList<Character>[] cargoGrids = new ArrayList[width];
        for(int i = 0; i < cargoGrids.length; i++) {
            cargoGrids[i] = new ArrayList<>();
        }
        for(int i = 0; i < widthLineIndex; i++) {
            String line = lines.get(widthLineIndex-1-i);
            int index = 0;
            for(int j = 1; j < line.length(); j += 4) {
                if(line.charAt(j) != ' ') {
                    cargoGrids[index].add(line.charAt(j));
                }
                index++;
            }
        }

        for (ArrayList<Character> characters : cargoGrids) {
            System.out.println(characters);
        }

        return cargoGrids;
    }
}
