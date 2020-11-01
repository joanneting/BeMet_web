package tw.com.BeMet.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CodeTablePK implements Serializable {
    private String tableName;
    private String columnName;
    private int code;

    @Column(name = "table_name")
    @Id
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "column_name")
    @Id
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "code")
    @Id
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeTablePK that = (CodeTablePK) o;
        return code == that.code &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(columnName, that.columnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, columnName, code);
    }
}
