package org.monexa.ipl_stats.ImageComposite;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CompositeImageService {

    private final PersonImageRepository personImageRepository;
    private final JerseyRepository jerseyRepository;
    private final ImageCompositionService imageCompositionService;
    private final FileStorageService fileStorageService;

    /** Returns the final composited PNG bytes, generating + caching to disk only if needed. */
    public byte[] getCompositeImage(Long personId, Long teamId) throws IOException {

        PersonImage person = personImageRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found: " + personId));

        Jersey jersey = jerseyRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Jersey not found for team: " + teamId));

        // filename encodes both versions -> acts as the cache key, no DB table needed
        String relativePath = "composites/%d_%d_pv%d_jv%d.png".formatted(
                personId, teamId, person.getImageVersion(), jersey.getImageVersion());

        if (fileStorageService.exists(relativePath)) {
            return fileStorageService.read(relativePath);
        }

        byte[] pngBytes = imageCompositionService.composite(person, jersey);
        fileStorageService.upload(relativePath, pngBytes);
        return pngBytes;
    }
}
