package org.example;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class actionCSV {
    private static String INSER_RATE = "INSERT INTO rate(datetime,currency1,currency2,course) VALUES(?,?,?,?)";
    public static void toCSV(LocalDateTime dateTime, String cur1, String cur2, Double rate){
        File file = new File("rates.csv");
        while (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try(PrintWriter pw = new PrintWriter(file);) {
            pw.println("Date and time: " + dateTime + ", Currency 1 : " + cur1 + ", Currency 2 : " + cur2 + ", Rate: " + rate);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void fromDBtoCSV(List<Rate> r){
        File file = new File("rateFromDB.csv");
        while (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try(PrintWriter pw = new PrintWriter(file);) {
            pw.println(r);
            }catch (FileNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void fromCSVtoDB(String s){
        List<Rate> rates = new ArrayList<>();
        Date date;
        File file = new File(s);
        LocalDateTime localDateTime = null;
        String cur1 = null;
        String cur2 = null;
        Double course = 0.0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String wholeLine = "";
            String line;
            while ((line = br.readLine()) != null){
                wholeLine += line;
            }
            Pattern p = Pattern.compile("dateTime=[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}");
            Matcher m = p.matcher(wholeLine);
            Pattern p2 = Pattern.compile("currency1=\\'[a-zA-Z]{1,}\\'");
            Matcher m2 = p2.matcher(wholeLine);
            Pattern p3 = Pattern.compile("currency2=\\'[a-zA-Z]{1,}\\'");
            Matcher m3 = p3.matcher(wholeLine);
            Pattern p4 = Pattern.compile("course=\\d{1,}.\\d{1,}");
            Matcher m4 = p4.matcher(wholeLine);
            while (m.find() && m2.find() && m3.find() && m4.find()){
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                localDateTime = LocalDateTime.parse(m.group().split("=")[1], dateTimeFormatter);
                cur1 = m2.group().split("'")[1];
                cur2 = m3.group().split("'")[1];
                course = Double.parseDouble(m4.group().split("=")[1]);
                try(Connection connection = DBUtils.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(INSER_RATE);) {
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(localDateTime));
                    preparedStatement.setString(2,cur1);
                    preparedStatement.setString(3,cur2);
                    preparedStatement.setDouble(4,course);
                    preparedStatement.executeUpdate();
                    PreparedStatement allStatement = connection.prepareStatement("SELECT * FROM rate");
                    ResultSet rs = allStatement.executeQuery();
                    while (rs.next()){
                        date = rs.getDate("datetime");
                        String c1 = rs.getString("currency1");
                        String c2 = rs.getString("CURRENCY2");
                        Double cour = rs.getDouble("course");
                        rates.add(new Rate(date,c1,c2,cour));
                    }
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
