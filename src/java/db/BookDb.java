package db;

import entity.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import rest.dto.RestSearchCondition;

/**
 *
 * @author ryouhei
 */
@Stateless
public class BookDb extends TryCatchDb<Book> {
    public BookDb() {
        super(Book.class);
    }
    
    /**
     * 次の連番を求める
     * @param userId
     * @return 
     */
    public Integer getNextSeqNo(String userId) {
        TypedQuery<Integer> q = em.createNamedQuery(Book.BOOK_GETMAXSEQ, Integer.class);
        q.setParameter("userId", userId);
        Integer maxSeq = q.getSingleResult();
        Integer nextSeq = 1;
        if (maxSeq != null) {
            nextSeq = maxSeq + 1;
        }
        return nextSeq;
    }
    
    public List<Book> searchBook (RestSearchCondition cond) throws ParseException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT b");
        query.append("  FROM Book b");
        query.append(" WHERE b.key.userId = :userid");
        if (StringUtils.isNotEmpty(cond.getTitle())) {
            query.append("   AND b.title LIKE :title");
        }
        if (StringUtils.isNotEmpty(cond.getAuthor())) {
            query.append("   AND b.author LIKE :author");
        }
        if (StringUtils.isNotEmpty(cond.getCompleteDateFrom())) {
            query.append("   AND b.completeDate >= :completeDateFrom");
        }
        if (StringUtils.isNotEmpty(cond.getCompleteDateTo())) {
            query.append("   AND b.completeDate <= :completeDateTo");
        }
        if (cond.getGenre() != null && cond.getGenre() != 0) {
            query.append("   AND b.genre.id = :genre");
        }
        if (cond.getRate() != null && cond.getRate() != 0) {
            query.append("   AND b.rate = :rate");
        }
        query.append(" ORDER BY b.completeDate DESC");
                
        TypedQuery<Book> q = em.createQuery(query.toString(), Book.class);
        q.setParameter("userid", cond.getUserId());
        if (StringUtils.isNotEmpty(cond.getTitle())) {
            q.setParameter("title", "%" + cond.getTitle() + "%");
        }
        if (StringUtils.isNotEmpty(cond.getAuthor())) {
            q.setParameter("author", "%" + cond.getAuthor() + "%");
        }
        if (StringUtils.isNotEmpty(cond.getCompleteDateFrom())) {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date completeDateFrom = sdFormat.parse(cond.getCompleteDateFrom() + " 00:00");
            q.setParameter("completeDateFrom", completeDateFrom);
        }
        if (StringUtils.isNotEmpty(cond.getCompleteDateTo())) {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date completeDateTo = sdFormat.parse(cond.getCompleteDateTo() + " 00:00");
            q.setParameter("completeDateTo", completeDateTo);
        }
        if (cond.getGenre() != null && cond.getGenre() != 0) {
            q.setParameter("genre", cond.getGenre());
        }
        if (cond.getRate() != null && cond.getRate() != 0) {
            q.setParameter("rate", cond.getRate());
        }
        
        List<Book> bookList = q.getResultList();
        return bookList;
    }
}
