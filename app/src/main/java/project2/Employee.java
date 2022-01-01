package project2;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.xml.transform.Result;
import java.io.*;
import java.net.ConnectException;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.*;
public class Employee {
    public static final String CREDENTIALS_STRING = "jdbc:mysql://tux-database.cmxvkfohfzm4.ap-southeast-2.rds.amazonaws.com:3306/a2?user=admin&password=password";
    public String name;
    public int role;
    public String movie;
    public String location;
    public int isTimeout = 0;
    public final int timeout = 15;
    public Employee(String name, int role){
        this.name =name;
        this.role =role;
    }
    public void addEmployee(){
        try { 
            Connection connadd = DriverManager.getConnection(  CREDENTIALS_STRING);
            PreparedStatement preStatementadd = connadd.prepareStatement("INSERT INTO Employee_account(eid, name, type, password) VALUES(?,?,?,?);");
            ArrayList<String>newStaff = new ArrayList<String>();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            

            System.out.println("Name: ");
            System.out.println("(Enter q to cancel)");
            String name = getEmployeeInputTimeOut(in);
            if(name.equals("q")){
                return;
            }
            if(name.equals("timeout")){
                return ;
            }
            System.out.println("Set your id: ");
            System.out.println("(Enter q to cancel)");
            String id = getEmployeeInputTimeOut(in);
            if(id.equals("timeout")){
                return ;
            }
            if(id.equals("q")){
                return;
            }
            System.out.println("Set your password: ");
            System.out.println("(Enter q to cancel)");
            
            String pass = getEmployeeInputTimeOut(in);
            if(pass.equals("timeout")){
                return ;
            }
            if(name.equals("q")){
                return;
            }
            String role = "staff";
            newStaff.add(id);
            newStaff.add(name);
            newStaff.add(role);
            newStaff.add(pass);
            ArrayList<String> potentialCandidate = newStaff;
            preStatementadd.setString(1,potentialCandidate.get(0));
            preStatementadd.setString(2,potentialCandidate.get(1));
            preStatementadd.setString(3, potentialCandidate.get(2));
            preStatementadd.setString(4, potentialCandidate.get(3));
            preStatementadd.executeUpdate();
            System.out.println(potentialCandidate.get(1) + " successfully added, welcome to the team!");
        }
        catch (Exception e){
            System.out.println("Invalid inputs");
        }
    }
    public void removeEmployee(){
        try { 
            Connection connrm = DriverManager.getConnection( CREDENTIALS_STRING);
            System.out.println("id: ");
            System.out.println("(Enter q to cancel)");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String id = getEmployeeInputTimeOut(in);
            if(id.equals("timeout")){
                return ;
            }
            if(id.equals("q")){
                return;
            }
            String firedEmployee = id;
            PreparedStatement preStatementrm = connrm.prepareStatement("DELETE FROM Employee_account WHERE eid = ?;");
            preStatementrm.setString(1,firedEmployee);
            preStatementrm.executeUpdate();
            System.out.println("Employee removed!");
        }
        catch (Exception e){
            System.out.println("Invalid inputs");
        }

    }
    public int selectUpcomingM(){
        try {
            List<Movie> ls = new ArrayList<Movie>(); 
            Connection connrm = DriverManager.getConnection( CREDENTIALS_STRING);
            Statement statement = connrm.createStatement();
            ResultSet result = statement.executeQuery("select * from Movies;");
            while(result.next()){
                String s = result.getString("title");
                int i = result.getInt("mid");
                Movie movie = new Movie(s,i);
                ls.add(movie);
            }
            int k = 1;
            for(Movie m: ls){
                System.out.println("[" + k + "] " + m.title );
                k += 1;
            }
            Scanner ask = new Scanner(System.in);
            System.out.println("Select movie: ");
            String selection = ask.nextLine();
            int selectionInt = Integer.parseInt(selection);
            Movie select = ls.get(selectionInt - 1);
            int mid = select.mid;
            movie = select.title;
            return mid;
        }
        catch (Exception e){
            System.out.println("Invalid inputs");
        }
        return 0;
    }
    public int selectUpcomingC(){
        try{
            Connection connrm = DriverManager.getConnection( CREDENTIALS_STRING);
            Statement statement = connrm.createStatement();
            List<Cinema> lsC = new ArrayList<Cinema>(); 
            statement = connrm.createStatement();
            ResultSet result = statement.executeQuery("select * from Cinema;");
            while(result.next()){
                String s = result.getString("clocation");
                int i = result.getInt("cid");
                Cinema cinema = new Cinema(s,i);
                lsC.add(cinema);
            }
            int k = 1;
            for(Cinema c: lsC){
                System.out.println("[" + k + "] " + c.clocation );
                k += 1;
            }
            Scanner ask = new Scanner(System.in);
            System.out.println("Select Cinema: ");
            String selection = ask.nextLine();
            int selectionInt = Integer.parseInt(selection);
            Cinema selectC = lsC.get(selectionInt - 1);
            int cid = selectC.cid;
            location = selectC.clocation;
            return cid;
        }
        catch (Exception e){
            System.out.println("Invalid input");
        }
        return 0;

    }
    public String selectUpcomingDate(){
            Scanner ask = new Scanner(System.in);
            System.out.println("Select month: ");
            String selectionMonth = ask.nextLine();
            if(selectionMonth.equals("q")){
                return null;
            }
            System.out.println("Select date: ");
            String selectionDate = ask.nextLine();
            if(selectionMonth.equals("q")){
                return null;
            }
            System.out.println("Select hour: ");
            String selectionHr = ask.nextLine();
            if(selectionMonth.equals("q")){
                return null;
            }
            System.out.println("Select minutes: ");
            String selectionMin = ask.nextLine();
            if(selectionMonth.equals("q")){
                return null;
            };
            String dateInStringSql = "2021-" + selectionMonth + "-" + selectionDate + " " + selectionHr + ":" + selectionMin + ":00" ; 
            return dateInStringSql;

    }
    public String selectionType(){
        System.out.println("Select screen type: ");
        System.out.println("[1] bronze\n[2] silver\n[3] gold");
        System.out.println("(enter 'q' to cancel) "); 
        Scanner ask = new Scanner(System.in);
        String selectionType = ask.nextLine();
        String type = "";
        if(selectionType.equals("q")){
            type = "exit";
        }
        if(selectionType.equals("1")){
            type = "bronze";
        }
        if(selectionType.equals("2")){
            type = "silver";
        }
        if(selectionType.equals("3")){
            type = "gold";
        }
        return type;
    }
    public int selectUpcomingSID(){
        System.out.println("Select sid: ");
        Scanner ask = new Scanner(System.in);
        String sids = ask.nextLine();
        int sid = Integer.parseInt(sids);
        return sid;

    }
    public void selectionUpcoming(int mid, int cid, String type, String dateInStringSql, int sid){
        try{
            Connection connrm = DriverManager.getConnection( CREDENTIALS_STRING);
            PreparedStatement preStatement = connrm.prepareStatement("INSERT INTO Upcoming_week VALUES(?,?,?,?,?,?,?,?);");
            preStatement.setInt(1,sid);
            preStatement.setInt(2,mid);
            preStatement.setInt(3,cid);
            preStatement.setString(4,type);
            preStatement.setString(5, dateInStringSql);
            preStatement.setInt(6, 0);
            preStatement.setInt(7, 0);
            preStatement.setInt(8, 0);
            preStatement.executeUpdate();
            System.out.println(movie + " added to upcoming week at " + location);
            System.out.println();
        }
        catch(Exception e){
            System.out.println("Invalid output");
        }
    }
    public void addGiftCard(){
        try { 
            Connection connrm = DriverManager.getConnection( CREDENTIALS_STRING);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Insert new giftcard:");
            System.out.println("Enter q to cancel");
            String code = getEmployeeInputTimeOut(in);
            

            if(code.equals("q")){
                return ;
            }
            if(code.equals("timeout")){
                return;
            }
            System.out.println("Insert value of giftcard");
            System.out.println("Enter q to cancel");
            String strv = getEmployeeInputTimeOut(in);
            Double value = 0.0;
            if(strv.equals("q")){
                return;
            }
            if(strv.equals("timeout")){
                return;
            }
            try{
                value = Double.parseDouble(strv);
            }
            catch(Exception e){
                return;
            }
            
            if (!checkGiftCard(code)){
                return ;
            }
            PreparedStatement preStatementrm = connrm.prepareStatement("INSERT INTO  Gift_card (serial_no,value, status) VALUES (? , ? ,0);");
            preStatementrm.setString(1,code);
            preStatementrm.setDouble(2,value);
            preStatementrm.executeUpdate();
            System.out.print("Gift card ");
            System.out.print(code);
            System.out.println(" is activated");
        }
        catch (Exception e){
            System.out.println("Invalid inputs");
        }
    }
    public boolean checkGiftCard(String gc){
        if (gc.length() != 16){
            System.out.println("Invalid Length");
            return false;
        }
        if  (!gc.substring(0,2).equals("GC")){
            System.out.println("Invalid Start, Gift Card must start with GC.");
            return false;
        }
        try { 
            Long.parseLong(gc.substring(2,16)); 
        } catch(NumberFormatException e) { 
            System.out.println("Invalid,  Gift Card must start with GC and follow by 14 digit number.");
            return false; 
        } catch(NullPointerException e) {
            System.out.println("Invalid.");
            return false;
        }
        return true;
    }

