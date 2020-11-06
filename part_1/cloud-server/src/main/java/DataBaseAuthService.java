import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAuthService implements AuthService {

    @Override
    public String getNickByLoginAndPass(String login, String password) {
        Connection connection = DataBaseConfig.getConnection();
        String sql = String.format("SELECT nickname FROM users_for_chat.users WHERE (`login` = '%s' AND `password` = '%s')", login, password);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Statement Error");
        }
        return null;
    }

    @Override
    public boolean changeNickname(String login, String password, String newNick) {
        Connection connection = DataBaseConfig.getConnection();
        String sql = String.format("UPDATE `users_for_chat`.`users` SET `nickname` = '%s' WHERE (`login` = '%s' AND `password` = '%s')", newNick, login, password);
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            throw new RuntimeException("Statement Error");
        }
        return false;
    }

    @Override
    public void start() {
        System.out.println("Authentication service started");
    }

    @Override
    public void stop() {
        System.out.println("Authentication service stopped");
    }
}
