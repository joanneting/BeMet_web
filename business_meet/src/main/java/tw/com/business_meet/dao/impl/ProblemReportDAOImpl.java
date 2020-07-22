package tw.com.business_meet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.business_meet.bean.ProblemReportBean;
import tw.com.business_meet.dao.ProblemReportDAO;
import tw.com.business_meet.vo.ProblemReport;

import java.util.List;

@Repository
public class ProblemReportDAOImpl extends BaseDAOImpl<ProblemReport> implements ProblemReportDAO {
    @Override
    public List<ProblemReport> search(ProblemReportBean problemReportBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProblemReport.class);
        String content = problemReportBean.getContent();
        String userId = problemReportBean.getUserId();
        if (content != null && !content.equals("")) {
            detachedCriteria.add(Restrictions.like("content", "%" + content + "%"));
        }
        if (userId != null && !userId.equals("")) {
            detachedCriteria.add(Restrictions.like("userId", "%" + userId + "%"));
        }
        return (List<ProblemReport>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
