package hellojpa;

import jakarta.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) //조인 전략
//@DiscriminatorColumn
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 싱글 테이블 전략
//@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //구현 클래스마다 테이블 전략
@DiscriminatorColumn
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
