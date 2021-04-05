import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        // Iterate through Restaurant list and check if provided restaurant is available
        // if found in the list return the Restaurant object
        for (Restaurant r : restaurants) {
            if (r.getName().contains(restaurantName)) {
                Restaurant searchRestaurant = r;
                return searchRestaurant;
            }
        }
        // To throw restaurantNotFoundException when provided restaurant is not found in the list
        throw new restaurantNotFoundException(restaurantName);
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    public int getTotalCostOfSelectedItems(List<Item> items){
        int totalCost = 0;
        //Iterate item list to get total cost
        if (items.size() > 0){
            for (Item i: items){
                totalCost += i.getPrice();
            }
        }
        return totalCost;
    }

}

