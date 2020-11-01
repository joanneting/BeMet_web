package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "friend", schema = "dbo", catalog = "BeMet")
public class Friend {
    private Integer friendNo;
    private String matchmakerId;
    private String friendId;
    private String remark;
    private Integer status;
    private Date createDate;
    private Date modifyDate;
    private Collection<FriendCustomization> friendCustomizationsByFriendNo;
    private Collection<FriendGroup> friendGroupsByFriendNo;
    private UserInformation userInformationByMatchmakerId;
    private UserInformation userInformationByFriendId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_no")
    public Integer getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(Integer friendNo) {
        this.friendNo = friendNo;
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
    @Column(name="status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
        result = 31 * result + (matchmakerId != null ? matchmakerId.hashCode() : 0);
        result = 31 * result + (friendId != null ? friendId.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchmaker_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    public UserInformation getUserInformationByMatchmakerId() {
        return userInformationByMatchmakerId;
    }

    public void setUserInformationByMatchmakerId(UserInformation userInformationByMatchmakerId) {
        this.userInformationByMatchmakerId = userInformationByMatchmakerId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    public UserInformation getUserInformationByFriendId() {
        return userInformationByFriendId;
    }

    public void setUserInformationByFriendId(UserInformation userInformationByFriendId) {
        this.userInformationByFriendId = userInformationByFriendId;
    }
}

