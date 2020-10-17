package tw.com.business_meet.dao;

import tw.com.business_meet.bean.GroupsBean;
import tw.com.business_meet.vo.Groups;

import java.util.List;

public interface GroupsDAO extends BaseDAO<Groups> {
    public List<Groups> search(GroupsBean groupsBean);
}
