package tw.com.BeMet.vo;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "problem_report", schema = "dbo", catalog = "BeMet")
public class ProblemReport {
	private Integer problemReportNo;
	private String content;
	private String userId;
	private Integer status;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private UserInformation userInformationByUserId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "problem_report_no", nullable = false)
	public Integer getProblemReportNo() {
		return problemReportNo;
	}

	public void setProblemReportNo(Integer problemReportNo) {
		this.problemReportNo = problemReportNo;
	}

	@Column(name = "[content]", length = 3000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "user_id", length = 100)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "start_date")
	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date")
	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@Column(name = "create_date")
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	@Column(name = "modify_date")
	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, createDate, modifyDate, problemReportNo, userId, startDate, endDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemReport other = (ProblemReport) obj;
		return Objects.equals(content, other.content) && Objects.equals(createDate, other.createDate)&& Objects.equals(status, other.status)
				&& Objects.equals(modifyDate, other.modifyDate)&& Objects.equals(startDate, other.startDate)&& Objects.equals(endDate, other.endDate)
				&& Objects.equals(problemReportNo, other.problemReportNo) && Objects.equals(userId, other.userId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	public UserInformation getUserInformationByUserId() {
		return userInformationByUserId;
	}

	public void setUserInformationByUserId(UserInformation userInformationByUserId) {
		this.userInformationByUserId = userInformationByUserId;
	}
}
