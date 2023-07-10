package tripdream.common.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.dto.req.ImageRequest;
import tripdream.common.dto.res.ImageResponse;
import tripdream.common.exception.FileNotFoundException;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadImage(@RequestPart MultipartFile file, @Valid @RequestPart(required = false) ImageRequest imageRequest) throws IOException {

        if(!file.isEmpty()) {
            file.transferTo(new File(file.getOriginalFilename()));
        } else {
            throw new FileNotFoundException();
        }

        log.info("refresh token = {}", imageRequest.getRefreshToken());

        ImageResponse imageResponse = imageService.uploadImage(file);

        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

}
