package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class templateForRegEX {
public static Double getRate(String s){
    Double rate = 0.0;
    Pattern p = Pattern.compile("\"result\":\\d{1,}\\D\\d{1,}");
    Matcher m = p.matcher(s);
    while (m.find()){
        rate = Double.parseDouble(m.group().split(":")[1]);
    }
    return rate;
}
}
