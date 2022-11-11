import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DB {
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DB_NAME = "test_db";
    private final String LOGIN = "root";
    private final String PASS = "root";

    private Connection dbConn = null;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String conStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(conStr, LOGIN, PASS);
        return dbConn;
    }

    public void isConnected() throws SQLException, ClassNotFoundException{
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }

    public void insertVal() throws SQLException, ClassNotFoundException{
        Statement statement = getDbConnection().createStatement();
        String sql1 = "SELECT `id` FROM `users` WHERE `login` = 'john'";
        ResultSet res1 = statement.executeQuery(sql1);
        int user_id = 0;
        while(res1.next()){
            user_id = res1.getInt("id");
        }

        String sql2 = "SELECT `id` FROM `items` WHERE `category` = 'hats'";
        ResultSet res2 = statement.executeQuery(sql2);
        ArrayList<Integer> items_id = new ArrayList<>();
        while (res2.next()){
            items_id.add(res2.getInt(1));
        }

//        String sql3 = "INSERT INTO  `orders` (`user_id`, `item_id`) VALUES ";
//        for(int i = 0; i < items_id.size(); i ++){
//            sql3 += "(" + user_id + " , " + items_id.get(i) + ")";
//            if(items_id.size() - i > 1){
//                sql3 += ", ";
//            } else{
//                sql3 += ";";
//            }
//        }
//        System.out.println(sql3);
//        PreparedStatement prSt = getDbConnection().prepareStatement(sql3);
//        prSt.executeUpdate();

        String sql3 = "SELECT a.login, c.title " +
                "FROM orders b " +
                "JOIN users a " +
                "ON a.id = b.user_id " +
                "JOIN items c " +
                "ON c.id = b.item_id " +
                "ORDER BY c.title DESC";
        ResultSet res4 = statement.executeQuery(sql3);
        System.out.println("Все заказы\n\n");
        while (res4.next()){
            System.out.println(res4.getString("login") + " - " + res4.getString("title"));
        }
    }

}
