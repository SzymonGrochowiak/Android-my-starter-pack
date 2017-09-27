package com.szymongrochowiak.androidstarterpack.test.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Szymon Grochowiak
 */
public class LocalFileParser {

    public LocalFileParser() {
    }

    public String readJsonFile(String jsonFilePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(jsonFilePath);
        File file = new File(resource.getPath());
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner;
        try {
            scanner = new Scanner(file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String lineSeparator = System.getProperty("line.separator");

        String var8;
        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append(lineSeparator);
            }

            var8 = fileContents.toString();
        } finally {
            scanner.close();
        }

        return var8;
    }
}