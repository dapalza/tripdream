package tripdream.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class CommonEntity extends CommonTimeEntity{

    @CreatedBy
    @Column(updatable = false)
    private String createdByIp;

    @LastModifiedBy
    private String lastModifiedByIp;

}
