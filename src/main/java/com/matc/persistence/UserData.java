package com.matc.persistence;

import com.matc.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    private final Logger logger = Logger.getLogger(this.getClass());

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM users";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.error("SearchUser.getAllUsers()...SQL Exception: " + e);
        } catch (Exception e) {
            logger.error("SearchUser.getAllUsers()...Exception: " + e);
        }
        return users;
    }

    public List<User> searchUsers (String searchString) {
        List<User> selectedUsers = new ArrayList<User>();
        for (User user : getAllUsers()) {
            if (searchUserForString(searchString, user)) {
                selectedUsers.add(user);
            }
        }
        logger.info("Users found: " + selectedUsers.size());
        return selectedUsers;
    }

    private Boolean searchUserForString(String searchString, User user) {
        Boolean found = false;
        String regex = "\\S*" + searchString + "\\S*";
        if (Pattern.matches(regex, user.getFirstName())) {
            found = true;
        } else if (Pattern.matches(regex, user.getLastName())) {
            found = true;
        } else if (Pattern.matches(regex, user.getDateOfBirth())) {
            found = true;
        } else if (Pattern.matches(regex, user.getUserid())) {
            found = true;
        }
        return found;
    }

    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setLastName(results.getString("last_name"));
        user.setFirstName(results.getString("first_name"));
        user.setUserid(results.getString("id"));
        user.setDateOfBirth(results.getString("date_of_birth"));
        return user;
    }

}
