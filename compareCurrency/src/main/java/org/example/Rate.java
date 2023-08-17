package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Rate implements Comparable<Rate>{
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTime;
    private String currency1;
    private String currency2;
    private Double course;

    public Rate() {}
    public Rate(LocalDateTime date, String currency1, String currency2, Double course) {
        this.dateTime = date;
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.course = course;
    }
    public Rate(String date, String currency1, String currency2, Double course){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = LocalDateTime.parse(date,dateTimeFormatter);
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.course = course;
    }

    public Rate(Date dateTime, String cur1, String cur2, Double course) {

    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getCurrency1() {
        return currency1;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public Double getCourse() {
        return course;
    }

    public void setCourse(Double course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "dateTime=" + dateTime +
                ", currency1='" + currency1 + '\'' +
                ", currency2='" + currency2 + '\'' +
                ", course=" + course +
                "}\n";
    }

    @Override
    public int compareTo(Rate o) {
        if (this.course > o.course){
            return 1;
        } else if (this.course < o.course) {
            return -1;
        }
        else
            return 0;
    }
}
