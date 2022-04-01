package ru.asgubin.test.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity                         //Указывает, что данный бин (класс) является сущностью
@Table(name = "operations")     //Имя сущности
public class Operations implements Serializable {

    @Id     //ID
    //указывает, что данное свойство будет создаваться согласно указанной стратегии
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    //Имя атрибута
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   //указывает на связь между таблицами «один к одному»
    @JoinColumn(name = "article_id", nullable = false)   //применяется когда внешний ключ находится в одной из сущностей
    private Articles article_id;

    @Column(name = "debit", nullable = false)         //Имя атрибута
    @NotNull(message = "Debit may not be null")
    private Double debit;

    @Column(name = "credit", nullable = false)        //Имя атрибута
    private Double credit;

    @Temporal(TemporalType.DATE)    //Использовать дату
    @Column(name = "create_date", nullable = false)   //Имя атрибута
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   //указывает на связь между таблицами «один к одному»
    @JoinColumn(name = "balance_id", nullable = true)   //применяется когда внешний ключ находится в одной из сущностей
    private Balance balanceId;

    protected Operations() {}

    public Operations(Articles article_id, double debit, double credit,
                      Date createDate) {
        this.article_id = article_id;
        this.debit = debit;
        this.credit = credit;
        this.createDate = createDate;
      }

    public Operations(Articles article_id, double debit, double credit,
                      Date createDate, Balance balanceId) {
        this(article_id, debit, credit, createDate);
        this.balanceId = balanceId;
    }

    @Override
    public String toString() {
            return "Operations{" +
                    "id=" + id +
                    ", article_id=" + article_id.getId() +
                    ", debit=" + debit +
                    ", credit=" + credit +
                    ", create_date=" + createDate +
                    ", balance_id=" + ((balanceId == null) ? "null" : balanceId.getId()) +
                    '}';
    }

    public Long getId() {
        return id;
    }

    public Articles getArticle_id() {
        return article_id;
    }

    public Double getDebit() {
        return debit;
    }

    public Double getCredit() {
        return credit;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Balance getBalanceId() {
        return balanceId;
    }

    public void setArticle_id(Articles article_id) {
        this.article_id = article_id;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setBalanceId(Balance balanceId) {
        this.balanceId = balanceId;
    }
}
