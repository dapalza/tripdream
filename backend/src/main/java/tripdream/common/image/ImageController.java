package tripdream.common.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.dto.res.ImageResponse;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.FileNotFoundException;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadImage(MultipartFile file) throws IOException {

        if(!file.isEmpty()) {
            file.transferTo(new File(file.getOriginalFilename()));
        } else {
            throw new FileNotFoundException(ErrorCode.FILE_NOT_FOUND);
        }

        ImageResponse imageResponse = imageService.uploadImage(file);

        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

}
