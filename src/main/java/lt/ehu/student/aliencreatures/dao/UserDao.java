package lt.ehu.student.aliencreatures.dao;

public interface UserDao {
    String authenticate(String login, String passEnc);
}
