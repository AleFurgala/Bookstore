package test;


import com.mysql.jdbc.Connection;
import main.JdbConnection;
import main.Order;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getDate() {
        Order order = new Order();

            String result = order.getDate();
            Calendar calendar = Calendar.getInstance();
            int expectedYear = calendar.get(Calendar.YEAR);
            int expectedMonth = calendar.get(Calendar.MONTH)+1;
            int expectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            String expectedDate = expectedDayOfMonth + "" + expectedMonth + "" +expectedYear;
            assertEquals(expectedDate,result);

    }
}