package tw.com.business_meet.service;

import tw.com.business_meet.bean.GroupsBean;

import java.util.List;

public interface GroupsService {
    public GroupsBean add(GroupsBean groupsBean) throws Exception;

    public GroupsBean updated(GroupsBean groupsBean) throws Exception;

    public List<GroupsBean> search(GroupsBean groupsBean) throws Exception;

    public List<GroupsBean> searchALl() throws Exception;

    public void delete(Integer groupsNo) throws Exception;
}
