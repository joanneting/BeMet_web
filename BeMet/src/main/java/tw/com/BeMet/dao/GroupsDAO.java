package tw.com.BeMet.dao;

import tw.com.BeMet.bean.GroupsBean;
import tw.com.BeMet.vo.Groups;

import java.util.List;

public interface GroupsDAO extends BaseDAO<Groups> {
    public List<Groups> search(GroupsBean groupsBean);
}
