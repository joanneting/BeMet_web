package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.FriendGroupBean;
import tw.com.business_meet.dao.FriendGroupDAO;
import tw.com.business_meet.vo.FriendGroup;

import java.util.List;

@Repository
public class FriendGroupDAOImpl extends BaseDAOImpl<FriendGroup> implements FriendGroupDAO {
    @Override
    public List<FriendGroup> search(FriendGroupBean friendGroupBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FriendGroup.class);
        Integer friendNo = friendGroupBean.getFriendNo();
        Integer groupNo = friendGroupBean.getGroupNo();
        if (friendNo != null && friendNo != 0) {
            detachedCriteria.add(Restrictions.eq("friendNo", friendNo));
        }
        if (groupNo != null && groupNo != 0) {
            detachedCriteria.add(Restrictions.eq("groupNo", groupNo));
        }
        return (List<FriendGroup>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
