package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class Member extends  BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //period 기간
    @Embedded //값타입 사용하는곳에 표시
    private Period workPeriod;

    //주소
    @Embedded //값타입 사용하는곳에 표시
    private Address homeAddress;

    //주소
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",
                    column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "strret",
                    column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode",
                    column = @Column(name = "WORK_ZIPCODE"))
    })

    private Address workAddress;

//    @ManyToOne (fetch = FetchType.LAZY) //fetchtype.lazy 시 프록시 상태로 조회홤
    @ManyToOne (fetch = FetchType.EAGER)  // 즉시로딩 >> 실무에선 절대 쓰지 말것
    @JoinColumn(name = "TEAM_ID") //외래키가 있는곳을 주인으로 정해라
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    // 다대다
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }
}
