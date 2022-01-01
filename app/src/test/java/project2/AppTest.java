/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project2;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.*;

class AppTest {
    @Test 
    void getInterfaceTest() {
        App a = new App();
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,a.getInterface());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(2,a.getInterface());
        data = "-1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(-1,a.getInterface());
    }
    @Test
    void bookTest(){
        App a = new App();
        Session session = new Session(1,1,123, "gold", new Timestamp(12),0,0,0);
        a.cinemas = new ArrayList<Cinema>();
        a.cinemas.add(new Cinema("Broadway", 123));
        a.movies = new ArrayList<Movie>();
        a.movies.add(new Movie("No Time To Die", 123));
        a.sessions = new ArrayList<Session>();
        a.sessions.add(session);
        a.user_cinema_selection=a.cinemas.get(0);
        a.login_status =1;
        a.user_session_selection = a.sessions.get(0);
        a.update_available_movies();
        a.user_movie_selection = a.movies.get(0);
        String data = "y";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.book());
        data = "q";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(8,a.book());

    }
    @Test
    void bookingPageTest(){
        App a = new App();
        a.seatBasket.add("Middle"); 
        a.age.add("Child");
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(11, a.bookingPage());
        data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.removeSeat());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(12, a.bookingPage());
        data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(13, a.bookingPage());
        data = "q";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(9, a.bookingPage());
        data = "e";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.bookingPage());
    }
    @Test
    void removeSeatTest(){
        App a = new App();
        a.seatBasket.add("Middle"); 
        a.age.add("Child");
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.removeSeat());
        a.seatBasket.add("Middle"); 
        a.age.add("Student");
        data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.removeSeat());
        a.seatBasket.add("Middle"); 
        a.age.add("Adult");
        data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.removeSeat());
    }
    @Test
    void seatPagetest(){
        App a = new App();
        Session session = new Session(1,1,123, "gold", new Timestamp(12),0,0,0);
        a.user_session_selection = session;
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(14, a.seatPage());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(14, a.seatPage());
        data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(14, a.seatPage());
        data = "q";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.seatPage());



    } 
    @Test
    void agePageTest(){
        App a = new App();
        Session session = new Session(1,1,123, "gold", new Timestamp(12),0,0,0);
        a.user_session_selection = session;
        a.lastSelectedSeat = "front"; 
        a.seatBasket.add("nihao");
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        a.lastSelectedSeat = "middle";
        data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        a.lastSelectedSeat = "back";
        data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.agePage());
        data = "q";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(11, a.agePage());



    } 
    @Test
    void employeeLogInAndInterfaceTest(){
        App a = new App();
        String data = "admin\n123456"; 
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,a.employeeLogIn(true));
        data = "admin\n1"; 
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(2,a.employeeLogIn(true));
        data = "carl1\n123456"; 
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,a.employeeLogIn(true));
    }
    @Test
    void customerInterfaceSelectionTest(){
        App a = new App();
        String data = "1"; 
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,a.customerInterfaceSelection(1));
        data = "2"; 
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(2,a.customerInterfaceSelection(2));
    }

    @Test
    void customerSelectTest() {
        App a = new App();
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(5,a.customerSelect());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(3,a.customerSelect());
        data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(4,a.customerSelect());
        data = "a";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,a.customerSelect());
    }


    @Test
    void customerLoginTest() {
        App a = new App();
        String data = "charles1\n123456";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(5,a.customerLogIn(true));
        data = "charles1\n1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(3,a.customerLogIn(true));
    }

    @Test
    void customerRegisterTest() {
        App a = new App();
        String data = "charles1\nCharles\n123456";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(4,a.customerRegister());

        data = "delete\nDelete\n123456";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(5,a.customerRegister());

        try {
            Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
            String delete = "delete";
            PreparedStatement state = conn.prepareStatement("DELETE FROM User_account WHERE uid = ?");
            state.setString(1, delete);
            state.executeUpdate();
        }
        catch (Exception e){

        }
    }

    @Test
    void getMoviesTest() {
        App a = new App();
        List<Movie> testlist = new ArrayList<>();
        testlist = a.getMovies();
        try {
            Connection con = DriverManager.getConnection(a.CREDENTIALS_STRING);
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(mid) as total_movie FROM Movies");
            ResultSet rs = statement.executeQuery();
            rs.next();
            assertEquals(rs.getInt("total_movie"), testlist.size());
        }
        catch (Exception e){
        }
    }

    @Test
    void getCinemasTest() {
        App a = new App();
        List<Cinema> testlist = new ArrayList<>();
        testlist = a.getCinemas();
        try {
            Connection con = DriverManager.getConnection(a.CREDENTIALS_STRING);
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(cid) as total_cinema FROM Cinema");
            ResultSet rs = statement.executeQuery();
            rs.next();
            assertEquals(rs.getInt("total_cinema"), testlist.size());
        }
        catch (Exception e){
        }
    }


    @Test
    void getSessionsTest() {
        App a = new App();
        List<Session> testlist = new ArrayList<>();
        testlist = a.getSessions();
        try {
            Connection con = DriverManager.getConnection(a.CREDENTIALS_STRING);
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(cid) as total_session FROM Current_week");
            ResultSet rs = statement.executeQuery();
            rs.next();
            assertEquals(rs.getInt("total_session"), testlist.size());
        }
        catch (Exception e){
        }
    }

    @Test
    void viewDetailTest(){
        App a = new App();
        a.movies = a.getMovies();
        a.cinemas = a.getCinemas();
        a.user_movie_selection=a.movies.get(0);

        String data = "q\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(6,a.viewDetail());

    }

    @Test
    void selectCinemaTest(){
        App a = new App();
        a.cinemas = a.getCinemas();
        String data = "1\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(6,a.selectCinema());
        data = "1000\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(5,a.selectCinema());
    }

    @Test
    void selectMovieTest(){
        App a = new App();
        a.cinemas = a.getCinemas();
        a.movies = a.getMovies();
        a.sessions = a.getSessions();
        a.user_cinema_selection=a.cinemas.get(0);
        String data = "0\n0\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(5,a.selectMovie());
        data = "1\n1\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(7,a.selectMovie());
        data = "1\n2\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(8,a.selectMovie());
        data = "1\n10000\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(6,a.selectMovie());
        data = "10000\n10000\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(6,a.selectMovie());
    }

    @Test
    void showScheduleTest(){
        App a = new App();
        a.cinemas = a.getCinemas();
        a.movies = a.getMovies();
        a.sessions = a.getSessions();
        a.user_cinema_selection=a.cinemas.get(0);
        a.login_status =1;
        a.update_available_movies();
        for(int i = 0; i < a.movies.size();i++){
            if(a.movies.get(i).mid == a.available_movies.get(0)){ 
                a.user_movie_selection = a.movies.get(i);
            }
        }
        
        String data = "1\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(9,a.ShowSchedule());
        data = "0\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(6,a.ShowSchedule());
        data = "1\n";
        a.login_status =0;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,a.ShowSchedule());
        data = "1000\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(8,a.ShowSchedule());
    }

    @Test
    void paymentTest() throws IOException, ParseException {
        App a = new App();
        User user = new User("test");
        a.user = user;
        String data = "5\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(10, a.payment());
        data = "6\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(13, a.payment());
        data = "a\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(13, a.payment());

        a.price = 10.0;
        a.user.balance = 10.0;
        data = "2\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(16, a.payment());
        a.user.balance = 0.0;
        data = "2\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(13, a.payment());

        a.price = 0.0;
        data = "3\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(13, a.payment());

        a.price = 10.0;
        data = "3\nCharles\n40691\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(16, a.payment());  

        data = "1\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(13, a.payment());
        
        data = "1\na\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(13, a.payment());
        

    }

    @Test

    void TransactionTest(){
        App a = new App();
        a.seatBasket.add("Test");
        a.age.add("18+");
        assertEquals(1,a.seatBasket.size());
        a.printTransaction();
        assertEquals(0,a.seatBasket.size());
    }
    @Test
    void updateDatabaseSeatTest(){
        App a = new App();
        Session session = new Session(15,1,1, "gold", new Timestamp(12),0,0,0);
        a.user_session_selection = session;
        a.updateDatabaseSeat();

    }

    @Test
    void saveCardTest(){
        String data = "y\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App a = new App();
        a.last_card = "no record";
        a.user_id = "charles1";
        assertEquals(15,a.saveCard());

    }
    /*
    @Test 
    void iterativeUserTest() {
        App a = new App();
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        a.run();
        System.exit(0);
       
    }
    
    @Test 
    void iterativeEmployeeTest() {
        App a = new App();
        a.run();
        String data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        return;
    }
    */
    
}
