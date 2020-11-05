package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "code_table", schema = "dbo", catalog = "BeMet")
@IdClass(CodeTablePK.class)
public class CodeTable {
    private String tableName;
    private String columnName;
    private Integer code;
    private String value;
    private String status;
    private Date createDate;
    private Date modifyDate;

    @Id
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Id
    @Column(name = "column_name")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeTable codeTable = (CodeTable) o;
        return code == codeTable.code &&
                Objects.equals(tableName, codeTable.tableName) &&
                Objects.equals(columnName, codeTable.columnName) &&
                Objects.equals(value, codeTable.value) &&
                Objects.equals(status, codeTable.status) &&
                Objects.equals(createDate, codeTable.createDate) &&
                Objects.equals(modifyDate, codeTable.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, columnName, code, value, status, createDate, modifyDate);
    }
}
