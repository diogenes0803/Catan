package shared.model;

import java.util.HashMap;
import java.util.Map;


public class UserManager implements IUserManager {
    private int m_nextUserId;
    private Map<Integer, IUser> m_users;

    public UserManager() {
        m_nextUserId = 0;
        m_users = new HashMap<>();
    }

    @Override
    public IUser createUser(String username, String password) {
        User newUser = null;
        if(!doesUserExist(username)) {
            newUser = new User(username, password, m_nextUserId);
            m_users.put(m_nextUserId, newUser);
            m_nextUserId++;
        }
        return newUser;
    }

    @Override
    public boolean doesUserExist(String username) {
        for (IUser user : m_users.values()) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IUser loginUser(String username, String password) {
        for (IUser user : m_users.values()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    @Override
    public IUser getUser(int id) throws ModelException {
        if (m_users.containsKey(id)) {
            return m_users.get(id);
        }
        else {
            throw new ModelException("No user with ID " + id + " exists.");
        }
    }

    @Override
    public void loadUser(IUser user) throws ModelException {
        if (!m_users.containsKey(user.getId())) {
            m_users.put(user.getId(), user);

            // always set the next user ID to be 1 greater than the last largest ID
            if (user.getId() > m_nextUserId-1) {
                m_nextUserId = user.getId() + 1;
            }
            assert !m_users.containsKey(m_nextUserId) : "Messed up setting nextUserId -- " + m_nextUserId + " is already used!";
        }
        else {
            throw new ModelException("Loaded multiple users with the same ID: " + user.getId());
        }
    }
}
