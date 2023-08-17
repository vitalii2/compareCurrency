package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Main {
    private final static String currency1 = "USD";
    private final static String currency2 = "CAD";
    private final static String separator = "&to=";

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                System.out.println(APIRequest.getCurrencyData(currency1 + separator + currency2));
                LocalDateTime localDateTime = LocalDateTime.now();
//                System.out.println(templateForRegEX.getRate(APIRequest.getCurrencyData(currency1 + separator + currency2)));
                Rate rate = new Rate();
                rate.setDateTime(localDateTime);
                rate.setCurrency1(currency1);
                rate.setCurrency2(currency2);
                rate.setCourse(templateForRegEX.getRate(APIRequest.getCurrencyData(currency1 + separator + currency2)));
//                SQLUtils.saveRate(rate);
                List<Rate> rates = SQLUtils.getDataRate("SELECT * FROM rate");
//                System.out.println(rates);
//                actionCSV.toCSV(localDateTime, currency1, currency2, rate.getCourse());
//                actionCSV.fromDBtoCSV(SQLUtils.getDataRate("SELECT * FROM rate"));
//                actionCSV.fromCSVtoDB("rateFromDB.csv");
//                System.out.println(SeekingFile.seek("rateFromDB.csv"));
//                  PDFreport.createReport(rates.toString());
                rates = rates.stream().filter(e -> e.getCourse() > 3.75).collect(Collectors.toList());
//                System.out.println(rates);
//              actionJSON.actionToJSON(rate);
             actionJSON.FromJSONTOClass("Rates.json");
            }
        };
        timer.schedule(timerTask, 0, 6 * 60 * 60 * 1000);
    }
}