    public void addMovie() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter name of movie to add: \n(enter 'q' to cancel) "); // ask for title
        String input = sc.nextLine();
        if(input.equals("q")) { // if q was entered, exit
            System.out.println("Exiting ...");
            return;
        }

        int id = -1; // check to ensure the movie title isn't a number
        try {
            id = Integer.parseInt(input);
        } catch(NumberFormatException e) {

        }

        if(id != -1) {
            System.out.println("Sorry, you can't use an id as a movie title. Please try again.");
            return;
        }

        try { // check to ensure the movie doesn't exist already
            Connection connection = DriverManager.getConnection(CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movies WHERE title = LOWER(?);");
            preparedStatement.setString(1, input.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() == true) {
                System.out.println("'" + input + "' already exists. Please try again.");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer lastID = -1; // get the id of the last movie
        try {
            Connection connection = DriverManager.getConnection(CREDENTIALS_STRING);
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT * FROM Movies ORDER BY mid DESC LIMIT 1;");
            if(rs.next() == false) { // no movies in the database
                lastID = 0;
            } else {
                lastID = rs.getInt("mid");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        lastID += 1;

        try { // add movie to database
            Connection connection = DriverManager.getConnection(CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movies(mid, title) VALUES(?, ?);");
            preparedStatement.setInt(1, lastID);
            preparedStatement.setString(2, input);
            preparedStatement.executeUpdate();
            System.out.println("Successfully added new movie '" + input + "'.");
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    public void modifyMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie title or id to modify. \nEnter 'q' to exit.");
        String input = sc.nextLine();

        if(input.equals("q")) {
            return;
        }

        int id = -1;
        try {
            id = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            id = -1;
        }

        if(id == -1) { // a movie title has been entered
            id = getMovieId(input);
        }

        // either a movie id has been entered or a movie id has been retrieved from movie title
        try {
            Connection connection = DriverManager.getConnection(CREDENTIALS_STRING);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Movies WHERE mid = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next() == false) {
                System.out.println("Movie id number '" + Integer.toString(id) + "' does not exist.\nPlease try again.");
                modifyMovie();
            } else {
                System.out.println("Updating movie '" + rs.getString("title") + "'. \nEnter new movie title. ('q' to exit) ");
                String in = sc.nextLine();

                if(in.equals("q")) {
                    return;
                }

                statement = connection.prepareStatement(("UPDATE Movies SET mid = ?, title = ? WHERE mid = ?"));
                statement.setInt(1, id);
                statement.setString(2, in);
                statement.setInt(3, id);
                statement.executeUpdate();
                System.out.println("Successfully updated movie. " + rs.getString("title") + " --> " + in);
                return;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getMovieId(String title) {
        title = title.toLowerCase(); // title should be case insensitive

        try {
            Connection connection = DriverManager.getConnection(CREDENTIALS_STRING);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Movies WHERE title = LOWER(?);");
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();

            if(rs.next() == false) {
                System.out.println("Movie '" + title + "' does not exist. Please try again.");
                modifyMovie();
            }

            return rs.getInt("mid");
        } catch(Exception e ) {
            e.printStackTrace();
            return -1;
        }
    }

    public void deleteMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie title or id to delete. \nEnter 'q' to exit.");
        String input = sc.nextLine();

        if(input.equals("q")) {
            return;
        }

        int id = -1;
        try {
            id = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            id = -1;
        }

        if(id == -1) { // a movie title has been entered
            id = getMovieId(input);
        }

        try {
            Connection connection = DriverManager.getConnection(CREDENTIALS_STRING);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT title FROM Movies WHERE mid = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() == true) {
                System.out.println("Are you sure you want to delete movie '" + resultSet.getString("title") + "' (mid " + Integer.toString(id) + ").\nThis action can't be undone. Type 'y' to confirm.");
            }
            if(sc.nextLine().equals("y")) {
                // delete
                preparedStatement = connection.prepareStatement("DELETE FROM Movies WHERE mid = ?;");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Removed movie '" + resultSet.getString("title") + "'.");
                return;
            } else {
                System.out.println("No movies were removed.");
                return;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void bookingSummary(List<Movie> movies, List<Cinema> cinemas, List<Session> sessions) {
        try {
            File f = new File("bookings_summary.txt");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Booking summary");
            pw.println("");

            for (int i = 0; i < sessions.size(); i++) {
                pw.println(i + 1);
                pw.printf("Session id: %d\n", sessions.get(i).sid);

                for (int j = 0; j < cinemas.size(); j ++) {
                    if (sessions.get(i).cid == cinemas.get(j).cid) {
                        pw.printf("Location: %s\n", cinemas.get(j).clocation);
                    }
                }
                for (int j = 0; j < movies.size(); j ++) {
                    if (sessions.get(i).mid == movies.get(j).mid) {
                        pw.printf("Movie title: %s\n", movies.get(j).title);
                    }
                }

                pw.printf("Screen type: %s\n", sessions.get(i).screen_type);
                pw.printf("Date/Time: %s\n", sessions.get(i).movieTime);
                pw.printf("Available front seat: %d\n", sessions.get(i).max_front_seat - sessions.get(i).booked_front_seat);
                pw.printf("Booked front seat: %d\n", sessions.get(i).booked_front_seat);
                pw.printf("Available middle seat: %d\n", sessions.get(i).max_middle_seat - sessions.get(i).booked_middle_seat);
                pw.printf("Booked middle seat: %d\n", sessions.get(i).booked_middle_seat);
                pw.printf("Available rear seat: %d\n", sessions.get(i).max_back_seat - sessions.get(i).booked_back_seat);
                pw.printf("Booked rear seat: %d\n", sessions.get(i).booked_back_seat);
                pw.println("");
            }

            pw.close();
            System.out.println("Bookings summary generated");

        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    public String getEmployeeInputTimeOut(BufferedReader in){
        String input = "";
        String timeo = "timeout";
        try{
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < timeout * 1000
                    && !in.ready()) {
                }
            if (in.ready()) {
                input = in.readLine();
            } else {
                System.out.println("");
                System.out.println("********************************************************");
                System.out.println("*                                                      *");                                    
                System.out.println("*    Session Timeout: Automatically logoff             *");
                System.out.println("*                                                      *");
                System.out.println("********************************************************");
                System.out.println("");
                isTimeout = 1;
                return timeo;
            }
        }
        catch(IOException e) {
            
            e.printStackTrace();
            return timeo;
        }
        return input;
    }


    public void cancelled_transaction_summary(){
        try {
            File transactions_file = new File("cancelled_transactions.txt");
            FileWriter fw = new FileWriter(transactions_file);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Cancelled transactions summary");
            pw.println("");
            Connection connadd = DriverManager.getConnection(  CREDENTIALS_STRING);
            PreparedStatement preStatementadd = connadd.prepareStatement("SELECT tid, status, detail, date_time, user_id FROM Transaction WHERE status='Unsuccessful' AND user_id IS NOT NULL;");
            // process the results
            ResultSet rs = preStatementadd.executeQuery();
            int i = 0;
            while ( rs.next() )
            {
                i++;
                pw.println("\n" + i);
                pw.printf("Transaction ID: " + rs.getString("tid"));
                pw.printf(" Status: " + rs.getString("status"));
                pw.printf(" Detail: " + rs.getString("detail"));
                pw.printf(" User ID: " + rs.getString("user_id"));
                pw.printf(" Date/Time: " + rs.getTimestamp("date_time"));
            }
            rs.close();
            preStatementadd.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
    
        
