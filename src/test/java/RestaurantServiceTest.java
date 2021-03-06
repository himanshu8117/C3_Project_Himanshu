import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    // Code Refactor
    // To Setup the default Restaurant data, which will be automatically executed before each test cases
    // provide in this Test class
    @BeforeEach
    public void setRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("jalebi",150);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        // Calling function to get the restaurant by name
        Restaurant searchedRestaurant = service.findRestaurantByName("Amelie's cafe");

        // Assert operation to check the returned object is not null
        assertTrue(searchedRestaurant != null);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        // Calling function to get the restaurant which is not available
        // and Checking the returned exception against the restaurantNotFoundException
        assertThrows(restaurantNotFoundException.class,
                () -> service.findRestaurantByName("Pantry d'or"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<TDD>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void empty_item_list_should_return_total_cost_0(){
        // Creating object of RestaurantService and List of Item
        RestaurantService rs = new RestaurantService();
        List<Item> items = new ArrayList<>();
        // calling a totalCostOfSelectedItems method to get cost of empty item list
        int totalCostOfSelectedItems = rs.getTotalCostOfSelectedItems(items);

        // Assert statement to check the total cost of selected item is 0
        assertEquals(0, totalCostOfSelectedItems);
    }


    @Test
    public void passing_list_of_item_should_return_total_cost_greater_than_0(){
        // Creating object of RestaurantService
        RestaurantService rs = new RestaurantService();

        // Creating item list and adding few items to it
        List<Item> items = new ArrayList<>();
        items.add(new Item("Sweet corn soup", 170));
        items.add(new Item("Jalebi", 150));

        // Calling a totalCostOfSelectedItems method to get the total cost of given item list
        int totalCostOfSelectedItems = rs.getTotalCostOfSelectedItems(items);

        // Assert statement to check the total cost of given items is greater than 0
        assertTrue(totalCostOfSelectedItems > 0);
    }
}

    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>