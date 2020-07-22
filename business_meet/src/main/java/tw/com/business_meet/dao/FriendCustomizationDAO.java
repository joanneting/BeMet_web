package tw.com.business_meet.dao;

import tw.com.business_meet.bean.FriendCustomizationBean;
import tw.com.business_meet.vo.FriendCustomization;

import java.util.List;

public interface FriendCustomizationDAO extends BaseDAO<FriendCustomization> {
    public List<FriendCustomization> search(FriendCustomizationBean friendCustomizationBean);
}
