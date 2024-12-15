package lab3;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("resultBean")
@ApplicationScoped
public class ResultBean implements Serializable {
    private List<Point> pointList = new ArrayList<Point>();
    private String pointListJson;
    public List<Point> getPointList() {
        return pointList;
    }
    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }
    public void addPoint(Point point) {
        pointList.add(point);
    }
    public void redrawningPointNewRadius(Point point){
        float newRadius = point.getR();
        for (Point p : pointList) {
            p.setR(newRadius);
            p.setIsHit(Checker.check(p.getX(),p.getY(),newRadius));
        }
    }

    public String getPointListJson() {
        Gson gson = new Gson();
        return gson.toJson(pointList);
    }

    public void setPointListJson(String pointListJson) {
        this.pointListJson = pointListJson;
    }
    public void clear(){
        this.pointList.clear();
    }
}
