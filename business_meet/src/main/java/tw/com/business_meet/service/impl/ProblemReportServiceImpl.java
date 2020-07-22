package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.ProblemReportBean;
import tw.com.business_meet.dao.ProblemReportDAO;
import tw.com.business_meet.service.ProblemReportService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.ProblemReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProblemReportServiceImpl implements ProblemReportService {
    @Autowired
    ProblemReportDAO problemReportDAO;

    @Override
    public ProblemReportBean add(ProblemReportBean problemReportBean) throws Exception {
        ProblemReport problemReport = new ProblemReport();
        problemReportBean.setCreateDate(new Date());
        BeanUtility.copyProperties(problemReportBean, problemReport);
        problemReport = problemReportDAO.saveAndReturn(problemReport);
        BeanUtility.copyProperties(problemReport, problemReportBean);
        return problemReportBean;
    }

    @Override
    public ProblemReportBean update(ProblemReportBean problemReportBean) throws Exception {
        ProblemReport problemReport = problemReportDAO.getById(problemReportBean.getProblemReportNo());
        problemReportBean.setCreateDate(new Date());
        BeanUtility.copyProperties(problemReportBean, problemReport);
        problemReportDAO.update(problemReport);
        BeanUtility.copyProperties(problemReport, problemReportBean);
        return problemReportBean;
    }

    @Override
    public List<ProblemReportBean> search(ProblemReportBean problemReportBean) throws Exception {
        List<ProblemReport> problemReportList = problemReportDAO.search(problemReportBean);
        List<ProblemReportBean> problemReportBeanList = new ArrayList<>();
        for (ProblemReport problemReport :
                problemReportList) {
            ProblemReportBean prb = new ProblemReportBean();
            BeanUtility.copyProperties(problemReport, prb);
            problemReportBeanList.add(prb);
        }
        return problemReportBeanList;
    }

    @Override
    public List<ProblemReportBean> searchAll() throws Exception {
        List<ProblemReport> problemReportList = problemReportDAO.searchAll();
        List<ProblemReportBean> problemReportBeanList = new ArrayList<>();
        for (ProblemReport problemReport :
                problemReportList) {
            ProblemReportBean prb = new ProblemReportBean();
            BeanUtility.copyProperties(problemReport, prb);
            problemReportBeanList.add(prb);
        }
        return problemReportBeanList;
    }

    @Override
    public void delete(Integer problemReportNo) throws Exception {
        ProblemReport problemReport = problemReportDAO.getById(problemReportNo);
        problemReportDAO.delete(problemReport);
    }
}
