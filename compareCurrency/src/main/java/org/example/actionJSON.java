package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class actionJSON {
    public static void actionToJSON(Rate rate){
        ObjectMapper om = new ObjectMapper();
        try {
            File file = new File("Rates.json");
            String res = om.writeValueAsString(rate);
            PrintWriter pw = new PrintWriter(file);
            pw.println(res);
            pw.close();
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void FromJSONTOClass(String s){
        File file = new File(s);
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            String wholeLine = "";
            while ((line = in.readLine()) != null){
               wholeLine += line;
            }
            ObjectMapper mapper = new ObjectMapper();
            try {
                Rate rate = mapper.readValue(wholeLine,Rate.class);
                System.out.println(rate.toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
