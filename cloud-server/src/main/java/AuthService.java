public interface AuthService {
    void start();
    void stop();
    String getNickByLoginAndPass(String login, String password);
    boolean changeNickname(String login, String password, String newNick);
}
