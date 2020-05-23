package rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ryouhei
 */
@XmlRootElement
public class RestSearchCondition {
    private String userId;
    private String title;
    private String author;
    private String completeDateFrom;
    private String completeDateTo;
    private Integer genre;
    private Integer rate;

    public RestSearchCondition() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompleteDateFrom() {
        return completeDateFrom;
    }

    public void setCompleteDateFrom(String completeDateFrom) {
        this.completeDateFrom = completeDateFrom;
    }

    public String getCompleteDateTo() {
        return completeDateTo;
    }

    public void setCompleteDateTo(String completeDateTo) {
        this.completeDateTo = completeDateTo;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    
}
