package org.monexa.ipl_stats.ImageComposite;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * GET /api/players/{personId}/{teamId}/image
 *
 * Returns the merged image directly as image/png bytes.
 * In Android: Glide.with(context).load("http://yourserver/api/players/1/5/image").into(imageView);
 * No JSON, no extra step — the URL itself IS the image.
 */
@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerJerseyImageController {

    private final CompositeImageService compositeImageService;

    @GetMapping(value = "/{personId}/{teamId}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getCompositeImage(
            @PathVariable Long personId,
            @PathVariable Long teamId) throws IOException {

        byte[] imageBytes = compositeImageService.getCompositeImage(personId, teamId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=" + TimeUnit.DAYS.toSeconds(365))
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }
}