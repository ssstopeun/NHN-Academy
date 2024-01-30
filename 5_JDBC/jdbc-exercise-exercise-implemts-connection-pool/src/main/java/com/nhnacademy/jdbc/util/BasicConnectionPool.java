package com.nhnacademy.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class BasicConnectionPool  {
    private final String driverClassName;
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final int maximumPoolSize;
    private final Queue<Connection> connections;

    public BasicConnectionPool(String driverClassName, String jdbcUrl, String username, String password, int maximumPoolSize)  {
        this.driverClassName=driverClassName;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.maximumPoolSize = maximumPoolSize;
        connections = new LinkedList<>();

        checkDriver();
        initialize();
    }

    private void checkDriver(){
        //todo#1 driverClassName에 해당하는 class가 존재하는지 check합니다.
        try{
            Class.forName(driverClassName);
        }catch(ClassNotFoundException e){
            throw new RuntimeException("Database driver not found : "+driverClassName);
        }
        //존재하지 않는다면 RuntimeException 예외처리.
    }

    private void initialize(){
        //todo#2 maximumPoolSize만큼 Connection 객체를 생성해서 Connection Pool에 등록합니다.
        try{
            for(int i=0;i<maximumPoolSize;i++){
                Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
                connections.offer(connection);
            }
        }catch(SQLException e){
            throw new RuntimeException("Error initializing connection pool : "+e.getMessage());
        }
    }

    public Connection getConnection() throws InterruptedException {
        //todo#3 Connection Pool에 connection이 존재하면 반환하고 비어있다면 Connection Pool에 Connection이 존재할 때 까지 대기합니다.
        synchronized (connections){
            while (connections.isEmpty()) {
                connections.wait();
            }
            return connections.poll();
        }
    }

    public void releaseConnection(Connection connection) {
        //todo#4 작업을 완료한 Connection 객체를 Connection Pool에 반납 합니다.
        synchronized (connections){
            connections.offer(connection);
            connections.notify();
        }
    }

    public int getUsedConnectionSize(){
        //todo#5 현재 사용중인 Connection 객체 수를 반환합니다.
        return maximumPoolSize - connections.size();
    }

    public void distory() throws SQLException {
        //todo#6 Connection Pool에 등록된 Connection을 close 합니다.
        synchronized (connections){
            for(Connection connection : connections){
                connection.close();
            }
            connections.clear();
        }

    }
}
