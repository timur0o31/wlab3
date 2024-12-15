package lab3;


import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("databaseBean")
@ApplicationScoped
public class DatabaseManagerBean implements Serializable {
    private DatabaseManager databaseManager;
    public DatabaseManagerBean() {
        String dbUrl = "jdbc:postgresql://localhost:5432/studs";
        String dbUrlHelios = "jdbc:postgresql://pg:5432/studs";
        String dbConfigPath = "C:\\Users\\Тимур\\IdeaProjects\\wlab3\\DBconfig.Properties";
        //String dbConfigPath = "/home/studs/s412875/DBconfig.Properties";
        this.databaseManager = new DatabaseManager(dbUrl, dbUrlHelios, dbConfigPath);
    }
    @PostConstruct
    public void init() {
        this.databaseManager.start();
        System.out.println("Database initialized");
    }
    public void add(Point point){
        databaseManager.add(point);
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager=databaseManager;
    }
    public void clear(){
        this.databaseManager.deleteData();
    }

    public void updateRadiusPoints(ResultBean result){
        this.databaseManager.updatePoints(result.getPointList());
    }
}
