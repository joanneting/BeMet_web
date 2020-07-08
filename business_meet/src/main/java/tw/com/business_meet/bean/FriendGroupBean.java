package tw.com.business_meet.bean;

import java.util.Date;

public class FriendGroupBean {
    private Integer friendGroupNo;
    private Integer groupNo;
    private Integer friendNo;
    private Date createDate;
    private Date modifyDate;

    public Integer getFriendGroupNo() {
        return friendGroupNo;
    }

    public void setFriendGroupNo(Integer friendGroupNo) {
        this.friendGroupNo = friendGroupNo;
    }

    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }

    public Integer getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(Integer friendNo) {
        this.friendNo = friendNo;
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
