package OnlineSupermarketDelivery;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
	// A static map to store usernames and their corresponding passwords
    private static final Map<String, String> users = new HashMap<>();

    // Adds a new user to the user manager
    // parameter username = username of the new user
    // parameter password = password of the new user
    public static void addUser(String username, String password) {
        users.put(username, password); // Store the username and password in the map
    }

    // Checks if the provided username and password are valid
    public static boolean isValidUser(String username, String password) {
    	// Check if the username exists and if the provided password matches the stored password
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
