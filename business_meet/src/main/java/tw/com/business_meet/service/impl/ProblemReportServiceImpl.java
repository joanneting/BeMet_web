package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.ProblemReportBean;
import tw.com.business_meet.dao.ProblemReportDAO;
import tw.com.business_meet.service.ProblemReportService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.ProblemReport;
import tw.com.business_meet.vo.UserInformation;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemReportServiceImpl implements ProblemReportService {
    @Autowired
    ProblemReportDAO problemReportDAO;

    @Override
    public  ProblemReportBean add(ProblemReportBean problemReportBean) {
		ProblemReport problemReport = new ProblemReport();
		UserInformation userinformation = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BeanUtility.copyProperties(problemReportBean, problemReport);
		problemReport.setUserId(userinformation.getUserId());
		//problemReport.setStartDate(LocalDateTime.now());
		problemReport.setCreateDate(LocalDateTime.now());
		//problemReport.setEndDate(LocalDateTime.now());
		problemReport.setStatus(1);
		problemReport = problemReportDAO.saveAndReturn(problemReport);
		BeanUtility.copyProperties(problemReport, problemReportBean);
		return problemReportBean;
	}

    @Override
	public List<ProblemReportBean> search(ProblemReportBean condition) {
		List<ProblemReport> problemReportList = problemReportDAO.search(condition);
		List<ProblemReportBean> result = new ArrayList<>();
		for (ProblemReport problemReport : problemReportList) {
			ProblemReportBean problemReportBean = new ProblemReportBean();
			BeanUtility.copyProperties(problemReport, problemReportBean);
			
			UserInformation user = problemReport.getUserInformationByUserId();
			problemReportBean.setUserName(user.getName());
			
			result.add(problemReportBean);
		}
		return result;
	}

    @Override
	public List<ProblemReportBean> searchAll() {
		List<ProblemReport> problemReportList = problemReportDAO.searchAll();
		List<ProblemReportBean> result = new ArrayList<>();
		for (ProblemReport problemReport : problemReportList) {
			ProblemReportBean problemReportBean = new ProblemReportBean();
			BeanUtility.copyProperties(problemReport, problemReportBean);
			
			UserInformation user = problemReport.getUserInformationByUserId();
			problemReportBean.setUserName(user.getName());
			
			result.add(problemReportBean);
		}
		return result;
	}
    
    
    @Override
    public ProblemReportBean update(ProblemReportBean problemReportBean) throws Exception {
        ProblemReport problemReport = problemReportDAO.getById(problemReportBean.getProblemReportNo());
        //if status ==1 更改modifyDate  else if status==2 更改modifyDate startDate else(status==3) 更改 modifyDate endDate
        //problemReportBean.setStatus(1);
        Integer count = 0;
        if(problemReportBean.getStatus()==2 && count==0) {count=count+1;problemReportBean.setStartDate(LocalDateTime.now());}
        if(problemReportBean.getStatus()==3) {problemReportBean.setEndDate(LocalDateTime.now());}
        problemReportBean.setModifyDate(LocalDateTime.now());
        BeanUtility.copyProperties(problemReportBean, problemReport);
        problemReportDAO.update(problemReport);
        BeanUtility.copyProperties(problemReport, problemReportBean);
        return problemReportBean;
    }
    
    @Override
    public void delete(Integer problemReportNo) throws Exception {
        ProblemReport problemReport = problemReportDAO.getById(problemReportNo);
        problemReportDAO.delete(problemReport);
    }
    
}
