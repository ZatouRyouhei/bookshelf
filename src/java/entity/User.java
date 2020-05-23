package entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ユーザ
 * @author ryouhei
 */
@Entity
@Table(name="t_user")
@Cacheable(false)
public class User implements Serializable {
    public static final int SIZE_ID = 20;
    public static final int SIZE_PASSWORD = 200;
    public static final int SIZE_NAME = 20;
    public static final int SIZE_ROLENAME = 6;
    
    @Id
    @Column(length=SIZE_ID)
    private String id;
    @Column(length=SIZE_PASSWORD)
    private String password;
    @Column(length=SIZE_NAME)
    private String name;
    @Column(length=SIZE_ROLENAME)
    @Enumerated(EnumType.STRING)
    private UserRoleName roleName;

    public User() {
    }

    public enum UserRoleName {
        USER("ユーザ"),  //職員
        ADMIN("管理者")  //業者
        ;
        private final String roleName;
        
        private UserRoleName(String roleName) {
            this.roleName = roleName;
        }
        
        public String getRoleName() {
            return this.roleName;
        }
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRoleName roleName) {
        this.roleName = roleName;
    }
}
