package tripdream.common.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripdream.common.dto.res.ImageResponse;
import tripdream.common.repository.ImageRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageResponse uploadImage() {
        ImageResponse imageResponse = new ImageResponse();

        return imageResponse;
    }
}
