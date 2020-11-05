package tw.com.BeMet.dao;

import tw.com.BeMet.bean.FriendGroupBean;
import tw.com.BeMet.vo.FriendGroup;

import java.util.List;

public interface FriendGroupDAO extends BaseDAO<FriendGroup> {
    public List<FriendGroup> search(FriendGroupBean friendGroupBean);
    List<FriendGroupBean> searchGroupCount(FriendGroupBean friendGroupBean);
    List<FriendGroupBean> searchFriendByGroup(FriendGroupBean friendGroupBean);
}
