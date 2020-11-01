package tw.com.BeMet.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_information", schema = "dbo", catalog = "BeMet")
public class UserInformation implements UserDetails {
    @ElementCollection(targetClass = GrantedAuthority.class)
    private Collection<GrantedAuthority> authorities;
    private String userId;
    private String password;
    private String name;
    private String gender;
    private String mail;
    private String profession;
    private String identifier;
    private String avatar;
    private String tel;
    private Integer roleNo;
    private String firebaseToken;
    private Date createDate;
    private Date modifyDate;
    private UserRole userRoleByRoleNo;
    private Collection<ActivityInvite> activityInvitesByUserId;
    private Collection<Friend> friendsByUserId;
    private Collection<Groups> groupsByUserId;
    private Collection<ProblemReport> problemReportsByUserId;
    private Collection<UserCustomization> userCustomizationsByUserId;
    private Collection<Friend> friendsByUserId_0;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
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

    @Column(name = "role_no")
    public Integer getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(Integer roleNo) {
        this.roleNo = roleNo;
    }

    @Column(name = "firebase_token")
    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
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
    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    @ManyToOne
    @JoinColumn(name = "role_no", nullable = false, insertable = false, updatable = false)
    public UserRole getUserRoleByRoleNo() {
        return userRoleByRoleNo;
    }

    public void setUserRoleByRoleNo(UserRole userRoleByRoleNo) {
        this.userRoleByRoleNo = userRoleByRoleNo;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserInformation that = (UserInformation) o;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
            return false;
        }
        if (password != null ? !password.equals(that.password) : that.password != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) {
            return false;
        }
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) {
            return false;
        }
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) {
            return false;
        }
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) {
            return false;
        }
        if (profession != null ? !profession.equals(that.profession) : that.profession != null) {
            return false;
        }
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
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
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userInformationByFriendId")
    public Collection<Friend> getFriendsByUserId_0() {
        return friendsByUserId_0;
    }

    public void setFriendsByUserId_0(Collection<Friend> friendsByUserId_0) {
        this.friendsByUserId_0 = friendsByUserId_0;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRoleByRoleNo.getRoleName()));
        this.authorities = authorities;
    }

    @Transient
    @Override
    public String getUsername() {
        return userId;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }
}
