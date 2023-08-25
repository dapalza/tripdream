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
public class S3File extends CommonEntity{
    @Id
    @GeneratedValue
    @Column(name = "S3FILE_ID")
    private Long id;

    // 이미지 경로(파일명 포함)
    private String fullPath;

    public S3File(String fullPath) {
        this.fullPath = fullPath;
    }
}
