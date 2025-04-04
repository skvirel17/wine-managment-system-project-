package control.auth;

public class AuthService {
    public static User login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new User("admin", "admin", Role.ADMIN);
        } else if (username.equals("user") && password.equals("user")) {
            return new User("user", "user", Role.USER);
        } else {
            return null;
        }
    }
}
