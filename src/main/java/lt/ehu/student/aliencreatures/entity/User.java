package lt.ehu.student.aliencreatures.entity;

import java.util.Objects;

public class User extends AbstractEntity {
    private String username;
    private String email;
    private String password;
    private String confirmationCode;
    private Role role;
    private Status status;

    public enum Role {
        ADMIN, MODER, USER, GUEST
    }

    public enum Status {
        ACTIVE, INACTIVE, BANNED
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(confirmationCode, user.confirmationCode) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, confirmationCode, role, status);
    }
}
