package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLUtils {
    private static String INSERT_RATE = "INSERT INTO rate(datetime,currency1,currency2,course) values(?,?,?,?);";

    public static List<Rate> saveRate(Rate rate) {
        List<Rate> rates = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RATE)) {
             preparedStatement.setTimestamp(1, Timestamp.valueOf(rate.getDateTime()));
             preparedStatement.setString(2,rate.getCurrency1());
             preparedStatement.setString(3,rate.getCurrency2());
             preparedStatement.setDouble(4,rate.getCourse());
             preparedStatement.executeUpdate();
             PreparedStatement allRates = connection.prepareStatement("SELECT * FROM rate");
            ResultSet rs = allRates.executeQuery();
            while (rs.next()){
                Date dateTime = rs.getDate("datetime");
                String cur1 = rs.getString("currency1");
                String cur2 = rs.getString("currency2");
                Double course = rs.getDouble("course");
                rates.add(new Rate(dateTime,cur1,cur2,course));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rates;
    }
    public static List<Rate> getDataRate(String query){
        List<Rate> rates = new ArrayList<>();
        try(Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            String dateTime = rs.getString("datetime");
            String cur1 = rs.getString("currency1");
            String cur2 = rs.getString("currency2");
            Double course = rs.getDouble("course");
            rates.add(new Rate(dateTime,cur1,cur2,course));
        }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rates;
    }
}
