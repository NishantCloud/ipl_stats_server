package org.monexa.ipl_stats.ImageComposite;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ImageUploadController {

    private final PersonImageRepository personImageRepository;
    private final JerseyRepository jerseyRepository;
    private final FileStorageService fileStorageService;

    @PostMapping(value = "/people-images/{personId}/full", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> uploadPlayerImageFull(
            @PathVariable Long personId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("anchorX") BigDecimal anchorX,
            @RequestParam("anchorY") BigDecimal anchorY,
            @RequestParam("widthFraction") BigDecimal widthFraction) throws Exception {

        BufferedImage img = ImageIO.read(file.getInputStream());
        String relativePath = "players/" + personId + ".png";
        String url = fileStorageService.upload(relativePath, file.getBytes());

        PersonImage person = personImageRepository.findById(personId).orElseGet(PersonImage::new);
        person.setPersonId(personId);
        person.setImageUrl(url);
        person.setImageWidth(img.getWidth());
        person.setImageHeight(img.getHeight());
        person.setNeckAnchorX(anchorX);
        person.setNeckAnchorY(anchorY);
        person.setNeckWidthFraction(widthFraction);
        person.setImageVersion(person.getImageVersion() == null ? 1 : person.getImageVersion() + 1);
        personImageRepository.save(person);

        return ResponseEntity.ok(Map.of("personId", personId, "imageUrl", url));
    }

    @PostMapping(value = "/jerseys/{teamId}/full", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> uploadJerseyImageFull(
            @PathVariable Long teamId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("anchorX") BigDecimal anchorX,
            @RequestParam("anchorY") BigDecimal anchorY,
            @RequestParam("widthFraction") BigDecimal widthFraction) throws Exception {

        BufferedImage img = ImageIO.read(file.getInputStream());
        String relativePath = "jerseys/" + teamId + ".png";
        String url = fileStorageService.upload(relativePath, file.getBytes());

        Jersey jersey = jerseyRepository.findById(teamId).orElseGet(Jersey::new);
        jersey.setTeamId(teamId);
        jersey.setImageUrl(url);
        jersey.setImageWidth(img.getWidth());
        jersey.setImageHeight(img.getHeight());
        jersey.setCollarAnchorX(anchorX);
        jersey.setCollarAnchorY(anchorY);
        jersey.setCollarWidthFraction(widthFraction);
        jersey.setImageVersion(jersey.getImageVersion() == null ? 1 : jersey.getImageVersion() + 1);
        jerseyRepository.save(jersey);

        return ResponseEntity.ok(Map.of("teamId", teamId, "imageUrl", url));
    }
}