package tw.com.business_meet.service;

import tw.com.business_meet.bean.MatchedBean;
import tw.com.business_meet.vo.Matched;

import java.util.List;

public interface MatchedService {
    public List<MatchedBean> search(MatchedBean matchedBean) throws Exception;
    public MatchedBean add(MatchedBean matchedBean) throws Exception;
    public void update(MatchedBean matchedBean) throws Exception;
}
