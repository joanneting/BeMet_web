package tw.com.BeMet.dao;

import tw.com.BeMet.bean.UserCustomizationBean;
import tw.com.BeMet.vo.UserCustomization;

import java.util.List;

public interface UserCustomizationDAO extends BaseDAO<UserCustomization> {
    public List<UserCustomization> search(UserCustomizationBean userCustomizationBean);
}
