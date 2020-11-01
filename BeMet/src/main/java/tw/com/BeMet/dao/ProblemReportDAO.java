package tw.com.BeMet.dao;

import java.util.List;

import tw.com.BeMet.bean.ProblemReportBean;
import tw.com.BeMet.vo.ProblemReport;

public interface ProblemReportDAO extends BaseDAO<ProblemReport> {
	List<ProblemReport> search(ProblemReportBean condition);
}