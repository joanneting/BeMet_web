package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Groups {
    private Integer groupNo;
    private String name;
    private String userId;
    private Date createDate;
    private Date modifyDate;
    private Collection<FriendGroup> friendGroupsByGroupNo;
    private UserInformation userInformationByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

        Groups groups = (Groups) o;

        if (groupNo != null ? !groupNo.equals(groups.groupNo) : groups.groupNo != null) {
            return false;
        }
        if (name != null ? !name.equals(groups.name) : groups.name != null) {
            return false;
        }
        if (userId != null ? !userId.equals(groups.userId) : groups.userId != null) {
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
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    public UserInformation getUserInformationByUserId() {
        return userInformationByUserId;
    }

    public void setUserInformationByUserId(UserInformation userInformationByUserId) {
        this.userInformationByUserId = userInformationByUserId;
    }
}
