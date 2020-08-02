package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.FriendRemarkBean;
import tw.com.business_meet.dao.FriendRemarkDAO;
import tw.com.business_meet.vo.FriendRemark;

import java.util.List;

@Repository
public class FriendRemarkDAOImpl extends BaseDAOImpl<FriendRemark> implements FriendRemarkDAO {
    @Override
    public List<FriendRemark> search(FriendRemarkBean friendRemarkBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendRemark.class);
        Integer friendCustomizationNo = friendRemarkBean.getFriendCustomizationNo();
        Integer friendLabelNo = friendRemarkBean.getFriendLabelNo();
        if (friendCustomizationNo != null && friendCustomizationNo != 0) {
            detachedCriteria.add(Restrictions.eq("friendCustomizationNo", friendCustomizationNo));
        }
        if (friendLabelNo != null && friendLabelNo != 0) {
            detachedCriteria.add(Restrictions.eq("friendLabelNo", friendLabelNo));
        }
        return (List<FriendRemark>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
