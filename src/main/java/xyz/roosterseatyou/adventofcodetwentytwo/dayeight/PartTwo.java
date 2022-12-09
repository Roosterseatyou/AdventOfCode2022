package xyz.roosterseatyou.adventofcodetwentytwo.dayeight;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartTwo {
    public static void main(String[] args) {
        System.out.println(solve(Utils.getFile("src/main/resources/dayEight.txt")));
    }

    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        HashMap<int[], Integer> map = new HashMap<>();
        char[][] grid = new char[lines.get(0).length()][lines.size()];
        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(i).length(); j++) {
                grid[j][i] = lines.get(i).charAt(j);
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                int[] coords = {i, j};
                int scenicScore = getBlockedAtUp(grid, i, j) * getBlockedAtLeft(grid, i, j) * getBlockedAtDown(grid, i, j) * getBlockedAtRight(grid, i, j);
                map.put(coords, scenicScore);
                System.out.println(scenicScore + " " + i + " " + j);
            }
        }
        return highest(map);
    }

    public static int getBlockedAtLeft(char[][] grid, int x, int y) {
        int treesUntilBlocked = 0;
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = x-1; i >= 0; i--) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[i][y]));
                if(!(val < value)) {
                    treesUntilBlocked++;
                    System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing left");
                    return treesUntilBlocked;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing left");
                    return treesUntilBlocked;
                } else {
                    System.out.println("What");
                }
            }
            treesUntilBlocked++;
        }
        System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing left");
        return treesUntilBlocked;
    }

    public static int getBlockedAtRight(char[][] grid, int x, int y) {
        int treesUntilBlocked = 0;
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = x+1; i < grid.length; i++) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[i][y]));
                if(!(val < value)) {
                    treesUntilBlocked++;
                    System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing right");
                    return treesUntilBlocked;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    return treesUntilBlocked;
                } else {
                    System.out.println("What");
                }
            }
            treesUntilBlocked++;
        }
        System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing right");
        return treesUntilBlocked;
    }

    public static int getBlockedAtUp(char[][] grid, int x, int y) {
        int treesUntilBlocked = 0;
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = y-1; i >= 0; i--) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[x][i]));
                if(!(val < value)) {
                    treesUntilBlocked++;
                    System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing up");
                    return treesUntilBlocked;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing up");
                    return treesUntilBlocked;
                } else {
                    System.out.println("What");
                }
            }
            treesUntilBlocked++;
        }
        System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing up");
        return treesUntilBlocked;
    }

    public static int getBlockedAtDown(char[][] grid, int x, int y) {
        int treesUntilBlocked = 0;
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = y+1; i < grid[0].length; i++) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[x][i]));
                if(!(val < value)) {
                    treesUntilBlocked++;
                    System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing down");
                    return treesUntilBlocked;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing down");
                    return treesUntilBlocked;
                } else {
                    System.out.println("What");
                }
            }
            treesUntilBlocked++;
        }
        System.out.println("Coord " + x + " " + y + " is blocked by  " + treesUntilBlocked + " trees facing down");
        return treesUntilBlocked;
    }

    public static int highest(Map map) {
        int highest = 0;
        for(Object o : map.values()) {
            if((int) o > highest) {
                highest = (int) o;
            }
        }
        return highest;
    }
}
