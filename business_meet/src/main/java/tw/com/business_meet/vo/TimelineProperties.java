package tw.com.business_meet.vo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "timeline_properties", schema = "dbo", catalog = "BeMet")
public class TimelineProperties {
    private Integer timelinePropertiesNo;
    private String name;
    private Date createDate;
    private Date modifyDate;
    private Collection<Timeline> timelinesByTimelinePropertiesNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeline_properties_no")
    public Integer getTimelinePropertiesNo() {
        return timelinePropertiesNo;
    }

    public void setTimelinePropertiesNo(Integer timelinePropertiesNo) {
        this.timelinePropertiesNo = timelinePropertiesNo;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Temporal(TemporalType.DATE)
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

        TimelineProperties that = (TimelineProperties) o;

        if (timelinePropertiesNo != null ? !timelinePropertiesNo.equals(that.timelinePropertiesNo) : that.timelinePropertiesNo != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
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
        int result = timelinePropertiesNo != null ? timelinePropertiesNo.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "timelinePropertiesByTimelinePropertiesNo")
    public Collection<Timeline> getTimelinesByTimelinePropertiesNo() {
        return timelinesByTimelinePropertiesNo;
    }

    public void setTimelinesByTimelinePropertiesNo(Collection<Timeline> timelinesByTimelinePropertiesNo) {
        this.timelinesByTimelinePropertiesNo = timelinesByTimelinePropertiesNo;
    }
}
