package lab3;



import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named("requestBean")
@RequestScoped
public class RequestBean implements Serializable {
    @Inject
    private ResultBean resultBean;
    @Inject
    private DatabaseManagerBean databaseBean;

    public DatabaseManagerBean getDatabaseBean() {
        return databaseBean;
    }

    public void setDatabaseBean(DatabaseManagerBean databaseBean) {
        this.databaseBean = databaseBean;
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

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
            System.out.println(x+","+y+","+r);
            System.out.println("Data not valid");
        }
        long endTime = System.nanoTime();
        pointBean.setLeadTime(endTime-startTime);
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(new Date(currentTimeMillis));
        pointBean.setServerTime(formattedDate);
        resultBean.addPoint(pointBean);
        databaseBean.add(pointBean);
        resultBean.redrawningPointNewRadius(pointBean); //тут лучше подумать, как правильно перерисовывать
        databaseBean.updateRadiusPoints(resultBean);
    }
    public void clearData(){
        resultBean.clear();
        databaseBean.clear();
    }
}
