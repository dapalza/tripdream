package tripdream.common.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.dto.res.ImageResponse;
import tripdream.common.entity.S3File;
import tripdream.common.exception.FileNotFoundException;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadImage(@RequestPart MultipartFile file) throws IOException {

        S3File s3File = fileService.uploadImage(file);

        ImageResponse imageResponse = new ImageResponse(s3File.getFullPath());

        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

}
