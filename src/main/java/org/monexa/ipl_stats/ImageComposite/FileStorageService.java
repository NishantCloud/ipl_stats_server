package org.monexa.ipl_stats.ImageComposite;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Saves files to src/main/resources/static/player-images (real folder on disk)
 * and returns a full HTTP URL. Every other class only ever deals with URLs —
 * so when you move to S3/Cloudinary/etc later, just rewrite the upload()
 * method to push there instead and return the cloud URL. Nothing else changes.
 */
@Service
public class FileStorageService {

    private final Path baseDir;
    private final String baseUrl;

    public FileStorageService(
            @Value("${app.storage.local-path:src/main/resources/static/player-images}") String localPath,
            @Value("${app.base-url:http://localhost:8080}") String baseUrl) {
        this.baseDir = Paths.get(localPath).toAbsolutePath().normalize();
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        try {
            Files.createDirectories(baseDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage dir: " + baseDir, e);
        }
    }

    /** Saves bytes under a subpath (e.g. "raw/players/xyz.png") and returns the public URL. */
    public String upload(String relativePath, byte[] bytes) {
        try {
            Path target = baseDir.resolve(relativePath).normalize();
            Files.createDirectories(target.getParent());
            Files.write(target, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return baseUrl + "/player-images/" + relativePath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + relativePath, e);
        }
    }

    /** Checks if a composite already exists on disk (used as the "cache") without a DB table. */
    public boolean exists(String relativePath) {
        return Files.exists(baseDir.resolve(relativePath));
    }

    public byte[] read(String relativePath) throws IOException {
        return Files.readAllBytes(baseDir.resolve(relativePath));
    }
}
