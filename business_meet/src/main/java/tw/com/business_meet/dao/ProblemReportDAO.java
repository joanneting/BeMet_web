package tw.com.business_meet.dao;

import java.util.List;

import tw.com.business_meet.bean.ProblemReportBean;
import tw.com.business_meet.vo.ProblemReport;

public interface ProblemReportDAO extends BaseDAO<ProblemReport> {
	List<ProblemReport> search(ProblemReportBean condition);
}