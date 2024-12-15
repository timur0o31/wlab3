package lab3;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
@Named("pointBean")
@RequestScoped
public class Point implements Serializable {
    private float x;
    private float y;
    private float r;
    private boolean isHit;
    private long leadTime;
    private String serverTime;
    public Point(){}
    public Point(float x, float y, float r, boolean isHit, long leadTime, String serverTime){
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.leadTime = leadTime;
        this.serverTime = serverTime;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean hit) {
        isHit = hit;
    }

    public long getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(long leadTime) {
        this.leadTime = leadTime;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

}
