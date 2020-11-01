package tw.com.BeMet.bean;

import java.util.Date;

public class UserCustomizationBean {
    private Integer userCustomizationNo;
    private String userId;
    private String columnName;
    private String content;
    private Date createDate;
    private Date modifyDate;
    private Integer statusCode;

    public Integer getUserCustomizationNo() {
        return userCustomizationNo;
    }

    public void setUserCustomizationNo(Integer userCustomizationNo) {
        this.userCustomizationNo = userCustomizationNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
