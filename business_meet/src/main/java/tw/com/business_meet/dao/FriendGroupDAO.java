package tw.com.business_meet.dao;

import tw.com.business_meet.bean.FriendGroupBean;
import tw.com.business_meet.vo.FriendGroup;

import java.util.List;

public interface FriendGroupDAO extends BaseDAO<FriendGroup> {
    public List<FriendGroup> search(FriendGroupBean friendGroupBean);
}
