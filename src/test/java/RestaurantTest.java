
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    // Code Refactor
    // To Setup the default Restaurant data, which will be automatically executed before each test cases
    // provide in this Test class
    @BeforeEach
    public void setRestaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        // Calling Mockito.Spy to create a spyRestaurant object
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        // Setting up current Time to 10:35
        LocalTime currentTime = LocalTime.of(10,35,00);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);

        // Calling method to check whether restaurant is open or not and executing assert statemnet
        boolean isRestaurantOpen = spyRestaurant.isRestaurantOpen();
        assertTrue(isRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        // Calling Mockito.Spy to create a spyRestaurant object
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        // Setting up current Time to 10:25
        LocalTime currentTime = LocalTime.of(10,25,00);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);

        // Calling method to check whether restaurant is open or not and executing assert statemnet
        boolean isRestaurantOpen = spyRestaurant.isRestaurantOpen();
        assertFalse(isRestaurantOpen);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}

