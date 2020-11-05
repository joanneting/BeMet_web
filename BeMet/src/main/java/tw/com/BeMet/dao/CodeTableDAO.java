package tw.com.BeMet.dao;

import tw.com.BeMet.vo.CodeTable;

import java.util.Map;

public interface CodeTableDAO extends BaseDAO<CodeTable> {
    Map<Integer,String> getCodeTableMap(String tableName, String columnName);
}
