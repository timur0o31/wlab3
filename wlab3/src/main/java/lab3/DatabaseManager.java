package lab3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DatabaseManager implements Serializable {
    private Connection connection;
    private String dbUrl;
    private String dbUrlHelios;
    private String dbPath;
    public DatabaseManager(String dbUrl, String dbUrlHelios, String dbPath) {
        this.dbUrl = dbUrl;
        this.dbUrlHelios = dbUrlHelios;
        this.dbPath = dbPath;
    }
    public void start(){
        try{
            this.connection();
            this.createDataBase();
            System.out.println("Create okay");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public void connection(){
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream(this.dbPath));
            this.connection = DriverManager.getConnection(this.dbUrl,prop.getProperty("user"),prop.getProperty("password"));
            System.out.println("connection okay");
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
                DROP TABLE IF EXISTS result_point CASCADE;
                CREATE TABLE result_point(
                    id SERIAL PRIMARY KEY,
                    x FLOAT NOT NULL,
                    y FLOAT NOT NULL,
                    r FLOAT NOT NULL,
                    isHit BOOLEAN,
                    lead_time BIGINT NOT NULL,
                    server_time VARCHAR(100) NOT NULL
                );
                """;
        this.connection.prepareStatement(sql).execute();
    }
    public void add(Point point){
        try{
            System.out.println("try to add new point:"+point.getX()+","+point.getY()+","+point.getR()+","+point.getIsHit()+","+point.getLeadTime()+","+point.getServerTime());
            String sql= """
                    INSERT INTO result_point(x, y, r, isHit, lead_time, server_time) VALUES(?,?,?,?,?,?);
                    """;
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setFloat(1, point.getX());
            ps.setFloat(2, point.getY());
            ps.setFloat(3, point.getR());
            ps.setBoolean(4, point.getIsHit());
            ps.setLong(5,point.getLeadTime());
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //LocalDateTime localDateTime = LocalDateTime.parse(point.getServerTime(), formatter);
            //ps.setTimestamp(6,Timestamp.valueOf(localDateTime));
            ps.setString(6,point.getServerTime());
            int rs = ps.executeUpdate();
            if(rs==0){
                System.err.println("Point not add in Database!");
            }else{
                System.out.println("Good, point add");
            }
        }catch(SQLException e){
            System.err.println("Error!!!!!");
        }
    }
    public void deleteData(){
        String sql ="DELETE FROM result_point";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int rowsDelete = ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error, data has not been deleted.");
        }
    }

    public void updatePoints(List<Point> points){
        String query = "UPDATE result_point SET r = ?, ishit = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int index = 1;
            for (Point point : points){
                preparedStatement.setDouble(1, point.getR());
                preparedStatement.setBoolean(2, point.getIsHit());
                preparedStatement.setInt(3, index);
                preparedStatement.executeUpdate();
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}