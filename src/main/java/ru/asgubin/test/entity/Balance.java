package ru.asgubin.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity                     //Указывает, что данный бин (класс) является сущностью
@Table(name = "balance")    //Имя сущности
public class Balance implements Serializable {

    @Id                     //ID
    //указывает, что данное свойство будет создаваться согласно указанной стратегии
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    //Имя атрибута
    private Long id;

    @Temporal(TemporalType.DATE)    //Использовать дату
    @Column(name = "create_date", nullable = false)   //Имя атрибута
    private Date create_date;

    @Column(name = "debit", nullable = false)         //Имя атрибута
    private Double debit;

    @Column(name = "credit", nullable = false)         //Имя атрибута
    private Double credit;

    @Column(name = "amount", nullable = false)         //Имя атрибута
    private Double amount;

    protected Balance() {}

    public Balance(Date create_date, double debit, double credit,
                   double amount) {
        this.create_date = create_date;
        this.debit = debit;
        this.credit = credit;
        this.amount = amount;
    }

    @Override
    public String toString() {

        return String.format("Balance[id=%d, create_date='%s', debit=%.2f, credit=%.2f, amount=%.2f]",
                id, create_date, debit, credit, amount);
    }

    public Long getId() {
        return id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public Double getCredit() {
        return credit;
    }

    public Double getDebit() {
        return debit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
