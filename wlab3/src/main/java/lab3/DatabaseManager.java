package lab3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager implements Serializable {
    private Connection connection;
    private final String dbUrl;
    private final String dbUrlHelios;
    private final String dbPath;
    public DatabaseManager(String dbUrl, String dbUrlHelios, String dbPath) {
        this.dbUrl = dbUrl;
        this.dbUrlHelios = dbUrlHelios;
        this.dbPath = dbPath;
    }
    public void start(){
        try{
            this.connection();
            this.createDataBase();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public void connection(){
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream(this.dbPath));
            this.connection = DriverManager.getConnection(this.dbUrl,prop.getProperty("user"),prop.getProperty("password"));
        }catch(IOException | SQLException e){
            try{
                this.connection = DriverManager.getConnection(this.dbUrlHelios,prop.getProperty("user"),prop.getProperty("password"));
            }catch(SQLException e2){
                System.err.println(e2.getMessage());
                System.exit(1);
            }
        }
    }
    public void createDataBase() throws SQLException{
        String sql= """
                DROP TABLE IF EXISTS resultPoint CASCADE;
                CREATE TABLE resultPoint(
                    id SERIAL PRIMARY KEY,
                    x FLOAT NOT NULL,
                    y FLOAT NOT NULL,
                    r FLOAT NOT NULL,
                    isHit BOOLEAN,
                    lead_time BIGINT NOT NULL,
                    server_time TIMESTAMP NOT NULL
                );
                """;
        this.connection.prepareStatement(sql).execute();
    }
    public void add(Point point){
        try{
            String sql= """
                    INSERT INTO resultPoint(id,x, y, r, isHit, lead_time, server_time) VALUES(?,?,?,?,?,?,?);
                    """;
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, point.getId());
            ps.setFloat(2, point.getX());
            ps.setFloat(3, point.getY());
            ps.setFloat(4, point.getR());
            ps.setBoolean(5, point.getIsHit());
            ps.setLong(6,point.getLeadTime());
            ps.setTimestamp(7,Timestamp.valueOf(point.getServerTime()));
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
