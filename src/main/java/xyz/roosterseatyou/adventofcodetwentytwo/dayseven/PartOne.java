package xyz.roosterseatyou.adventofcodetwentytwo.dayseven;

import xyz.roosterseatyou.adventofcodetwentytwo.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartOne {
    public static void main(String[] args) {
        System.out.println(solve(Utils.getFile("src/main/resources/daySeven.txt")));
    }

    public static int solve(File file) {
        ArrayList<String> lines = Utils.readFile(file);
        Map<String, ArrayList<String>> directories = new HashMap<>();

        String MAIN_DIR = "/";

        String currentDir = "";
        int totalFileSize = 0;
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
        printHashMap(directories);

        ArrayList<String> lowerDirs = new ArrayList<>();
        //FILE SIZE
        for (Map.Entry<String, ArrayList<String>> entry : directories.entrySet()) {
            String dir = entry.getKey();
            ArrayList<String> files = entry.getValue();
            int size = getDirectorySize(dir, directories);
            System.out.println("Directory " + dir + " contains " + size + " bytes");
            if(size <= 100000) {
                lowerDirs.add(dir);
            }
        }

        int lowerDirSize = 0;
        for (String lowerDir : lowerDirs) {
            lowerDirSize += getDirectorySize(lowerDir, directories);
        }
        return lowerDirSize;
    }

    public static void printHashMap(Map map) {
        for(Object key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }

    public static boolean isInMainDir(String dir) {
        return dir.startsWith("/") && dir.indexOf("/") == dir.lastIndexOf("/");
    }

    public static int getDirectorySize(String dir, Map<String, ArrayList<String>> directories) {
        int dirSize = 0;
        ArrayList<String> files = directories.get(dir);
        for (String fileLine : files) {
            if (!fileLine.startsWith("dir ")) {
                int fileSize = Integer.parseInt(fileLine.split(" ")[0]);
                dirSize += fileSize;
            } else {
                String subDir = fileLine.split(" ")[1];
                if(dir.endsWith("/")) {
                    dirSize += getDirectorySize(dir + subDir, directories);
                } else {
                    dirSize += getDirectorySize(dir + "/" + subDir, directories);
                }
            }
        }
        return dirSize;
    }
}
