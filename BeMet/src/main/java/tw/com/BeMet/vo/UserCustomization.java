package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_customization", schema = "dbo", catalog = "BeMet")
public class UserCustomization {
    private Integer userCustomizationNo;
    private String userId;
    private String columnName;
    private String content;
    private Date createDate;
    private Date modifyDate;
    private UserInformation userInformationByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_customization_no")
    public Integer getUserCustomizationNo() {
        return userCustomizationNo;
    }

    public void setUserCustomizationNo(Integer userCustomizationNo) {
        this.userCustomizationNo = userCustomizationNo;
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
    @Column(name = "column_name")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
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

        UserCustomization that = (UserCustomization) o;

        if (userCustomizationNo != null ? !userCustomizationNo.equals(that.userCustomizationNo) : that.userCustomizationNo != null) {
            return false;
        }
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
            return false;
        }
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) {
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
        int result = userCustomizationNo != null ? userCustomizationNo.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
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
}
