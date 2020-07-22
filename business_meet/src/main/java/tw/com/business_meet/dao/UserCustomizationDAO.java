package tw.com.business_meet.dao;

import tw.com.business_meet.bean.UserCustomizationBean;
import tw.com.business_meet.vo.UserCustomization;

import java.util.List;

public interface UserCustomizationDAO extends BaseDAO<UserCustomization> {
    public List<UserCustomization> search(UserCustomizationBean userCustomizationBean);
}
