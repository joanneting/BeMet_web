package tw.com.business_meet.vo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "friend_label", schema = "dbo", catalog = "BeMet")
public class FriendLabel {
    private Integer friendLabelNo;
    private String content;
    private Integer friendCustomizationNo;
    private Date createDate;
    private Date modifyDate;
    private FriendCustomization friendCustomizationByFriendCustomizationNo;
    private Collection<FriendRemark> friendRemarksByFriendLabelNo;

    @Id
    @Column(name = "friend_label_no")
    public Integer getFriendLabelNo() {
        return friendLabelNo;
    }

    public void setFriendLabelNo(Integer friendLabelNo) {
        this.friendLabelNo = friendLabelNo;
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
    @Column(name = "friend_customization_no")
    public Integer getFriendCustomizationNo() {
        return friendCustomizationNo;
    }

    public void setFriendCustomizationNo(Integer friendCustomizationNo) {
        this.friendCustomizationNo = friendCustomizationNo;
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

        FriendLabel that = (FriendLabel) o;

        if (friendLabelNo != null ? !friendLabelNo.equals(that.friendLabelNo) : that.friendLabelNo != null) {
            return false;
        }
        if (content != null ? !content.equals(that.content) : that.content != null) {
            return false;
        }
        if (friendCustomizationNo != null ? !friendCustomizationNo.equals(that.friendCustomizationNo) : that.friendCustomizationNo != null) {
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
        int result = friendLabelNo != null ? friendLabelNo.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (friendCustomizationNo != null ? friendCustomizationNo.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "friend_customization_no", referencedColumnName = "friend_customization_no", nullable = false, insertable = false, updatable = false)
    public FriendCustomization getFriendCustomizationByFriendCustomizationNo() {
        return friendCustomizationByFriendCustomizationNo;
    }

    public void setFriendCustomizationByFriendCustomizationNo(FriendCustomization friendCustomizationByFriendCustomizationNo) {
        this.friendCustomizationByFriendCustomizationNo = friendCustomizationByFriendCustomizationNo;
    }

    @OneToMany(mappedBy = "friendLabelByFriendLabelNo")
    public Collection<FriendRemark> getFriendRemarksByFriendLabelNo() {
        return friendRemarksByFriendLabelNo;
    }

    public void setFriendRemarksByFriendLabelNo(Collection<FriendRemark> friendRemarksByFriendLabelNo) {
        this.friendRemarksByFriendLabelNo = friendRemarksByFriendLabelNo;
    }
}
