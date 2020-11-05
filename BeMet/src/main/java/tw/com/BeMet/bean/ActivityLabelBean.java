package tw.com.BeMet.bean;

import java.util.Date;

public class ActivityLabelBean {
    private Integer activityLabelNo;
    private Integer activityNo;
    private String content;
    private Date createDate;
    private Date modifyDate;
    private Integer statusCode;

    public Integer getActivityLabelNo() {
        return activityLabelNo;
    }

    public void setActivityLabelNo(Integer activityLabelNo) {
        this.activityLabelNo = activityLabelNo;
    }

    public Integer getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(Integer activityNo) {
        this.activityNo = activityNo;
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
