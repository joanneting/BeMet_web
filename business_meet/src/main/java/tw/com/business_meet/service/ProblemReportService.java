package tw.com.business_meet.service;

import tw.com.business_meet.bean.ProblemReportBean;

import java.util.List;

public interface ProblemReportService {
    public ProblemReportBean add(ProblemReportBean problemReportBean) throws Exception;

    public ProblemReportBean update(ProblemReportBean problemReportBean) throws Exception;

    public List<ProblemReportBean> search(ProblemReportBean problemReportBean) throws Exception;

    public List<ProblemReportBean> searchAll() throws Exception;

    public void delete(Integer problemReportNo) throws Exception;

}
