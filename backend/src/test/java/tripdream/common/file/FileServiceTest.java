package tripdream.common.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.entity.S3File;
import tripdream.common.exception.FileNotFoundException;
import tripdream.common.repository.ImageRepository;
import tripdream.common.repository.MemberRepository;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    private String bucket;

    private String path;

    private AmazonS3Client amazonS3Client;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    public FileServiceTest(@Value("${cloud.aws.s3.bucket}") String bucket, @Value("${custom.path.image}") String path,
                       AmazonS3Client amazonS3Client) {
        this.bucket = bucket;
        this.path = path;
        this.amazonS3Client = amazonS3Client;
    }

//    @Test
    @DisplayName("upload image test")
    void uploadImage() throws IOException {

        MultipartFile file = new MockMultipartFile(
                "filename",
                "filename.png",
                MediaType.IMAGE_PNG_VALUE,
                "filename".getBytes()
        );

        S3File s3File;
        if (!file.isEmpty()) {
            String fileUrl = path + file.getOriginalFilename();

            s3File = new S3File(fileUrl);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, fileUrl, file.getInputStream(), metadata);

        } else {
            throw new FileNotFoundException();
        }

        S3Object s3Object = amazonS3Client.getObject(bucket, file.getOriginalFilename());
        S3ObjectInputStream objectContent = s3Object.getObjectContent();

//        Assertions.assertThat(objectContent).isEqualTo(file.getInputStream());
    }

    @Test
    void deleteImage() {
    }
}