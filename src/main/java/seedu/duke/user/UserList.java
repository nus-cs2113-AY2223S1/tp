package seedu.duke.user;

import java.util.ArrayList;

public class UserList {
    protected ArrayList<User> userList;

    public UserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public UserList() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User toAdd) {
        userList.add(toAdd);
    }

    public void deleteUser(int indexToDelete) {
        userList.remove(indexToDelete);
    }

    public int getSize() {
        return userList.size();
    }

    public User getUser(int index) {
        return userList.get(index);
    }

    public int findUser(String userId) {
        for (int i = 0; i < userList.size(); i += 1) {
            if (userList.get(i).getUserId().equals(userId)) {
                return i;
            }
        }
        return -1;
    }

    public String listUser() {
        StringBuilder listOfUsers = new StringBuilder();
        for (User user : userList) {
            listOfUsers.append(user.toString()).append(System.lineSeparator());
        }
        return listOfUsers.toString();
    }
}
