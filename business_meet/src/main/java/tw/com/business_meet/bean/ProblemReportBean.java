package tw.com.business_meet.bean;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ProblemReportBean {
    private Integer problemReportNo;
    private String content;
    private String userId;
    private String userName;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Integer getProblemReportNo() {
        return problemReportNo;
    }

    public void setProblemReportNo(Integer problemReportNo) {
        this.problemReportNo = problemReportNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
