package com.trading;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresqlExample {
    public static void main(String[] args) throws ClassNotFoundException {
        try (final Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://pg-5851406-trading-algo.e.aivencloud.com:16435/cryptodb?ssl=require&user=avnadmin&password=AVNS_l9364VVgZPQExnp2bhV");
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("select * from test_table")) {

        while (resultSet.next()) {
            System.out.println("Version: " + resultSet.getString("id"));
        }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}