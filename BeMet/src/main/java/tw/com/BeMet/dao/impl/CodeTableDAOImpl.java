package tw.com.BeMet.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.BeMet.dao.CodeTableDAO;
import tw.com.BeMet.vo.CodeTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CodeTableDAOImpl extends BaseDAOImpl<CodeTable> implements CodeTableDAO {
    @Override
    public Map<Integer, String> getCodeTableMap(String tableName, String columnName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeTable.class);
        detachedCriteria.add(Restrictions.eq("tableName",tableName));
        detachedCriteria.add(Restrictions.eq("columnName",columnName));
        detachedCriteria.add(Restrictions.eq("status","Y"));
        List<CodeTable> codeTableList = (List<CodeTable>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Map<Integer,String> codeTableMap = new HashMap<>();
        for (CodeTable codeTable : codeTableList) {
            codeTableMap.put(codeTable.getCode(),codeTable.getValue());
        }
        return codeTableMap;
    }
}
