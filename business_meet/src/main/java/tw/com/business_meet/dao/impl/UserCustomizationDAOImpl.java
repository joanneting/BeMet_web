package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.UserCustomizationBean;
import tw.com.business_meet.dao.UserCustomizationDAO;
import tw.com.business_meet.vo.UserCustomization;

import java.util.List;

@Repository
public class UserCustomizationDAOImpl extends BaseDAOImpl<UserCustomization> implements UserCustomizationDAO {
    @Override
    public List<UserCustomization> search(UserCustomizationBean userCustomizationBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserCustomization.class);
        String columnName = userCustomizationBean.getColumnName();
        String content = userCustomizationBean.getContent();
        String userId = userCustomizationBean.getUserId();
        if (columnName != null && !columnName.equals("")) {
            detachedCriteria.add(Restrictions.like("columnName", "%" + columnName + "%"));
        }
        if (content != null && !content.equals("")) {
            detachedCriteria.add(Restrictions.like("content", "%" + content + "%"));
        }
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.like("userId", "%" + userId + "%"));
        }

        return (List<UserCustomization>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
