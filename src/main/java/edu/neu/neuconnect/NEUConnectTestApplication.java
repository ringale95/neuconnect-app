package edu.neu.neuconnect;

import edu.neu.neuconnect.controller.rest.AuthController;
import edu.neu.neuconnect.controller.rest.UserController;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.Post;
import edu.neu.neuconnect.model.User;

import java.util.Date;

public class NEUConnectTestApplication {

    public static void main(String args[]){

        // Create a user
        User user = new User(1, "Raveena", "Ingale", "female", new Date(1995,8,9), "ravina.ing95@gmail.com", "123", "002837209", false);

        // Create Post for User
    }
}
