package rest.dto;

import entity.User;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ryouhei
 */
@XmlRootElement
public class RestUser {
    private String id;
    private String password;
    private String name;
    private User.UserRoleName roleName;

    public RestUser() {
    }

    public RestUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.roleName = user.getRoleName();
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

    public User.UserRoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(User.UserRoleName roleName) {
        this.roleName = roleName;
    }
}
