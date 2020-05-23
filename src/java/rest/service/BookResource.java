package rest.service;

import db.BookDb;
import db.GenreDb;
import entity.Book;
import entity.BookKey;
import entity.Genre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rest.dto.RestBook;
import rest.dto.RestSearchCondition;

/**
 *
 * @author ryouhei
 */
@RequestScoped
@Path("/book")
public class BookResource {
    @Inject
    BookDb bookDb;
    
    @Inject
    GenreDb genreDb;
    
    @POST
    @Path("/regist")
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer registBook(RestBook restBook) {
        try {
            Book newBook = new Book();
            
            newBook.setTitle(restBook.getTitle());
            newBook.setAuthor(restBook.getAuthor());
            newBook.setPrice(restBook.getPrice());
            newBook.setPublisher(restBook.getPublisher());
            newBook.setPublished(new SimpleDateFormat("yyyy-MM-dd").parse(restBook.getPublished()));
            newBook.setCompleteDate(new SimpleDateFormat("yyyy-MM-dd").parse(restBook.getCompleteDate()));
            Genre restGenre = genreDb.search(restBook.getGenre().getId());
            newBook.setGenre(restGenre);
            newBook.setMemo(restBook.getMemo());
            newBook.setRate(restBook.getRate());
            newBook.setImgUrl(restBook.getImgUrl());
            newBook.setInfoUrl(restBook.getInfoUrl());
            
            // IDが入っていないときは、IDを求める。
            if (restBook.getKey().getSeqNo() == null) {
                // 新規登録
                Integer nextSeq = bookDb.getNextSeqNo(restBook.getKey().getUserId());
                newBook.setKey(new BookKey(restBook.getKey().getUserId(), nextSeq));
                bookDb.add(newBook);
            } else {
                // 更新
                newBook.setKey(new BookKey(restBook.getKey().getUserId(), restBook.getKey().getSeqNo()));
                bookDb.update(newBook);
            }
            
            return newBook.getKey().getSeqNo();
        } catch (ParseException ex) {
            throw new InternalServerErrorException();
        }
    }
    
    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestBook> searchBook(RestSearchCondition cond) {
        try {
            List<Book> bookList = bookDb.searchBook(cond);
            List<RestBook> restBookList = bookList
                                            .stream()
                                            .map(book -> new RestBook(book))
                                            .collect(Collectors.toList());
            return restBookList;
        } catch (ParseException ex) {
            throw new InternalServerErrorException();
        }
    }
    
    @DELETE
    @Path("/delete/{userId}/{seqNo}")
    public void deleteBook(@PathParam("userId") String userId, @PathParam("seqNo") Integer seqNo) {
        Book targetBook = bookDb.search(new BookKey(userId, seqNo));
        if (targetBook != null) {
            bookDb.delete(targetBook);
        }
    }
    
}
