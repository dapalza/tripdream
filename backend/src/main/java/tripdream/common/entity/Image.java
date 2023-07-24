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
public class Image extends CommonEntity{
    @Id
    @GeneratedValue
    @Column(name = "IMAGE_ID")
    private Long id;

    // 이미지 경로
    private String fullPath;

    // 이미지 파일명
    private String name;

}
