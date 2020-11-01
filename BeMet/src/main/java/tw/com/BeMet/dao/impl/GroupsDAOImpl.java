package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.GroupsBean;
import tw.com.BeMet.dao.GroupsDAO;
import tw.com.BeMet.vo.Groups;

import java.util.List;

@Repository
public class GroupsDAOImpl extends BaseDAOImpl<Groups> implements GroupsDAO {
    @Override
    public List<Groups> search(GroupsBean groupsBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Groups.class);
        String name = groupsBean.getName();
        String userId = groupsBean.getUserId();
        if (name != null && !name.equals("")) {
            detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.eq("userId", userId));
        }
        return (List<Groups>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
