package shared.model;


public class User implements IUser {
    private String name;
    private String m_password;
    private int playerID;

    public User(String username, String password, int id) {
        name = username;
        m_password = password;
        playerID = id;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return m_password;
    }

    @Override
    public int getId() {
        return playerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (playerID != user.playerID) return false;
        if (!m_password.equals(user.m_password)) return false;
        if (!name.equals(user.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + m_password.hashCode();
        result = 31 * result + playerID;
        return result;
    }
}
