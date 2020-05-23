package rest.dto;

import entity.Genre;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ryouhei
 */
@XmlRootElement
public class RestGenre {
    private Integer id;
    private String name;

    public RestGenre() {
    }
    public RestGenre(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
