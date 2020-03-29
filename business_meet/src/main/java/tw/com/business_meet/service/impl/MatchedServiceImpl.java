package tw.com.business_meet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.business_meet.bean.MatchedBean;
import tw.com.business_meet.dao.MatchedDAO;
import tw.com.business_meet.service.MatchedService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.vo.Matched;
import tw.com.business_meet.vo.UserInformation;

import java.util.ArrayList;
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
            mbList.add(mb);
        }
        return mbList;
    }

    @Override
    public MatchedBean add(MatchedBean matchedBean) throws Exception {
        Matched m = new Matched();
        BeanUtility.copyProperties(matchedBean,m);
        Matched result = matchedDAO.saveAndReturn(m);
        BeanUtility.copyProperties(result,matchedBean);
        return matchedBean;
    }

    @Override
    public void update(MatchedBean matchedBean) throws Exception {
        Matched m = matchedDAO.getById(matchedBean.getMSno());
        BeanUtility.copyProperties(matchedBean,m);
        matchedDAO.update(m);
    }
}
