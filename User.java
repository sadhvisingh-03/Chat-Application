package chatapp;

// Parent class (Inheritance example)
public class User {
    protected String username;

    public User(String username) {
        this.username = username;
    }

    // Polymorphism example
    public void displayInfo() {
        System.out.println("User: " + username);
    }
}


// Child class demonstrating inheritance
class RegisteredUser extends User {
    private String password;

    public RegisteredUser(String username, String password) {
        super(username);
        this.password = password;
    }

    @Override
    public void displayInfo() {
        System.out.println("Registered User: " + username);
    }
}
//Separate class containing main method
class TestApp {
    public static void main(String[] args) {
        User user1 = new User("GuestUser");
        RegisteredUser user2 = new RegisteredUser("Sadhvi", "12345");

        user1.displayInfo();   // Parent method
        user2.displayInfo();   // Overridden method
    }
}