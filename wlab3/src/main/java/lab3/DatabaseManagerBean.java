package lab3;


import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("databaseBean")
@ApplicationScoped
public class DatabaseManagerBean implements Serializable {
    private final DatabaseManager databaseManager;
    public DatabaseManagerBean() {
        String dbUrl = "jdbc:postgresql://localhost:5432/studs";
        String dbUrlHelios = "jdbc:postgresql://pg:5432/studs";
        String dbConfigPath = "C:\\Users\\Тимур\\IdeaProjects\\wlab3\\DBconfig.Properties";
        this.databaseManager = new DatabaseManager(dbUrl, dbUrlHelios, dbConfigPath);
        databaseManager.start();
    }
    public void add(Point point){
        databaseManager.add(point);
    }
}
