package tw.com.business_meet.bean;

import java.util.Date;

public class FriendBean {
    private Integer friendNo;
    private Integer matchmaker;
    private Integer friend;
    private String remark;
    private Date createDate;
    private Date modifyDate;

    public Integer getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(Integer friendNo) {
        this.friendNo = friendNo;
    }

    public Integer getMatchmaker() {
        return matchmaker;
    }

    public void setMatchmaker(Integer matchmaker) {
        this.matchmaker = matchmaker;
    }

    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
