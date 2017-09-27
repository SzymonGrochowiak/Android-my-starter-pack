package com.szymongrochowiak.androidstarterpack.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Szymon Grochowiak
 */
public class AssetsFileParser {

    public static final String UTF_8 = "UTF-8";

    private Context testContext;

    public AssetsFileParser(Context testContext) {
        this.testContext = testContext;
    }

    public String readJsonFile(String jsonFilePath) throws Exception {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(testContext.getAssets().open(jsonFilePath), UTF_8));
            String line;
            StringBuilder text = new StringBuilder();

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
            return text.toString();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}