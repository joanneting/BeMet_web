package tw.com.BeMet.service;

import tw.com.BeMet.bean.GroupsBean;

import java.util.List;

public interface GroupsService {
    public GroupsBean add(GroupsBean groupsBean) throws Exception;

    public GroupsBean update(GroupsBean groupsBean) throws Exception;

    public List<GroupsBean> search(GroupsBean groupsBean) throws Exception;

    public List<GroupsBean> searchALl() throws Exception;

    public void delete(Integer groupsNo) throws Exception;
}
