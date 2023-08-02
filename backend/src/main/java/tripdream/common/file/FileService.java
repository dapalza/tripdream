package tripdream.common.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.dto.res.ImageResponse;
import tripdream.common.exception.FileNotFoundException;
import tripdream.common.repository.ImageRepository;

import java.io.File;
import java.io.IOException;

@Service
@Transactional
public class FileService {

    private final ImageRepository imageRepository;

    private String bucket;

    private String path;

    private AmazonS3Client amazonS3Client;

    @Autowired
    public FileService(@Value("${cloud.aws.s3.bucket}") String bucket, @Value("${custom.path.image}") String path,
                       ImageRepository imageRepository, AmazonS3Client amazonS3Client) {
        this.bucket = bucket;
        this.path = path;
        this.imageRepository = imageRepository;
        this.amazonS3Client = amazonS3Client;
    }

    public ImageResponse uploadImage(MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            ImageResponse imageResponse = new ImageResponse(file.getOriginalFilename());
            String fileUrl = "https://" + bucket + path + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, file.getOriginalFilename(), file.getInputStream(), metadata);

            return imageResponse;
        } else {
            throw new FileNotFoundException();
        }

    }

    public ImageResponse deleteImage(String path, String fileName) {
        return null;
    }
}
