package tw.com.BeMet.vo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "dbo", catalog = "BeMet")
public class UserRole {
    private Integer roleNo;
    private String roleName;
    private Collection<UserInformation> userRoleByRoleNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_no")
    public Integer getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(Integer roleNo) {
        this.roleNo = roleNo;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @OneToMany(mappedBy = "userRoleByRoleNo")
    public Collection<UserInformation> getUserRoleByRoleNo() {
        return userRoleByRoleNo;
    }

    public void setUserRoleByRoleNo(Collection<UserInformation> userRoleByRoleNo) {
        this.userRoleByRoleNo = userRoleByRoleNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return Objects.equals(roleNo, userRole.roleNo) &&
                Objects.equals(roleName, userRole.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleNo, roleName);
    }
}
