package tw.com.business_meet.vo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user_information", schema = "dbo", catalog = "BeMet")
public class UserInformation {
    private String userId;
    private String password;
    private String name;
    private String gender;
    private String mail;
    private String avatar;
    private String tel;
    private String profession;
    private String bluetooth;
    private Date createDate;
    private Date modifyDate;
    private Collection<ActivityInvite> activityInvitesByUserId;
    private Collection<Friend> friendsByUserId;
    private Collection<FriendCustomization> friendCustomizationsByUserId;
    private Collection<Groups> groupsByUserId;
    private Collection<ProblemReport> problemReportsByUserId;
    private Collection<UserCustomization> userCustomizationsByUserId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Basic
    @Column(name = "bluetooth")
    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
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


    @OneToMany(mappedBy = "userInformationByUserId")
    public Collection<ActivityInvite> getActivityInvitesByUserId() {
        return activityInvitesByUserId;
    }

    public void setActivityInvitesByUserId(Collection<ActivityInvite> activityInvitesByUserId) {
        this.activityInvitesByUserId = activityInvitesByUserId;
    }

    @OneToMany(mappedBy = "userInformationByMatchmakerId")
    public Collection<Friend> getFriendsByUserId() {
        return friendsByUserId;
    }

    public void setFriendsByUserId(Collection<Friend> friendsByUserId) {
        this.friendsByUserId = friendsByUserId;
    }

    @OneToMany(mappedBy = "userInformationByUserId")
    public Collection<FriendCustomization> getFriendCustomizationsByUserId() {
        return friendCustomizationsByUserId;
    }

    public void setFriendCustomizationsByUserId(Collection<FriendCustomization> friendCustomizationsByUserId) {
        this.friendCustomizationsByUserId = friendCustomizationsByUserId;
    }

    @OneToMany(mappedBy = "userInformationByUserId")
    public Collection<Groups> getGroupsByUserId() {
        return groupsByUserId;
    }

    public void setGroupsByUserId(Collection<Groups> groupsByUserId) {
        this.groupsByUserId = groupsByUserId;
    }

    @OneToMany(mappedBy = "userInformationByUserId")
    public Collection<ProblemReport> getProblemReportsByUserId() {
        return problemReportsByUserId;
    }

    public void setProblemReportsByUserId(Collection<ProblemReport> problemReportsByUserId) {
        this.problemReportsByUserId = problemReportsByUserId;
    }

    @OneToMany(mappedBy = "userInformationByUserId")
    public Collection<UserCustomization> getUserCustomizationsByUserId() {
        return userCustomizationsByUserId;
    }

    public void setUserCustomizationsByUserId(Collection<UserCustomization> userCustomizationsByUserId) {
        this.userCustomizationsByUserId = userCustomizationsByUserId;
    }
}
