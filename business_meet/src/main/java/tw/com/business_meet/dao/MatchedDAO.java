package tw.com.business_meet.dao;

import tw.com.business_meet.bean.MatchedBean;
import tw.com.business_meet.vo.Matched;

import java.util.List;

public interface MatchedDAO extends BaseDAO<Matched> {
    public List<Matched> search(MatchedBean matchedBean);
}
