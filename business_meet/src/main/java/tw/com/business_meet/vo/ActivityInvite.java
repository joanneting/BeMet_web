package tw.com.business_meet.vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "activity_invite", schema = "dbo", catalog = "BeMet")
public class ActivityInvite {
    private Integer activityInviteNo;
    private Integer userNo;
    private Integer activityNo;
    private Date createDate;
    private Date modifyDate;
    private UserInformation userInformationByUserNo;
    private Timeline timelineByActivityNo;
    private String userId;
    private UserInformation userInformationByUserId;

    @Id
    @Column(name = "activityInvite_no")
    public Integer getActivityInviteNo() {
        return activityInviteNo;
    }

    public void setActivityInviteNo(Integer activityInviteNo) {
        this.activityInviteNo = activityInviteNo;
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
    @Column(name = "activity_no")
    public Integer getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(Integer activityNo) {
        this.activityNo = activityNo;
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

        ActivityInvite that = (ActivityInvite) o;

        if (activityInviteNo != null ? !activityInviteNo.equals(that.activityInviteNo) : that.activityInviteNo != null) {
            return false;
        }
        if (userNo != null ? !userNo.equals(that.userNo) : that.userNo != null) {
            return false;
        }
        if (!Objects.equals(activityNo, that.activityNo)) {
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
        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
        result = 31 * result + (activityNo != null ? activityNo.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no", nullable = false)
    public UserInformation getUserInformationByUserNo() {
        return userInformationByUserNo;
    }

    public void setUserInformationByUserNo(UserInformation userInformationByUserNo) {
        this.userInformationByUserNo = userInformationByUserNo;
    }

    @ManyToOne
    @JoinColumn(name = "activity_no", referencedColumnName = "timeline_no", nullable = false)
    public Timeline getTimelineByActivityNo() {
        return timelineByActivityNo;
    }

    public void setTimelineByActivityNo(Timeline timelineByActivityNo) {
        this.timelineByActivityNo = timelineByActivityNo;
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
