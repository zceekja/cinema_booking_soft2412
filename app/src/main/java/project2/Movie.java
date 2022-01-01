package project2;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;
public class Movie {
    public static final String CREDENTIALS_STRING = "jdbc:mysql://tux-database.cmxvkfohfzm4.ap-southeast-2.rds.amazonaws.com:3306/a2?user=admin&password=password";
    public String title;
    public int mid;

    public Movie(String title, int mid){
        this.title = title;
        this.mid = mid; 
    }
    
}
    
        
