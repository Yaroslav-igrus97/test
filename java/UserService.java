import java.util.List;

public class UserService {
    private static final UserDAO _userDAO = new UserDAO();

    public static int add(User user){
        return _userDAO.add(user);
    }

    public static int edit(int id, User user){
        return _userDAO.edit(1,user);
    }

    public static List<User> getByName(String username){
        return _userDAO.getByName(username);
    }

    public static List<User> getByAges(){
        return _userDAO.getByAges();
    }

    public static void removeByName(String name){
        _userDAO.removeByName(name);
    }

}
