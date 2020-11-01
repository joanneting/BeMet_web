package tw.com.BeMet.service;

import tw.com.BeMet.bean.FriendGroupBean;

import java.util.List;

public interface FriendGroupService {
    public FriendGroupBean add(FriendGroupBean friendGroupBean) throws Exception;

    public FriendGroupBean update(FriendGroupBean friendGroupBean) throws Exception;

    public List<FriendGroupBean> search(FriendGroupBean friendGroupBean) throws Exception;
    public List<FriendGroupBean> searchFriendByGroup(Integer groupNo) throws Exception;

    public List<FriendGroupBean> searchAll() throws Exception;

    public void delete(Integer friendGroupNo) throws Exception;
    
    public List<FriendGroupBean> searchGroupCount() throws  Exception;

}
