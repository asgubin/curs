package ru.asgubin.test.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity                     //Указывает, что данный бин (класс) является сущностью
@Table(name = "articles")   //Имя сущности
public class Articles implements Serializable {

    @Id                     //ID
    //указывает, что данное свойство будет создаваться согласно указанной стратегии
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    //Имя атрибута
    private Long id;

    @Column(name = "name", length = 50, nullable = false)  //Имя атрибута
    private String name;

    protected Articles() {}

    public Articles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return String.format("Articles[id=%d, name=%s]", id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
