package tw.com.business_meet.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.com.business_meet.bean.ProblemReportBean;
import tw.com.business_meet.dao.ProblemReportDAO;
import tw.com.business_meet.vo.ProblemReport;

@Repository
@SuppressWarnings("unchecked")
public class ProblemReportDAOImpl extends BaseDAOImpl<ProblemReport> implements ProblemReportDAO {

	@Override
	public List<ProblemReport> search(ProblemReportBean condition) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProblemReport.class);
		
		// SELECT * FROM ProblemReport;
		// 塞條件 -> WHERE
		if (condition != null) { 
			if (condition.getUserId() != null && !condition.getUserId().isBlank()) {
				// SELECT * FROM ProblemReport WHERE userNo = 1
				detachedCriteria.add(Restrictions.eq("userId", condition.getUserId()));
			}
			
			if (condition.getContent() != null && !condition.getContent().isBlank()) {
				detachedCriteria.add(Restrictions.like("content", "%" + condition.getContent() + "%"));
			}
			
			if (condition.getStatus() != null) {
				detachedCriteria.add(Restrictions.eq("status", condition.getStatus()));
			}
			
			if (condition.getStartDate() != null && condition.getEndDate() != null) {
				
				detachedCriteria.add(Restrictions.between(
						"endDate",
						condition.getStartDate(),
						condition.getEndDate()
				));
			} else if(condition.getStartDate() != null) {
				detachedCriteria.add(Restrictions.ge(
						"startDate",
						condition.getStartDate()
				));
			} else if(condition.getEndDate() != null) {
				detachedCriteria.add(Restrictions.lt(
						"endDate",
						condition.getEndDate()
				));
				}
			}
		
		
		return (List<ProblemReport>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
