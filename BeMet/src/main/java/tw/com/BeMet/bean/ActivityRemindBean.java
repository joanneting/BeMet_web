package tw.com.BeMet.bean;

import java.util.Date;

public class ActivityRemindBean {
    private Integer activityRemindNo;
    private Date time;
    private Integer activityNo;
    private Date createDate;
    private Date modifyDate;
    private Integer statusCode;

    public Integer getActivityRemindNo() {
        return activityRemindNo;
    }

    public void setActivityRemindNo(Integer activityRemindNo) {
        this.activityRemindNo = activityRemindNo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(Integer activityNo) {
        this.activityNo = activityNo;
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
