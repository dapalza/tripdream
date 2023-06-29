package tripdream.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();
}
