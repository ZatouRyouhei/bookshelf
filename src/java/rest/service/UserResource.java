package rest.service;

import db.BookDb;
import db.UserDb;
import entity.User;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Inject
    BookDb bookdb;
    
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
    
    @GET
    @Path("/getList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestUser> getList() {
        return userdb.getAll()
                        .stream()
                        .map(user -> new RestUser(user))
                        .sorted(Comparator.comparing(RestUser::getId))
                        .collect(Collectors.toList());
    }
    
    @POST
    @Path("/regist")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registUser(RestUser restUser) {
        User targetUser = userdb.search(restUser.getId());
        if (targetUser == null) {
            // 初期パスワード
            SHA256Encoder encoder = new SHA256Encoder();
            String iniPassword = encoder.encodePassword("111111");
            User registUser = new User();
            registUser.setId(restUser.getId());
            registUser.setPassword(iniPassword);
            registUser.setName(restUser.getName());
            registUser.setRoleName(restUser.getRoleName());
            userdb.add(registUser);
        } else {
            targetUser.setName(restUser.getName());
            targetUser.setRoleName(restUser.getRoleName());
            userdb.update(targetUser);
        }
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Integer deleteUser(@PathParam("id") String id) {
        User targetUser = userdb.search(id);
        Integer result = 0;
        if (targetUser != null) {
            if (bookdb.countBooks(id).equals(0)) {
                // 削除する。
                userdb.delete(targetUser);
                result = 1;
            } else {
                // 本のデータが登録されているので登録不可。
            }
        } else {
            // IDが存在しない。
            throw new NotFoundException();
        }
        return result;
    }
}
