package tw.com.business_meet.dao;

import tw.com.business_meet.bean.FriendLabelBean;
import tw.com.business_meet.vo.FriendLabel;

import java.util.List;

public interface FriendLabelDAO extends BaseDAO<FriendLabel> {
    public List<FriendLabel> search(FriendLabelBean friendLabelBean);
}
