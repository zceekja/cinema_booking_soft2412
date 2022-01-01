package project2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

public class User {
    public static final String CREDENTIALS_STRING = "jdbc:mysql://tux-database.cmxvkfohfzm4.ap-southeast-2.rds.amazonaws.com:3306/a2?user=admin&password=password";
    public Session user_session_selection; //Idk why these were added here, but I'll leave it
    public Cinema user_cinema_selection;
    public Movie user_movie_selection;
    public String name;
    public double balance;
    public List cards;

    public User(String name) throws IOException, ParseException {
        this.name = name;
        this.balance = 0.00;

        JSONParser parser = new JSONParser();
        this.cards = (ArrayList) parser.parse(new FileReader("credit_cards.json"));

    }

    public int redeemGiftCard(String code) { //all return 13 b/c it's easier for testcases
        try {
            Connection conn = DriverManager.getConnection(CREDENTIALS_STRING);
            PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM Gift_card WHERE serial_no = ?");
            preStatement.setString(1, code);
            ResultSet resultSet = preStatement.executeQuery();
            if (resultSet.next() == false) {
                System.out.println("Gift card code not valid");
                return 13;

            } else if (resultSet.getBoolean("status")) {
                System.out.println("Gift card already redeemed");
                return 13;

            } else {
                try {
                    Connection conne = DriverManager.getConnection(CREDENTIALS_STRING);
                    PreparedStatement update = conne.prepareStatement("UPDATE Gift_card SET status = 1 WHERE serial_no =?");
                    update.setString(1, code);
                    update.executeUpdate();
                    this.balance += resultSet.getDouble("value");
                    System.out.printf("$%.2f added to account balance!\n", resultSet.getDouble("value"));
                    return 13;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 13;
    }

    public boolean payWithBalance(double price) {
        if (App.price == 0) {
            System.out.println("Basket is empty");
            return false;
        } else if (this.balance == 0) {
            System.out.println("No funds available in balance");
            return false;
        } else if (this.balance - price < 0) {
            App.price -= this.balance;
            this.balance = 0;
            System.out.println("Please pay the remainder amount");
            return false;
        } else {
            this.balance -= price;
            App.price = 0;
            System.out.println("Payment success");
            return true;
        }
    }

    public boolean payWithCreditCard(String name, String number) {
        for (int i = 0; i < this.cards.size(); i++) {
            if (((JSONObject) this.cards.get(i)).get("name").equals(name) && ((JSONObject) this.cards.get(i)).get("number").equals(number)) {
                App.price = 0;
                System.out.println("Payment success");
                return true;
            }
        }
        System.out.println("Credit Card invalid");
        return false;
    }

    public boolean payWithSavedCard() {
        try {
            Connection conn = DriverManager.getConnection(CREDENTIALS_STRING);
            PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM User_account WHERE name = ?");
            preStatement.setString(1, this.name);
            ResultSet resultSet = preStatement.executeQuery();
            resultSet.next();

            if (resultSet.getString("cardno").equals("no record")) {
                System.out.println("No card saved to account");
                return false;
            } else {
                App.price = 0;
                System.out.println("Payment success");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
