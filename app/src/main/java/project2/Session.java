package project2;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.util.*;
public class Session {
    public static final String CREDENTIALS_STRING = "jdbc:mysql://tux-database.cmxvkfohfzm4.ap-southeast-2.rds.amazonaws.com:3306/a2?user=admin&password=password";
    public int sid;
    public int mid;
    public int cid;
    public Timestamp movieTime;
    public int max_front_seat;
    public int max_middle_seat;
    public int max_back_seat;
    public int booked_front_seat = 0;
    public int booked_middle_seat = 0;
    public int booked_back_seat = 0;

    public String screen_type;

    public Session(int sid, int mid, int cid, String screen_type ,Timestamp movieTime, int booked_front, int booked_middle, int booked_back){
        this.screen_type = screen_type;
        this.mid = mid;
        this.cid = cid; 
        this.sid = sid;
        insertSeatByScreenType(screen_type);
        this.movieTime = movieTime;
        booked_front_seat = booked_front;
        booked_middle_seat = booked_middle;
        booked_back_seat = booked_back;

    }
    public void insertSeatByScreenType(String screen_type){
        if (screen_type.equals("gold")){
            max_front_seat = 100;
            max_middle_seat = 150;
            max_back_seat = 100;
        }
        if (screen_type.equals("silver")){
            max_front_seat = 60;
            max_middle_seat = 100;
            max_back_seat = 60;    
        }
        if (screen_type.equals("bronze")){
            max_front_seat = 5;
            max_middle_seat = 30;
            max_back_seat = 20;    
        }
    }  
}