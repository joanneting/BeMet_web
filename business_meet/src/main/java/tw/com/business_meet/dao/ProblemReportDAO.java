package tw.com.business_meet.dao;

import tw.com.business_meet.bean.ProblemReportBean;
import tw.com.business_meet.vo.ProblemReport;

import java.util.List;

public interface ProblemReportDAO extends BaseDAO<ProblemReport> {
    public List<ProblemReport> search(ProblemReportBean problemReportBean);
}
