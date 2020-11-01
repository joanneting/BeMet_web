package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Timeline {
    private Integer timelineNo;
    private String matchmakerId;
    private String friendId;
    private String place;
    private String title;
    private String remark;
    private Integer timelinePropertiesNo;
    private String color;
    private Date createDate;
    private Date modifyDate;
    private Collection<ActivityInvite> activityInvitesByTimelineNo;
    private Collection<ActivityLabel> activityLabelsByTimelineNo;
    private Collection<ActivityRemind> activityRemindsByTimelineNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeline_no")
    public Integer getTimelineNo() {
        return timelineNo;
    }

    public void setTimelineNo(Integer timelineNo) {
        this.timelineNo = timelineNo;
    }

    @Column(name = "matchmaker_id", nullable = false)
    public String getMatchmakerId() {
        return matchmakerId;
    }

    public void setMatchmakerId(String matchmakerId) {
        this.matchmakerId = matchmakerId;
    }

    @Column(name = "friend_id", nullable = true)
    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "timeline_properties_no")
    public Integer getTimelinePropertiesNo() {
        return timelinePropertiesNo;
    }

    public void setTimelinePropertiesNo(Integer timelinePropertiesNo) {
        this.timelinePropertiesNo = timelinePropertiesNo;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

        Timeline timeline = (Timeline) o;

        if (timelineNo != null ? !timelineNo.equals(timeline.timelineNo) : timeline.timelineNo != null) {
            return false;
        }
        if (place != null ? !place.equals(timeline.place) : timeline.place != null) {
            return false;
        }
        if (title != null ? !title.equals(timeline.title) : timeline.title != null) {
            return false;
        }
        if (remark != null ? !remark.equals(timeline.remark) : timeline.remark != null) {
            return false;
        }
        if (timelinePropertiesNo != null ? !timelinePropertiesNo.equals(timeline.timelinePropertiesNo) : timeline.timelinePropertiesNo != null) {
            return false;
        }
        if (color != null ? !color.equals(timeline.color) : timeline.color != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(timeline.createDate) : timeline.createDate != null) {
            return false;
        }
        if (modifyDate != null ? !modifyDate.equals(timeline.modifyDate) : timeline.modifyDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = timelineNo != null ? timelineNo.hashCode() : 0;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (timelinePropertiesNo != null ? timelinePropertiesNo.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "timelineByActivityNo")
    public Collection<ActivityInvite> getActivityInvitesByTimelineNo() {
        return activityInvitesByTimelineNo;
    }

    public void setActivityInvitesByTimelineNo(Collection<ActivityInvite> activityInvitesByTimelineNo) {
        this.activityInvitesByTimelineNo = activityInvitesByTimelineNo;
    }

    @OneToMany(mappedBy = "timelineByActivityNo")
    public Collection<ActivityLabel> getActivityLabelsByTimelineNo() {
        return activityLabelsByTimelineNo;
    }

    public void setActivityLabelsByTimelineNo(Collection<ActivityLabel> activityLabelsByTimelineNo) {
        this.activityLabelsByTimelineNo = activityLabelsByTimelineNo;
    }

    @OneToMany(mappedBy = "timelineByActivityNo")
    public Collection<ActivityRemind> getActivityRemindsByTimelineNo() {
        return activityRemindsByTimelineNo;
    }

    public void setActivityRemindsByTimelineNo(Collection<ActivityRemind> activityRemindsByTimelineNo) {
        this.activityRemindsByTimelineNo = activityRemindsByTimelineNo;
    }

}
