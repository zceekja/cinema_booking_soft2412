package project2;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserTest {

    @Test
    void redeemGiftCardTest() {
        try {
            App a = new App();
            User user1 = new User("test");
            assertEquals(13, user1.redeemGiftCard("a"));
            assertEquals(13, user1.redeemGiftCard("GC00000000000000"));
            assertEquals(13, user1.redeemGiftCard("GC40404040404040"));

            try {
                Connection conne = DriverManager.getConnection(a.CREDENTIALS_STRING);
                PreparedStatement update = conne.prepareStatement("UPDATE Gift_card SET status = ? WHERE serial_no = ?");
                update.setBoolean(1, false);
                update.setString(2, "GC40404040404040");
                update.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void payWithBalanceTest() {
        try {
            App a = new App();
            User user2 = new User("test");
            a.price = 0.0;
            assertFalse(user2.payWithBalance(a.price));
            a.price = 20.0;
            assertFalse(user2.payWithBalance(a.price));
            user2.balance = 10.0;
            assertFalse(user2.payWithBalance(a.price));
            a.price = 20.0;
            user2.balance = 20.0;
            assertTrue(user2.payWithBalance(a.price));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void payWithCreditCardTest() {
        try {
            User user3 = new User("test");
            assertTrue(user3.payWithCreditCard("Charles", "40691"));
            assertFalse(user3.payWithCreditCard("c", "40691"));
            assertFalse(user3.payWithCreditCard("Charles", "1"));
            assertFalse(user3.payWithCreditCard("c", "1"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void payWithSavedCardTest() {
        try {
            User user4 = new User("test");
            assertFalse(user4.payWithSavedCard());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
