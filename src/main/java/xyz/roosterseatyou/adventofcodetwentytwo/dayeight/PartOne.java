package xyz.roosterseatyou.adventofcodetwentytwo.dayeight;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;

public class PartOne {
    public static void main(String[] args) {
        System.out.println(solve(Utils.getFile("src/main/resources/dayEight.txt")));
    }

    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);

        char[][] grid = new char[lines.get(0).length()][lines.size()];
        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(i).length(); j++) {
                grid[j][i] = lines.get(i).charAt(j);
            }
        }
        int visible = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
                if(isVisibleUp(grid, i, j) || isVisibleDown(grid, i, j) || isVisibleLeft(grid, i, j) || isVisibleRight(grid, i, j)) {
                    visible++;
                    System.out.println(visible + " " + i + " " + j);
                }
            }
        }

        return visible;
    }

    public static boolean isVisibleRight(char[][] grid, int x, int y) {
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = x+1; i < grid.length; i++) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[i][y]));
                if(!(val < value)) {
                    return false;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    return true;
                } else {
                    System.out.println("What");
                }
            }
        }
        return true;
    }

    public static boolean isVisibleDown(char[][] grid, int x, int y) {
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = y+1; i < grid[0].length; i++) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[x][i]));
                if(!(val < value)) {
                    return false;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    return true;
                } else {
                    System.out.println("What");
                }
            }
        }
        return true;
    }

    public static boolean isVisibleLeft(char[][] grid, int x, int y) {
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = x-1; i >= 0; i--) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[i][y]));
                if(!(val < value)) {
                    return false;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    return true;
                } else {
                    System.out.println("What");
                }
            }
        }
        return true;
    }

    public static boolean isVisibleUp(char[][] grid, int x, int y) {
        int value = Integer.parseInt(String.valueOf(grid[x][y]));
        for(int i = y-1; i >= 0; i--) {
            try {
                int val = Integer.parseInt(String.valueOf(grid[x][i]));
                if(!(val < value)) {
                    return false;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    return true;
                } else {
                    System.out.println("What");
                }
            }
        }
        return true;
    }
}
