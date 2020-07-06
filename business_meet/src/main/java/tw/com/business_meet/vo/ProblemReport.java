package tw.com.business_meet.vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "problem_report", schema = "dbo", catalog = "BeMet")
public class ProblemReport {
    private Integer problemReportNo;
    private String content;
    private Integer userNo;
    private Date createDate;
    private Date modifyDate;
    private UserInformation userInformationByUserNo;
    private String userId;
    private UserInformation userInformationByUserId;

    @Id
    @Column(name = "problem_report_no")
    public Integer getProblemReportNo() {
        return problemReportNo;
    }

    public void setProblemReportNo(Integer problemReportNo) {
        this.problemReportNo = problemReportNo;
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

        ProblemReport that = (ProblemReport) o;

        if (problemReportNo != null ? !problemReportNo.equals(that.problemReportNo) : that.problemReportNo != null) {
            return false;
        }
        if (content != null ? !content.equals(that.content) : that.content != null) {
            return false;
        }
        if (userNo != null ? !userNo.equals(that.userNo) : that.userNo != null) {
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
        int result = problemReportNo != null ? problemReportNo.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
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
