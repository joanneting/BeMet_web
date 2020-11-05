package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.bean.ProblemReportBean;
import tw.com.BeMet.dao.ProblemReportDAO;
import tw.com.BeMet.vo.ProblemReport;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class ProblemReportDAOImpl extends BaseDAOImpl<ProblemReport> implements ProblemReportDAO {

    @Override
    public List<ProblemReport> search(ProblemReportBean problemReportBean) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProblemReport.class);
        detachedCriteria.createAlias("userInformationByUserId", "userInformation");

        // SELECT * FROM ProblemReport;
        // 塞條件 -> WHERE
        if (problemReportBean != null) {
            if (problemReportBean.getUserId() != null && !problemReportBean.getUserId().isBlank()) {
                // SELECT * FROM ProblemReport WHERE userNo = 1
                detachedCriteria.add(Restrictions.eq("userId", problemReportBean.getUserId()));
            }
            if (problemReportBean.getUserName() != null && !problemReportBean.getUserName().isBlank()) {
                // SELECT * FROM ProblemReport WHERE userNo = 1
                detachedCriteria.add(Restrictions.eq("userInformation.name", problemReportBean.getUserName()));
            }
            System.out.println("problemReportBean.getContent() = " + problemReportBean.getContent());
            if (problemReportBean.getContent() != null && !problemReportBean.getContent().isBlank()) {
                System.out.println("problemReportBean.getContent().isBlank() = " + problemReportBean.getContent().isBlank());
                detachedCriteria.add(Restrictions.like("content", "%" + problemReportBean.getContent() + "%"));
            }

            if (problemReportBean.getStatus() != null) {
                detachedCriteria.add(Restrictions.eq("status", problemReportBean.getStatus()));
            }

            if (problemReportBean.getStartDate() != null && problemReportBean.getEndDate() != null) {

                detachedCriteria.add(Restrictions.ge(
                        "startDate",
                        problemReportBean.getStartDate()
                ));
                detachedCriteria.add(Restrictions.le(
                        "endDate",
                        problemReportBean.getEndDate().plusHours(23).plusMinutes(59)
                ));
            } else if (problemReportBean.getStartDate() != null) {
                detachedCriteria.add(Restrictions.ge(
                        "startDate",
                        problemReportBean.getStartDate()
                ));
            } else if (problemReportBean.getEndDate() != null) {
                detachedCriteria.add(Restrictions.lt(
                        "endDate",
                        problemReportBean.getEndDate().plusHours(23).plusMinutes(59)
                ));
            }
        }


        return (List<ProblemReport>) getHibernateTemplate().findByCriteria(detachedCriteria);
    }

}
