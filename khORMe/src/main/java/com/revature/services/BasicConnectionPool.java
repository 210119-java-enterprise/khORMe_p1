package com.revature.services;

import com.revature.util.DbManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;




    public static BasicConnectionPool create(String url, String user, String password) throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new BasicConnectionPool(url, user, password, pool);
    }

    private BasicConnectionPool(String url, String user, String password, List<Connection> pool){
        this.url=url;
        this.user=user;
        this.password=password;
        this.connectionPool=pool;
    }

//    public BasicConnectionPool getInstance() {
//        return new BasicConnectionPool( url, user,  password,  connectionPool);
//    }


    @Override
    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        //return DriverManager.getConnection(url, user, password);TODO
        return null;
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Connection> getUsedConnections() {
        return usedConnections;
    }

    public void setUsedConnections(List<Connection> usedConnections) {
        this.usedConnections = usedConnections;
    }

}
