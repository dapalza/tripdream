package tripdream.common.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.dto.res.ImageResponse;
import tripdream.common.entity.S3File;
import tripdream.common.exception.FileNotFoundException;
import tripdream.common.repository.ImageRepository;

import java.io.File;
import java.io.IOException;

@Service
@Transactional
@Slf4j
public class FileService {

    private String bucket;

    private String path;

    private AmazonS3Client amazonS3Client;

    @Autowired
    public FileService(@Value("${cloud.aws.s3.bucket}") String bucket, @Value("${custom.path.image}") String path,
                       ImageRepository imageRepository, AmazonS3Client amazonS3Client) {
        this.bucket = bucket;
        this.path = path;
        this.amazonS3Client = amazonS3Client;
    }

    public S3File uploadImage(MultipartFile file) throws IOException {
        log.info("upload image = {}", file.toString());

        if(!file.isEmpty()) {
            String fileUrl = path + file.getOriginalFilename();

            S3File s3File = new S3File(fileUrl);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, fileUrl, file.getInputStream(), metadata);

            return s3File;
        } else {
            throw new FileNotFoundException();
        }

    }

    public ImageResponse deleteImage(String fileUrl) {
        amazonS3Client.deleteObject(bucket, fileUrl);
        return null;
    }
}
