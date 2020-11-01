package tw.com.BeMet.dao;

import tw.com.BeMet.bean.FriendCustomizationBean;
import tw.com.BeMet.vo.FriendCustomization;

import java.util.List;

public interface FriendCustomizationDAO extends BaseDAO<FriendCustomization> {
    public List<FriendCustomization> search(FriendCustomizationBean friendCustomizationBean);
}
