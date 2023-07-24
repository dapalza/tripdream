package tripdream.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Budget extends CommonEntity{
    @Id
    @GeneratedValue
    @Column(name = "BUDGET_ID")
    private Long id;

    private String category;

    private long cost;
}
