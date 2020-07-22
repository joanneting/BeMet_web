package tw.com.business_meet.dao;

import tw.com.business_meet.bean.FriendRemarkBean;
import tw.com.business_meet.vo.FriendRemark;

import java.util.List;

public interface FriendRemarkDAO extends BaseDAO<FriendRemark> {
    public List<FriendRemark> search(FriendRemarkBean friendRemarkBean);
}
