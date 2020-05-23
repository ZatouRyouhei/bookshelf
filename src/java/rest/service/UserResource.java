package rest.service;

import db.UserDb;
import entity.User;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rest.dto.RestUser;
import utils.SHA256Encoder;

/**
 *
 * @author ryouhei
 */
@RequestScoped
@Path("/user")
public class UserResource {
    @Inject
    UserDb userdb;
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestUser login (RestUser restUser) {
        User user = userdb.search(restUser.getId());
        if (user != null) {
            SHA256Encoder encoder = new SHA256Encoder();
            String inputPassword = encoder.encodePassword(restUser.getPassword());
            if (user.getPassword().equals(inputPassword)) {
                // 認証成功
                RestUser resUser = new RestUser();
                resUser.setId(user.getId());
                resUser.setName(user.getName());
                resUser.setRoleName(user.getRoleName());
                return resUser;
            } else {
                // パスワード誤り
                throw new NotFoundException();
            }
        } else {
            // 入力されたIDが存在しない。
            throw new NotFoundException();
        }
    }
    
    @POST
    @Path("/changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changePassword(RestUser restUser) {
        User targetUser = userdb.search(restUser.getId());
        if (targetUser != null) {
            SHA256Encoder encoder = new SHA256Encoder();
            String inputPassword = encoder.encodePassword(restUser.getPassword());
            targetUser.setPassword(inputPassword);
            userdb.update(targetUser);
        } else {
            // IDが存在しない。
            throw new NotFoundException();
        }
    }
}
