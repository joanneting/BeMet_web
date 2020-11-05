package tw.com.BeMet.service;

import tw.com.BeMet.bean.ProblemReportBean;
import tw.com.BeMet.vo.ProblemReport;

import java.util.List;

public interface ProblemReportService {
	public ProblemReportBean add(ProblemReportBean problemReportBean);

    public List<ProblemReportBean> searchAll();

    public List<ProblemReportBean> search(ProblemReportBean condition);
    
    public void delete(Integer problemReportNo) throws Exception;
    
    public ProblemReportBean update(ProblemReportBean problemReportBean) throws Exception;

    public ProblemReportBean getById(Integer problemReportNo) throws Exception;
}
