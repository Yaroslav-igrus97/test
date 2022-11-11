import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DB db = new DB();

        try {
//            db.isConnected();
            db.insertVal();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
