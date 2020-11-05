package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import tw.com.BeMet.bean.ProblemReportBean;
import tw.com.BeMet.dao.CodeTableDAO;
import tw.com.BeMet.dao.ProblemReportDAO;
import tw.com.BeMet.dao.UserInformationDAO;
import tw.com.BeMet.service.ProblemReportService;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.vo.ProblemReport;
import tw.com.BeMet.vo.UserInformation;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ProblemReportServiceImpl implements ProblemReportService {
    @Autowired
    ProblemReportDAO problemReportDAO;
	@Autowired
	CodeTableDAO codeTableDAO;
	@Autowired
	UserInformationDAO userInformationDAO;
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
	public List<ProblemReportBean> search(ProblemReportBean problemReportBean) {
		List<ProblemReport> problemReportList = problemReportDAO.search(problemReportBean);
		List<ProblemReportBean> problemReportBeanList = new ArrayList<>();
		Map<Integer, String> codeTableMap = codeTableDAO.getCodeTableMap("problem_report", "status");
		for (ProblemReport problemReport : problemReportList) {
			ProblemReportBean prb = new ProblemReportBean();
			BeanUtility.copyProperties(problemReport, prb);
			prb.setStatusStr(codeTableMap.get(prb.getStatus()));
			UserInformation user = problemReport.getUserInformationByUserId();
			prb.setUserName(user.getName());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			prb.setStartDateStr(prb.getStartDate()!=null?prb.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):null);
			prb.setEndDateStr(prb.getEndDate()!=null?prb.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):null);
			problemReportBeanList.add(prb);
		}
		return problemReportBeanList;
	}

    @Override
	public List<ProblemReportBean> searchAll() {
		List<ProblemReport> problemReportList = problemReportDAO.searchAll();
		List<ProblemReportBean> result = new ArrayList<>();
		Map<Integer, String> codeTableMap = codeTableDAO.getCodeTableMap("problem_report", "status");
		for (ProblemReport problemReport : problemReportList) {
			ProblemReportBean problemReportBean = new ProblemReportBean();
			BeanUtility.copyProperties(problemReport, problemReportBean);
			problemReportBean.setStatusStr(codeTableMap.get(problemReportBean.getStatus()));
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
        if(problemReportBean.getStatus()==2) {
        	problemReportBean.setStartDate(LocalDateTime.now());
        }else if(problemReportBean.getStatus()==3) {
        	problemReportBean.setEndDate(LocalDateTime.now());
        }
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

	@Override
	public ProblemReportBean getById(Integer problemReportNo) throws Exception {
		ProblemReport problemReport = problemReportDAO.getById(problemReportNo);
		Map<Integer, String> codeTableMap = codeTableDAO.getCodeTableMap("problem_report", "status");
		ProblemReportBean problemReportBean = new ProblemReportBean();
		BeanUtility.copyProperties(problemReport,problemReportBean);
		problemReportBean.setStatusStr(codeTableMap.get(problemReportBean.getStatus()));
		UserInformation userInformation = userInformationDAO.getById(problemReportBean.getUserId());
		problemReportBean.setUserName(userInformation.getName());
		return problemReportBean;
	}
}
