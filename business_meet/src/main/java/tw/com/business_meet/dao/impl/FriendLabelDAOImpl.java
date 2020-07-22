package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.FriendLabelBean;
import tw.com.business_meet.dao.FriendLabelDAO;
import tw.com.business_meet.vo.FriendLabel;

import java.util.List;

@Repository
public class FriendLabelDAOImpl extends BaseDAOImpl<FriendLabel> implements FriendLabelDAO {
    @Override
    public List<FriendLabel> search(FriendLabelBean friendLabelBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendLabel.class);
        Integer friendCustomizationNo = friendLabelBean.getFriendCustomizationNo();
        String content = friendLabelBean.getContent();
        if (friendCustomizationNo != null && friendCustomizationNo != 0) {
            detachedCriteria.add(Restrictions.eq("friendCustomizationNo", friendCustomizationNo));
        }
        if (content != null && !content.equals("")) {
            detachedCriteria.add(Restrictions.like("content", "%" + content + "%"));
        }
        return (List<FriendLabel>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
