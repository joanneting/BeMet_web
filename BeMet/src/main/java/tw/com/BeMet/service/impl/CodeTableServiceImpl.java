package tw.com.BeMet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.BeMet.dao.CodeTableDAO;
import tw.com.BeMet.service.CodeTableService;

import java.util.Map;

@Service
public class CodeTableServiceImpl implements CodeTableService {
    @Autowired
    CodeTableDAO codeTableDAO;
    @Override
    public Map<Integer, String> getCodeTableMap(String tableName, String columnName) throws Exception {
        return codeTableDAO.getCodeTableMap(tableName,columnName);
    }
}
