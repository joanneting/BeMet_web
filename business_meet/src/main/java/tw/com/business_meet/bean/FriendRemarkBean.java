package tw.com.business_meet.bean;

import java.util.Date;

public class FriendRemarkBean {
    private Integer friendRemarksNo;
    private Integer friendLabelNo;
    private Integer friendCustomizationNo;
    private Date createDate;
    private Date modifyDate;
    private Integer statusCode;

    public Integer getFriendRemarksNo() {
        return friendRemarksNo;
    }

    public void setFriendRemarksNo(Integer friendRemarksNo) {
        this.friendRemarksNo = friendRemarksNo;
    }

    public Integer getFriendLabelNo() {
        return friendLabelNo;
    }

    public void setFriendLabelNo(Integer friendLabelNo) {
        this.friendLabelNo = friendLabelNo;
    }

    public Integer getFriendCustomizationNo() {
        return friendCustomizationNo;
    }

    public void setFriendCustomizationNo(Integer friendCustomizationNo) {
        this.friendCustomizationNo = friendCustomizationNo;
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
