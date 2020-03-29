package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.dao.UserInformationDAO;
import tw.com.business_meet.vo.UserInformation;

import java.io.Serializable;
import java.util.List;

@Repository
public class UserInformationDAOImpl extends BaseDAOImpl<UserInformation> implements UserInformationDAO {
    @Override
    public List<UserInformation> search(UserInformationBean userInformationBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInformation.class);
         String blueTooth = userInformationBean.getBlueTooth();
         String userName = userInformationBean.getUserName();
         String company = userInformationBean.getCompany();
         String position = userInformationBean.getPosition();
         String avatar = userInformationBean.getAvatar();
         String tel = userInformationBean.getTel();
         String email = userInformationBean.getEmail();
        if(blueTooth != null && !blueTooth.equals("")){
            detachedCriteria.add(Restrictions.eq("blueTooth",blueTooth));
        }
        if(userName != null && !userName.equals("")){
            detachedCriteria.add(Restrictions.eq("userName",userName));
        }
        if(company != null && !company.equals("")){
            detachedCriteria.add(Restrictions.eq("company",company));
        }
        if(position != null && !position.equals("")){
            detachedCriteria.add(Restrictions.eq("position",position));
        }
        if(avatar != null && !avatar.equals("")){
            detachedCriteria.add(Restrictions.eq("avatar",avatar));
        }
        if(tel != null && !tel.equals("")){
            detachedCriteria.add(Restrictions.eq("tel",tel));
        }
        if(email != null && !email.equals("")){
            detachedCriteria.add(Restrictions.eq("email",email));
        }

        return (List<UserInformation>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
