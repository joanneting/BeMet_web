package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity_invite", schema = "dbo", catalog = "BeMet")
public class ActivityInvite {
    private Integer activityInviteNo;
    private String userId;
    private Integer activityNo;
    private Integer status;
    private Date createDate;
    private Date modifyDate;
    private UserInformation userInformationByUserId;
    private Timeline timelineByActivityNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activityInvite_no")
    public Integer getActivityInviteNo() {
        return activityInviteNo;
    }

    public void setActivityInviteNo(Integer activityInviteNo) {
        this.activityInviteNo = activityInviteNo;
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
    @Column(name = "activity_no")
    public Integer getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(Integer activityNo) {
        this.activityNo = activityNo;
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

        ActivityInvite that = (ActivityInvite) o;

        if (activityInviteNo != null ? !activityInviteNo.equals(that.activityInviteNo) : that.activityInviteNo != null) {
            return false;
        }
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
            return false;
        }
        if (activityNo != null ? !activityNo.equals(that.activityNo) : that.activityNo != null) {
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
        int result = activityInviteNo != null ? activityInviteNo.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (activityNo != null ? activityNo.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    public UserInformation getUserInformationByUserId() {
        return userInformationByUserId;
    }

    public void setUserInformationByUserId(UserInformation userInformationByUserId) {
        this.userInformationByUserId = userInformationByUserId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_no", referencedColumnName = "timeline_no", nullable = false, insertable = false, updatable = false)
    public Timeline getTimelineByActivityNo() {
        return timelineByActivityNo;
    }

    public void setTimelineByActivityNo(Timeline timelineByActivityNo) {
        this.timelineByActivityNo = timelineByActivityNo;
    }
}
