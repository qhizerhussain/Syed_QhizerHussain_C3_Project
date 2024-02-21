import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        LocalTime currentTime = LocalTime.parse("12:00:00");
        boolean isOpen = restaurant.isRestaurantOpen();
        assertTrue(isOpen, "Restaurant should be open at 12:00 PM");
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        LocalTime currentTime = LocalTime.parse("23:00:00");
        boolean isOpen = restaurant.isRestaurantOpen();
        assertFalse(isOpen, "Restaurant should be closed at 11:00 PM");
    }

    @Test
    public void calculate_order_value_should_return_sum_of_selected_items_prices() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int orderValue = restaurant.calculateOrderValue("Sweet corn soup", "Vegetable lasagne");

        assertEquals(119 + 269, orderValue);
    }

    @Test
    public void calculate_order_value_with_empty_selection_should_return_zero() {
        int orderValue = restaurant.calculateOrderValue();
        assertEquals(0, orderValue);
    }

    @Test
    public void calculate_order_value_with_nonexistent_item_should_ignore_item() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int orderValue = restaurant.calculateOrderValue("Sweet corn soup", "Nonexistent Item", "Vegetable lasagne");

        assertEquals(119 + 269, orderValue);
    }
}
