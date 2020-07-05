package tw.com.business_meet.bean;

import java.util.Date;

public class UserCustomizationBean {
    private Integer userCustomizationNo;
    private Integer userNo;
    private String columnName;
    private String content;
    private Date createDate;
    private Date modifyDate;

    public Integer getUserCustomizationNo() {
        return userCustomizationNo;
    }

    public void setUserCustomizationNo(Integer userCustomizationNo) {
        this.userCustomizationNo = userCustomizationNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
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
}
