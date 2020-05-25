package tw.com.business_meet.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.MatchedBean;
import tw.com.business_meet.dao.MatchedDAO;
import tw.com.business_meet.vo.Matched;

import java.util.List;
@Repository
public class MatchedDAOImpl extends BaseDAOImpl<Matched> implements MatchedDAO {
    @Override
    public List<Matched> search(MatchedBean matchedBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Matched.class);
        String blueTooth = matchedBean.getBlueTooth();
        String matchedBlueTooth = matchedBean.getMatchedBlueTooth();
        String memorandum = matchedBean.getMemorandum();
        System.out.println("matched search bluetoth: " + blueTooth);
        System.out.println("matched search matchedBluetoth: " + matchedBlueTooth);
        if(blueTooth!=null && !blueTooth.equals("")){
            detachedCriteria.add(Restrictions.eq("userInformationByBlueTooth.blueTooth",blueTooth));
        }
        if(matchedBlueTooth!=null && !matchedBlueTooth.equals("")){
            detachedCriteria.add(Restrictions.eq("userInformationByMatchedBlueTooth.blueTooth",matchedBlueTooth));
        }
        if(memorandum!=null && !memorandum.equals("")){
            detachedCriteria.add(Restrictions.eq("memorandum",memorandum));
        }
        return (List<Matched>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
