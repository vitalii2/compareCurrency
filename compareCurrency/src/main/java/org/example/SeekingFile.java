package org.example;

import java.io.File;

public class SeekingFile {
    public static String seek(String s){
        String path = "D:\\java\\IJ SQL\\compareCurrency";
        File folder = new File(path);
        File[] file = folder.listFiles();
        for (File files: file) {
            if (files.getName().equals(s)){
                return files.getPath();
            }
        }
        return null;
    }
}
