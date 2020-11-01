package tw.com.BeMet.dao;

import tw.com.BeMet.bean.ActivityLabelBean;
import tw.com.BeMet.vo.ActivityLabel;

import java.util.List;

public interface ActivityLabelDAO extends BaseDAO<ActivityLabel> {
    public List<ActivityLabel> search(ActivityLabelBean activityLabelBean);
}
