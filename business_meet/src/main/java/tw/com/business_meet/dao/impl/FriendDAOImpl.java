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
        String matchmaker = friendBean.getMatchmakerId();
        String friend = friendBean.getFriendId();
        String remark = friendBean.getRemark();
        System.out.println("matched search bluetoth: " + matchmaker);
        System.out.println("matched search matchedBluetoth: " + friend);
        if (matchmaker != null && !matchmaker.equals("")) {
            detachedCriteria.add(Restrictions.eq("matchmaker", matchmaker));
        }
        if (friend != null && !friend.equals("")) {
            detachedCriteria.add(Restrictions.eq("friend", friend));
        }
        if (remark != null && !remark.equals("")) {
            detachedCriteria.add(Restrictions.eq("memorandum", remark));
        }
        return (List<Friend>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
