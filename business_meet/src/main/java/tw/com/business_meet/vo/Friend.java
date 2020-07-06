package tw.com.business_meet.vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
public class Friend {
    private Integer friendNo;
    private Integer matchmaker;
    private Integer friend;
    private String remark;
    private Date createDate;
    private Date modifyDate;
    private UserInformation userInformationByMatchmaker;
    private UserInformation userInformationByFriend;
    private Collection<FriendCustomization> friendCustomizationsByFriendNo;
    private Collection<FriendGroup> friendGroupsByFriendNo;
    private String matchmakerId;
    private String friendId;
    private UserInformation userInformationByMatchmakerId;
    private UserInformation userInformationByFriendId;

    @Id
    @Column(name = "friend_no")
    public Integer getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(Integer friendNo) {
        this.friendNo = friendNo;
    }

    @Basic
    @Column(name = "matchmaker")
    public Integer getMatchmaker() {
        return matchmaker;
    }

    public void setMatchmaker(Integer matchmaker) {
        this.matchmaker = matchmaker;
    }

    @Basic
    @Column(name = "friend")
    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Friend friend1 = (Friend) o;

        if (friendNo != null ? !friendNo.equals(friend1.friendNo) : friend1.friendNo != null) {
            return false;
        }
        if (matchmaker != null ? !matchmaker.equals(friend1.matchmaker) : friend1.matchmaker != null) {
            return false;
        }
        if (friend != null ? !friend.equals(friend1.friend) : friend1.friend != null) {
            return false;
        }
        if (remark != null ? !remark.equals(friend1.remark) : friend1.remark != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(friend1.createDate) : friend1.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(friend1.modifyDate) : friend1.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendNo != null ? friendNo.hashCode() : 0;
        result = 31 * result + (matchmaker != null ? matchmaker.hashCode() : 0);
        result = 31 * result + (friend != null ? friend.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "matchmaker", referencedColumnName = "user_no", nullable = false)
    public UserInformation getUserInformationByMatchmaker() {
        return userInformationByMatchmaker;
    }

    public void setUserInformationByMatchmaker(UserInformation userInformationByMatchmaker) {
        this.userInformationByMatchmaker = userInformationByMatchmaker;
    }

    @ManyToOne
    @JoinColumn(name = "friend", referencedColumnName = "user_no", nullable = false)
    public UserInformation getUserInformationByFriend() {
        return userInformationByFriend;
    }

    public void setUserInformationByFriend(UserInformation userInformationByFriend) {
        this.userInformationByFriend = userInformationByFriend;
    }

    @OneToMany(mappedBy = "friendByFriendNo")
    public Collection<FriendCustomization> getFriendCustomizationsByFriendNo() {
        return friendCustomizationsByFriendNo;
    }

    public void setFriendCustomizationsByFriendNo(Collection<FriendCustomization> friendCustomizationsByFriendNo) {
        this.friendCustomizationsByFriendNo = friendCustomizationsByFriendNo;
    }

    @OneToMany(mappedBy = "friendByFriendNo")
    public Collection<FriendGroup> getFriendGroupsByFriendNo() {
        return friendGroupsByFriendNo;
    }

    public void setFriendGroupsByFriendNo(Collection<FriendGroup> friendGroupsByFriendNo) {
        this.friendGroupsByFriendNo = friendGroupsByFriendNo;
    }

    @Basic
    @Column(name = "matchmaker_id")
    public String getMatchmakerId() {
        return matchmakerId;
    }

    public void setMatchmakerId(String matchmakerId) {
        this.matchmakerId = matchmakerId;
    }

    @Basic
    @Column(name = "friend_id")
    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @ManyToOne
    @JoinColumn(name = "matchmaker_id", referencedColumnName = "user_id", nullable = false)
    public UserInformation getUserInformationByMatchmakerId() {
        return userInformationByMatchmakerId;
    }

    public void setUserInformationByMatchmakerId(UserInformation userInformationByMatchmakerId) {
        this.userInformationByMatchmakerId = userInformationByMatchmakerId;
    }

    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "user_id", nullable = false)
    public UserInformation getUserInformationByFriendId() {
        return userInformationByFriendId;
    }

    public void setUserInformationByFriendId(UserInformation userInformationByFriendId) {
        this.userInformationByFriendId = userInformationByFriendId;
    }
}
