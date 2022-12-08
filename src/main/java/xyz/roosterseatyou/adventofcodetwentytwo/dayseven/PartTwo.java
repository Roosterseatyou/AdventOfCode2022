package xyz.roosterseatyou.adventofcodetwentytwo.dayseven;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PartTwo {
    public static void main(String[] args) {
        System.out.println(solve(Utils.getFile("src/main/resources/daySeven.txt")));
    }

    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        HashMap<String, ArrayList<String>> directories = new HashMap<>();

        String MAIN_DIR = "/";

        String currentDir = "";
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if(line.startsWith("$ ")) {
                String command = line.split(" ")[1];
                if (command.equals("cd")) {
                    //CD COMMAND
                    if(line.split(" ")[2].equals("..")) {
                        if(!currentDir.substring(0, currentDir.lastIndexOf("/")).startsWith("/")) {
                            currentDir = MAIN_DIR;
                        } else {
                            currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"));
                        }
                    } else {
                        if(currentDir.endsWith("/")) {
                            currentDir = currentDir + line.split(" ")[2];
                        } else if (currentDir.equals("")) {
                            currentDir = line.split(" ")[2];
                        } else {
                            currentDir = currentDir + "/" + line.split(" ")[2];
                        }
                    }
                    System.out.println("Current dir: " + currentDir);
                } else if (command.equals("ls")) {
                    //LS COMMAND
                    ArrayList<String> files = new ArrayList<>();
                    for (int j = i + 1; j < lines.size(); j++) {
                        String fileLine = lines.get(j);
                        if (fileLine.startsWith("$ ")) {
                            break;
                        }
                        if(fileLine.startsWith("dir ")) {
                            if(currentDir.endsWith("/")) {
                                directories.put(currentDir + fileLine.split(" ")[1], new ArrayList<>());
                            } else {
                                directories.put(currentDir + "/" + fileLine.split(" ")[1], new ArrayList<>());
                            }
                        }
                        files.add(fileLine);
                    }
                    System.out.println("Current directory " + currentDir + " contains: " + files);
                    if (!currentDir.equals("")) {
                        directories.put(currentDir, files);
                    } else {
                        throw new RuntimeException("No current directory");
                    }
                }
            }
        }

        System.out.println("BEGINNING FILE SIZE CALCULATION");
        HashMap<String, Integer> fileSizes = new HashMap<>();
        //FILE SIZE
        for (Map.Entry<String, ArrayList<String>> entry : directories.entrySet()) {
            String dir = entry.getKey();
            int size = PartOne.getDirectorySize(dir, directories);

            fileSizes = addDirSize(fileSizes, dir, directories);
            PartOne.printHashMap(fileSizes);
        }

        int targetSize = getHighestDirSize(fileSizes) - (70000000 - 30000000);

        System.out.println("Target value: " + targetSize);

        return findClosestDirSizeGreaterThan(fileSizes, targetSize);
    }

    public static HashMap<String, Integer> addDirSize(HashMap<String, Integer> map, String dir, HashMap<String, ArrayList<String>> directories) {
        int size = PartOne.getDirectorySize(dir, directories);
        map.put(dir, size);
        map = sortHashMap(map);
        return map;
    }

    public static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> map) {
        HashMap<String, Integer> sortedMap = new HashMap<>();
        ArrayList<Integer> values = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            values.add(entry.getValue());
        }
        values.sort(Integer::compareTo);
        for (Integer value : values) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if(Objects.equals(entry.getValue(), value)) {
                    sortedMap.put(entry.getKey(), entry.getValue());
                    System.out.println("Added " + entry.getKey() + " with value " + entry.getValue());
                }
            }
        }
        return sortedMap;
    }

    public static int getHighestDirSize(HashMap<String, Integer> map) {
        int highest = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > highest) {
                highest = entry.getValue();
            }
        }
        return highest;
    }

    public static int findClosestDirSizeGreaterThan(HashMap<String, Integer> map, int target) {
        int closest = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > target) {
                if(closest == 0) {
                    closest = entry.getValue();
                } else if (entry.getValue() < closest) {
                    closest = entry.getValue();
                }
            }
        }
        return closest;
    }
}
