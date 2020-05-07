package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.MatchedBean;
import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.dao.MatchedDAO;
import tw.com.business_meet.service.MatchedService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.Matched;
import tw.com.business_meet.vo.UserInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class MatchedServiceImpl implements MatchedService {
    @Autowired
    MatchedDAO matchedDAO;
    @Override
    public List<MatchedBean> search(MatchedBean matchedBean) throws Exception {

        List<Matched> mList = matchedDAO.search(matchedBean);
        List<MatchedBean> mbList = new ArrayList<>();
        for (Matched m: mList) {
            MatchedBean mb = new MatchedBean();
            BeanUtility.copyProperties(m,mb);
            mb.setBlueTooth(m.getUserInformationByBlueTooth().getBlueTooth());
            mb.setMatchedBlueTooth(m.getUserInformationByMatchedBlueTooth().getBlueTooth());

            System.out.println("matched search data : " + mb.getBlueTooth());
            mbList.add(mb);
        }
        return mbList;
    }

    @Override
    public MatchedBean add(MatchedBean matchedBean) throws Exception {
        Matched m = new Matched();
        BeanUtility.copyProperties(matchedBean,m);
        m.setCreateDate(new Date());

        UserInformation ufbMy = new UserInformation();
        UserInformation ufbMatched = new UserInformation();

        ufbMy.setBlueTooth(matchedBean.getBlueTooth());
        ufbMatched.setBlueTooth(matchedBean.getMatchedBlueTooth());
        m.setUserInformationByBlueTooth(ufbMy);
        m.setUserInformationByMatchedBlueTooth(ufbMatched);
        Matched result = matchedDAO.saveAndReturn(m);
        BeanUtility.copyProperties(result,matchedBean);
        matchedBean.setMatchedBlueTooth(m.getUserInformationByMatchedBlueTooth().getBlueTooth());
        matchedBean.setBlueTooth(m.getUserInformationByBlueTooth().getBlueTooth());
        return matchedBean;
    }

    @Override
    public void update(MatchedBean matchedBean) throws Exception {
        Matched m = matchedDAO.getById(matchedBean.getMSno());
        BeanUtility.copyProperties(matchedBean,m);
        m.setModifyDate(new Date());
        matchedDAO.update(m);
    }

    @Override
    public List<MatchedBean> searchAll() throws Exception {
        List<Matched> matchedList = matchedDAO.searchAll();
        List<MatchedBean> matchedBeanList = new ArrayList<>();
        for (Matched m : matchedList){
            MatchedBean matchedBean = new MatchedBean();
            BeanUtility.copyProperties(m,matchedBean);
            matchedBean.setMatchedBlueTooth(m.getUserInformationByMatchedBlueTooth().getBlueTooth());
            matchedBean.setBlueTooth(m.getUserInformationByBlueTooth().getBlueTooth());

            matchedBeanList.add(matchedBean);
        }
        return matchedBeanList;
    }
}
