package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.FriendCustomizationBean;
import tw.com.business_meet.dao.FriendCustomizationDAO;
import tw.com.business_meet.vo.FriendCustomization;

import java.util.List;

@Repository
public class FriendCustomizationDAOImpl extends BaseDAOImpl<FriendCustomization> implements FriendCustomizationDAO {
    @Override
    public List<FriendCustomization> search(FriendCustomizationBean friendCustomizationBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendCustomization.class);
        Integer friendNo = friendCustomizationBean.getFriendNo();
        String name = friendCustomizationBean.getName();
        if (friendNo != null && friendNo != 0) {
            detachedCriteria.add(Restrictions.eq("friendNo", friendNo));
        }
        if (name != null && !name.equals("")) {
            detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        return (List<FriendCustomization>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
