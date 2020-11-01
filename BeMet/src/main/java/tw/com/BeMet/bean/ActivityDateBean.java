package tw.com.BeMet.bean;

import java.util.Date;

public class ActivityDateBean {
    private Integer activityDateNo;
    private Integer activityNo;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private Date modifyDate;

    public Integer getActivityDateNo() {
        return activityDateNo;
    }

    public void setActivityDateNo(Integer activityDateNo) {
        this.activityDateNo = activityDateNo;
    }

    public Integer getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(Integer activityNo) {
        this.activityNo = activityNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
