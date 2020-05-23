package rest.service;

import db.GenreDb;
import entity.Genre;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rest.dto.RestGenre;

/**
 *
 * @author ryouhei
 */
@RequestScoped
@Path("/genre")
public class GenreResource {
    @Inject
    GenreDb genreDb;
    
    @GET
    @Path("/getList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestGenre> getList() {
        List<Genre> genreList = genreDb.getAll();
        List<RestGenre> restGenreList = genreList
                                            .stream()
                                            .map(genre -> new RestGenre(genre))
                                            .sorted(Comparator.comparing(RestGenre::getId))
                                            .collect(Collectors.toList());
        return restGenreList;
    }
}
