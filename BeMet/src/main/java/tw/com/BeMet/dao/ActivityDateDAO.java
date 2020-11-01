package tw.com.BeMet.dao;

import tw.com.BeMet.bean.ActivityDateBean;
import tw.com.BeMet.vo.ActivityDate;

import java.util.List;


public interface ActivityDateDAO extends BaseDAO<ActivityDate> {
    public List<ActivityDate> search(ActivityDateBean activityDateBean);
}
