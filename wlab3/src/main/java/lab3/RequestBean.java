package lab3;



import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;

@Named("requestBean")
@RequestScoped
public class RequestBean implements Serializable {
    @Inject
    private ResultBean resultBean;
    @Inject
    private DatabaseManagerBean databaseManagerBean;

    public void requestForm(Point pointBean){
        long startTime = System.nanoTime();
        float x = pointBean.getX();
        float y = pointBean.getY();
        float r = pointBean.getR();
        if (Validator.validate(x,y,r)){
            if (Checker.check(x,y,r)){
                System.out.println("hit");
                pointBean.setIsHit(true);
            }else{
                pointBean.setIsHit(false);
                System.out.println("miss");
            }
        }else{
            System.out.println("Data not valid");
        }
        long endTime = System.nanoTime();
        LocalDateTime now = LocalDateTime.now();
        pointBean.setLeadTime(endTime-startTime);
        pointBean.setServerTime(now);
        resultBean.addPoint(pointBean);
        databaseManagerBean.add(pointBean);
        resultBean.redrawningPointNewRadius(pointBean);
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

    public DatabaseManagerBean getDatabaseManagerBean() {
        return databaseManagerBean;
    }

    public void setDatabaseManagerBean(DatabaseManagerBean databaseManagerBean) {
        this.databaseManagerBean = databaseManagerBean;
    }
}
