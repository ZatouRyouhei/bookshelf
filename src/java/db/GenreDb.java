package db;

import entity.Genre;
import javax.ejb.Stateless;

/**
 *
 * @author ryouhei
 */
@Stateless
public class GenreDb extends TryCatchDb<Genre> {
    public GenreDb() {
        super(Genre.class);
    }
}
