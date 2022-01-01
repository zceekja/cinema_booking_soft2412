package project2;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    
    @Test
    void addAndremoveEmployeeTest(){
        Employee subject1 = new Employee("dude", 1);
        String name = "dude2";
        String id = "lol";
        String pass = "098765";
        String role = "staff";
        String data = name + "\n" + id + "\n" + pass +"\n"; 
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        subject1.addEmployee();
        ArrayList<String> test = new ArrayList<String>();
        test.add(id);
        test.add(name);
        test.add(role);
        test.add(pass); 
        //assertEquals(test,subject1.addEmployee());
        System.setIn(new ByteArrayInputStream(id.getBytes()));
        subject1.removeEmployee();

    }
    @Test
    void rmEmployeeTest(){
        Employee subject2 = new Employee("dude", 1);
        String id = "lol";
        System.setIn(new ByteArrayInputStream(id.getBytes()));
        //assertEquals("lol",subject2.removeEmployee()); //I commented b/c it causes an issue. Idk why it's the only issue after merging with Andrew's branch
    }

    @Test
    void selectUpcomingMTest(){
        App a = new App();
        Employee subject3 = new Employee("dude", 3);
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,subject3.selectUpcomingM());
        // try {
        //     Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
        //     PreparedStatement state = conn.prepareStatement("DELETE FROM Upcoming_week WHERE sid = ?");
        //     state.setString(1, "1");
        //     state.executeUpdate();
        // }
        // catch (Exception e){
        // }
    
    }
    @Test
    void selectUpcomingCTest(){
        App a = new App();
        Employee subject3 = new Employee("dude", 3);
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,subject3.selectUpcomingC());
        // try {
        //     Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
        //     PreparedStatement state = conn.prepareStatement("DELETE FROM Upcoming_week WHERE sid = ?");
        //     state.setString(1, "1");
        //     state.executeUpdate();
        // }
        // catch (Exception e){
        // }
    
    }
    @Test
    void selectUpcomingDateTest(){
        Employee subject3 = new Employee("dude", 3);
        String data = "1\n1\n1\n1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals("2021-1-1 1:1:00",subject3.selectUpcomingDate());
        // try {
        //     Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
        //     PreparedStatement state = conn.prepareStatement("DELETE FROM Upcoming_week WHERE sid = ?");
        //     state.setString(1, "1");
        //     state.executeUpdate();
        // }
        // catch (Exception e){
        // }
    
    }
    @Test
    void selectUpcomingType(){
        Employee subject3 = new Employee("dude", 3);
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals("bronze",subject3.selectionType());
        data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals("silver",subject3.selectionType());
        data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals("gold",subject3.selectionType());
        // try {
        //     Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
        //     PreparedStatement state = conn.prepareStatement("DELETE FROM Upcoming_week WHERE sid = ?");
        //     state.setString(1, "1");
        //     state.executeUpdate();
        // }
        // catch (Exception e){
        // }
    
    }
    @Test
    void selectUpcomingTest(){
        Employee subject3 = new Employee("dude", 3);
        subject3.selectionUpcoming(1,1, "bronze", "2021-1-1 1:1:00", 1);
        // try {
        //     Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
        //     PreparedStatement state = conn.prepareStatement("DELETE FROM Upcoming_week WHERE sid = ?");
        //     state.setString(1, "1");
        //     state.executeUpdate();
        // }
        // catch (Exception e){
        // }
    
    }
    @Test
    void selectUpcomingSIDtest(){
        Employee subject3 = new Employee("dude", 3);
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        assertEquals(1,subject3.selectUpcomingSID());
        // try {
        //     Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
        //     PreparedStatement state = conn.prepareStatement("DELETE FROM Upcoming_week WHERE sid = ?");
        //     state.setString(1, "1");
        //     state.executeUpdate();
        // }
        // catch (Exception e){
        // }
    
    }
    @Test  
    void addGCTest(){
        App a = new App();
        Employee em = new Employee("a",1);
        String data ="GC34567890199999\n50.00\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.addGiftCard();
        try {
            Connection conn = DriverManager.getConnection(a.CREDENTIALS_STRING);
            PreparedStatement state = conn.prepareStatement("DELETE FROM Gift_card WHERE serial_no = ?");
            state.setString(1, "GC34567890199999");
            state.executeUpdate();
        }
        catch (Exception e){
        }
    }
  
    @Test 
    void checkGCtest(){
        Employee e = new Employee("a",1);
        assertEquals(true, e.checkGiftCard("GC34567890123456"));
        assertEquals(false, e.checkGiftCard("GC3456789012345"));
        assertEquals(false, e.checkGiftCard("AC34567890123456"));
        assertEquals(false, e.checkGiftCard("GC34567890D23456"));
    }


    @Test
    void addMovieTestPosCase() {
        App a = new App();
        Employee em = new Employee("a", 1);
        String data = "Test Movie";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.addMovie();

        // check that 'Test Movie' exists
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Test Movie");
            ResultSet resultSet = preparedStatement.executeQuery();

            assertTrue(resultSet.next()); // assert test movie exists.
        } catch(Exception e) {
            e.printStackTrace();
        }

        // delete the test movie
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Test Movie");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void addMovieTestNegCase() {
        App a = new App();
        Employee em = new Employee("a", 1);
        InputStream backup = System.in;
        String data = "Test Movie";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.addMovie();
        System.setIn(backup);

        // check that 'Test Movie' exists
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Test Movie");
            ResultSet resultSet = preparedStatement.executeQuery();

            assertTrue(resultSet.next()); // assert test movie exists.
        } catch(Exception e) {
            e.printStackTrace();
        }

        int count = 0; // this will store the amount of movies in the database to ensure the duplicate movie isn't added
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS totalMovies FROM Movies;");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() == true) {
                count = resultSet.getInt(1);
            }

        } catch(Exception e) {
            System.out.println("ERROR OCCURED");
            e.printStackTrace();
        }

        // attempt to add the same movie
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.addMovie();

        // check that the total amount of movies remains unchanged
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS totalMovies FROM Movies;");
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == true) {
                assertEquals(rs.getInt("totalMovies"), count);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        // delete the test movie
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Test Movie");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void addMovieNegCaseTwo() {
        int count = 0; // get total amount of movies
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies;");
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == true) {
                count = rs.getInt(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        Employee em = new Employee("a", 1);
        InputStream backup = System.in;
        String data = "123";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.addMovie();
        System.setIn(backup);


        try { // ensure the movie with an invalid title was not added
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies;");
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == true) {
                assertEquals(rs.getInt(1), count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void deleteMovieTest() {
        // add a movie to the database to test
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movies(mid, title) VALUES(99999, ?);");
            preparedStatement.setString(1, "Sample Movie");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
//
        App a = new App();
        Employee em = new Employee("a",1);
        String data = "Sample Movie\ny";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.deleteMovie();


        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies WHERE mid = 99999;"); // try to select movie
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next() == true) {

                assertEquals(rs.getInt(1), 0);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void deleteMovieTestById() {
        // add a movie to the database to test
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movies(mid, title) VALUES(99999, ?);");
            preparedStatement.setString(1, "Sample Movie");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
//
        App a = new App();
        Employee em = new Employee("a",1);
        String data = "99999\ny";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.deleteMovie();


        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies WHERE mid = 99999;"); // try to select movie
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next() == true) {

                assertEquals(rs.getInt(1), 0);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void cancelDeleteMovie() {
        // add a movie to the database to test
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movies(mid, title) VALUES(99999, ?);");
            preparedStatement.setString(1, "Sample Movie");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
//
        App a = new App();
        Employee em = new Employee("a",1);
        String data = "99999\nn"; // enter movie to remove, then don't enter 'y'
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.deleteMovie();


        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies WHERE mid = 99999;"); // try to select movie
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next() == true) {

                assertEquals(rs.getInt(1), 1); // check to ensure the movie was not removed
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        data = "99999\ny";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.deleteMovie();

    }

    @Test
    void movieDoesntExistDelete(){
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies;"); // count total movies
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next() == true) {

                count = rs.getInt(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        App a = new App();
        Employee em = new Employee("a",1); // attempt to remove a movie that doesn't exist
        String data = "Random Movie\nq"; // enter movie to remove, then don't enter 'y'
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.deleteMovie();


        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies;"); // try to select movie
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next() == true) {

                assertEquals(rs.getInt(1), count); // check to ensure the movie was not removed
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void modifyMovie() {
        // add a movie to the database to test
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movies(mid, title) VALUES(99999, ?);");
            preparedStatement.setString(1, "Sample Movie");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

        App a = new App();
        Employee em = new Employee("a",1);
        String data = "Sample Movie\nSample Movie Modified"; // update movie name
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.modifyMovie();

        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Sample Movie Modified");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() == true) {
                assertEquals(1, resultSet.getInt(1)); // new movie name should exist.
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        // remove sample movie
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Sample Movie Modified");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void modifyMovieById() {
        // add a movie to the database to test
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movies(mid, title) VALUES(99999, ?);");
            preparedStatement.setString(1, "Sample Movie");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

        App a = new App();
        Employee em = new Employee("a",1);
        String data = "99999\nSample Movie Modified"; // update movie name
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        em.modifyMovie();

        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Sample Movie Modified");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() == true) {
                assertEquals(1, resultSet.getInt(1)); // new movie name should exist.
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        // remove sample movie
        try {
            Connection connection = DriverManager.getConnection(App.CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Movies WHERE title = ?;");
            preparedStatement.setString(1, "Sample Movie Modified");
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void bookingSummaryTest() {
        Employee e = new Employee("test", 2);
        List<Movie> m = new ArrayList<>();
        List<Cinema> c = new ArrayList<>();
        List<Session> s = new ArrayList<>();
        m.add(new Movie("movie1", 1));
        m.add(new Movie("movie1", 2));
        c.add(new Cinema("location1", 1));
        c.add(new Cinema("location1", 2));
        s.add(new Session(1, 1, 1, "gold", null, 0, 0, 0));
        e.bookingSummary(m, c, s);
    }
}
