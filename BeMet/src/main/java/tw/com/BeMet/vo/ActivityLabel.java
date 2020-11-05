package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity_label", schema = "dbo", catalog = "BeMet")
public class ActivityLabel {
    private Integer activityLabelNo;
    private Integer activityNo;
    private String content;
    private Date createDate;
    private Date modifyDate;
    private Timeline timelineByActivityNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activityLabel_no")
    public Integer getActivityLabelNo() {
        return activityLabelNo;
    }

    public void setActivityLabelNo(Integer activityLabelNo) {
        this.activityLabelNo = activityLabelNo;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        ActivityLabel that = (ActivityLabel) o;

        if (activityLabelNo != null ? !activityLabelNo.equals(that.activityLabelNo) : that.activityLabelNo != null) {
            return false;
        }
        if (activityNo != null ? !activityNo.equals(that.activityNo) : that.activityNo != null) {
            return false;
        }
        if (content != null ? !content.equals(that.content) : that.content != null) {
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
        int result = activityLabelNo != null ? activityLabelNo.hashCode() : 0;
        result = 31 * result + (activityNo != null ? activityNo.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
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
