package test;

import main.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void showAllOrder() throws SQLException {

    }
}