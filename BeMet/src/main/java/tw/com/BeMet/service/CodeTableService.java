package tw.com.BeMet.service;

import java.util.Map;

public interface CodeTableService {
    public Map<Integer,String> getCodeTableMap(String tableName,String columnName) throws Exception;
}
