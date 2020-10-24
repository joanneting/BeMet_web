package tw.com.business_meet.service;

import tw.com.business_meet.bean.ProblemReportBean;

import java.util.List;

public interface ProblemReportService {
	public ProblemReportBean add(ProblemReportBean problemReportBean);

    public List<ProblemReportBean> searchAll();

    public List<ProblemReportBean> search(ProblemReportBean condition);
    
    public void delete(Integer problemReportNo) throws Exception;
    
    public ProblemReportBean update(ProblemReportBean problemReportBean) throws Exception;
}
