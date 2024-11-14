package enrollmentDemo;

import java.util.ArrayList;

public class Login {

    //arraylist to store usersList
    private ArrayList<User> users;

    //constructor
    public Login() {
        this.users = new ArrayList<>();
    }

    //method to add users
    public void addUser(User user) {
        this.users.add(user);
    }

    //verify if the user is found
    public boolean authenticate(String username, String password) {
        for (User user : this.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                //user is found
                return true;
            }
        }
        //user does not exist
        return false;
    }//login

    //get role for specific menu
    public String getUserRole(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user.getRole();
            }
        }
        return null;
    }//getUserRole

}//login class