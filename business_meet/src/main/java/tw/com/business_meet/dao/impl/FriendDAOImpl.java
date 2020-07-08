package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.FriendBean;
import tw.com.business_meet.dao.FriendDAO;
import tw.com.business_meet.vo.Friend;

import java.util.List;

@Repository
public class FriendDAOImpl extends BaseDAOImpl<Friend> implements FriendDAO {
    @Override
    public List<Friend> search(FriendBean friendBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Friend.class);
        String matchmakerId = friendBean.getMatchmakerId();
        String friendId = friendBean.getFriendId();
        String remark = friendBean.getRemark();
        System.out.println("matched search matchmakerId: " + matchmakerId);
        System.out.println("matched search friendId: " + friendId);
        if (matchmakerId != null && !matchmakerId.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmakerId", matchmakerId));
        }
        if (friendId != null && !friendId.equals("")) {
            detachedCriteria.add(Restrictions.eq("friendId", friendId));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.eq("remark", remark));
        }
        return (List<Friend>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
