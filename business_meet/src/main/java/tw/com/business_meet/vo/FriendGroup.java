package tw.com.business_meet.vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "friend_group", schema = "dbo", catalog = "BeMet")
public class FriendGroup {
    private Integer friendGroupNo;
    private Integer groupNo;
    private Integer friendNo;
    private Date createDate;
    private Date modifyDate;
    private Groups groupsByGroupNo;
    private Friend friendByFriendNo;

    @Id
    @Column(name = "friendGroup_no")
    public Integer getFriendGroupNo() {
        return friendGroupNo;
    }

    public void setFriendGroupNo(Integer friendGroupNo) {
        this.friendGroupNo = friendGroupNo;
    }

    @Basic
    @Column(name = "group_no")
    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }

    @Basic
    @Column(name = "friend_no")
    public Integer getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(Integer friendNo) {
        this.friendNo = friendNo;
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

        FriendGroup that = (FriendGroup) o;

        if (friendGroupNo != null ? !friendGroupNo.equals(that.friendGroupNo) : that.friendGroupNo != null) {
            return false;
        }
        if (groupNo != null ? !groupNo.equals(that.groupNo) : that.groupNo != null) {
            return false;
        }
        if (friendNo != null ? !friendNo.equals(that.friendNo) : that.friendNo != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendGroupNo != null ? friendGroupNo.hashCode() : 0;
        result = 31 * result + (groupNo != null ? groupNo.hashCode() : 0);
        result = 31 * result + (friendNo != null ? friendNo.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "group_no", referencedColumnName = "group_no", nullable = false)
    public Groups getGroupsByGroupNo() {
        return groupsByGroupNo;
    }

    public void setGroupsByGroupNo(Groups groupsByGroupNo) {
        this.groupsByGroupNo = groupsByGroupNo;
    }

    @ManyToOne
    @JoinColumn(name = "friend_no", referencedColumnName = "friend_no", nullable = false)
    public Friend getFriendByFriendNo() {
        return friendByFriendNo;
    }

    public void setFriendByFriendNo(Friend friendByFriendNo) {
        this.friendByFriendNo = friendByFriendNo;
    }
}
