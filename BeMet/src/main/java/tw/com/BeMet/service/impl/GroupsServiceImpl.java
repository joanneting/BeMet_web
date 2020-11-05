package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.BeMet.bean.GroupsBean;
import tw.com.BeMet.dao.GroupsDAO;
import tw.com.BeMet.service.GroupsService;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.vo.Groups;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupsServiceImpl implements GroupsService {
    @Autowired
    GroupsDAO groupsDAO;

    @Override
    public GroupsBean add(GroupsBean groupsBean) throws Exception {
        Groups groups = new Groups();
        groupsBean.setCreateDate(new Date());
        BeanUtility.copyProperties(groupsBean, groups);
        groups = groupsDAO.saveAndReturn(groups);
        BeanUtility.copyProperties(groups, groupsBean);
        return groupsBean;
    }

    @Override
    public GroupsBean update(GroupsBean groupsBean) throws Exception {
        Groups groups = groupsDAO.getById(groupsBean.getGroupNo());
        groupsBean.setModifyDate(new Date());
        BeanUtility.copyProperties(groupsBean, groups);
        groupsDAO.update(groups);
        BeanUtility.copyProperties(groups, groupsBean);
        return groupsBean;
    }

    @Override
    public List<GroupsBean> search(GroupsBean groupsBean) throws Exception {
        List<Groups> groupsList = groupsDAO.search(groupsBean);
        List<GroupsBean> groupsBeanList = new ArrayList<>();
        for (Groups groups :
                groupsList) {
            GroupsBean gb = new GroupsBean();
            BeanUtility.copyProperties(groups, gb);
            groupsBeanList.add(gb);
        }
        return groupsBeanList;
    }

    @Override
    public List<GroupsBean> searchALl() throws Exception {
        List<Groups> groupsList = groupsDAO.searchAll();
        List<GroupsBean> groupsBeanList = new ArrayList<>();
        for (Groups groups :
                groupsList) {
            GroupsBean gb = new GroupsBean();
            BeanUtility.copyProperties(groups, gb);
            groupsBeanList.add(gb);
        }
        return groupsBeanList;
    }

    @Override
    public void delete(Integer groupsNo) throws Exception {
        Groups groups = groupsDAO.getById(groupsNo);
        groupsDAO.delete(groups);
    }
}
