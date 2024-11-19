package lab3;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Point implements Serializable {
    private int id=0;
    private float x;
    private float y;
    private float r;
    private boolean isHit;
    private long leadTime;
    private LocalDateTime serverTime;
    public Point(){}
    public Point(float x, float y, float r, boolean isHit, long leadTime, LocalDateTime serverTime){
        this.id+=1;
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.leadTime = leadTime;
        this.serverTime = serverTime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getServerTime() {
        return serverTime;
    }

    public void setServerTime(LocalDateTime serverTime) {
        this.serverTime = serverTime;
    }
}
