package tw.com.business_meet.vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
public class Groups {
    private Integer groupNo;
    private String name;
    private Integer userNo;
    private Date createDate;
    private Date modifyDate;
    private Collection<FriendGroup> friendGroupsByGroupNo;
    private UserInformation userInformationByUserNo;
    private String userId;
    private UserInformation userInformationByUserId;

    @Id
    @Column(name = "group_no")
    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "user_no")
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
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

        Groups groups = (Groups) o;

        if (groupNo != null ? !groupNo.equals(groups.groupNo) : groups.groupNo != null) {
            return false;
        }
        if (name != null ? !name.equals(groups.name) : groups.name != null) {
            return false;
        }
        if (userNo != null ? !userNo.equals(groups.userNo) : groups.userNo != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(groups.createDate) : groups.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(groups.modifyDate) : groups.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupNo != null ? groupNo.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "groupsByGroupNo")
    public Collection<FriendGroup> getFriendGroupsByGroupNo() {
        return friendGroupsByGroupNo;
    }

    public void setFriendGroupsByGroupNo(Collection<FriendGroup> friendGroupsByGroupNo) {
        this.friendGroupsByGroupNo = friendGroupsByGroupNo;
    }

    @ManyToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no", nullable = false)
    public UserInformation getUserInformationByUserNo() {
        return userInformationByUserNo;
    }

    public void setUserInformationByUserNo(UserInformation userInformationByUserNo) {
        this.userInformationByUserNo = userInformationByUserNo;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserInformation getUserInformationByUserId() {
        return userInformationByUserId;
    }

    public void setUserInformationByUserId(UserInformation userInformationByUserId) {
        this.userInformationByUserId = userInformationByUserId;
    }
}
