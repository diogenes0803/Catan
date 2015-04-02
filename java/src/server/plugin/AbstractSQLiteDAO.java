package server.plugin;

import server.persistence.PersistenceException;
import server.plugin.SQLitePersistenceManager;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractSQLiteDAO {
    private SQLitePersistenceManager local_persistenceManager;

    protected AbstractSQLiteDAO(SQLitePersistenceManager manager) {
        local_persistenceManager = manager;
    }

    protected void writeToDB(String sql, int id, Object obj) throws PersistenceException {
        try (PreparedStatement stmt = local_persistenceManager.getConnection().prepareStatement(sql)) {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(obj);

            stmt.setInt(1, id);
            stmt.setBytes(2, byteStream.toByteArray());

            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new PersistenceException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> readFromDB(String sql, int queryValue, String columnToRetrieve) throws PersistenceException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<T> returnValues = new ArrayList<T>();
        try {
            stmt = local_persistenceManager.getConnection().prepareStatement(sql);
            if (queryValue != -1) {
                stmt.setInt(1, queryValue);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                byte[] objectBytes = rs.getBytes(columnToRetrieve);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(objectBytes);
                ObjectInputStream objectStream = new ObjectInputStream(inputStream);
                returnValues.add((T)objectStream.readObject());
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                throw new PersistenceException(ex);
            }
        }

        return returnValues;
    }

    protected void deleteFromDB(String sql, int queryValue) throws PersistenceException {
        PreparedStatement stmt = null;

        try {
            stmt = local_persistenceManager.getConnection().prepareStatement(sql);
            stmt.setInt(1, queryValue);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        }
    }

    protected void updateDB(String sql, Object obj, int id) throws PersistenceException {
        try (PreparedStatement stmt = local_persistenceManager.getConnection().prepareStatement(sql)) {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(obj);
            objectStream.close();

            stmt.setBytes(1, byteStream.toByteArray());
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new PersistenceException(e);
        }
    }

    protected SQLitePersistenceManager getPersistenceManager() {
        return local_persistenceManager;
    }
}
