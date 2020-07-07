package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.dao.UserInformationDAO;
import tw.com.business_meet.vo.UserInformation;

import java.util.List;

@Repository
public class UserInformationDAOImpl extends BaseDAOImpl<UserInformation> implements UserInformationDAO {
    @Override
    public List<UserInformation> search(UserInformationBean userInformationBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInformation.class);
        String blueTooth = userInformationBean.getBluetooth();
        String userId = userInformationBean.getUserId();
        String password = userInformationBean.getPassword();
        String gender = userInformationBean.getGender();
        String userName = userInformationBean.getName();
        String profession = userInformationBean.getProfession();
        String avatar = userInformationBean.getAvatar();
        String tel = userInformationBean.getTel();
        String mail = userInformationBean.getMail();
        if (blueTooth != null && !blueTooth.equals("")) {
            detachedCriteria.add(Restrictions.eq("bluetooth", blueTooth));
        }
        if (userName != null && !userName.equals("")) {
            detachedCriteria.add(Restrictions.eq("name", userName));
        }
        if (gender != null && !gender.equals("")) {
            detachedCriteria.add(Restrictions.eq("company", gender));
        }
        if (profession != null && !profession.equals("")) {
            detachedCriteria.add(Restrictions.eq("profession", profession));
        }
        if (avatar != null && !avatar.equals("")) {
            detachedCriteria.add(Restrictions.eq("avatar", avatar));
        }
        if (tel != null && !tel.equals("")) {
            detachedCriteria.add(Restrictions.eq("tel", tel));
        }
        if (mail != null && !mail.equals("")) {
            detachedCriteria.add(Restrictions.eq("mail", mail));
        }
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.eq("userId", userId));
        }
        return (List<UserInformation>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
