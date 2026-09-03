package org.monexa.ipl_stats.ImageComposite;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class ImageCompositionService {

    public byte[] composite(PersonImage person, Jersey jersey) throws IOException {
        BufferedImage playerImg = loadImage(person.getImageUrl());
        BufferedImage jerseyImg = loadImage(jersey.getImageUrl());

        double playerNeckWidthPx = person.getNeckWidthFraction().doubleValue() * playerImg.getWidth();
        double jerseyCollarWidthPx = jersey.getCollarWidthFraction().doubleValue() * jerseyImg.getWidth();
        double scale = jerseyCollarWidthPx / playerNeckWidthPx;

        int scaledWidth = (int) Math.round(playerImg.getWidth() * scale);
        int scaledHeight = (int) Math.round(playerImg.getHeight() * scale);
        BufferedImage scaledPlayer = scaleImage(playerImg, scaledWidth, scaledHeight);

        double jerseyAnchorPxX = jersey.getCollarAnchorX().doubleValue() * jerseyImg.getWidth();
        double jerseyAnchorPxY = jersey.getCollarAnchorY().doubleValue() * jerseyImg.getHeight();
        double playerAnchorPxX = person.getNeckAnchorX().doubleValue() * scaledWidth;
        double playerAnchorPxY = person.getNeckAnchorY().doubleValue() * scaledHeight;

        int offsetX = (int) Math.round(jerseyAnchorPxX - playerAnchorPxX);
        int offsetY = (int) Math.round(jerseyAnchorPxY - playerAnchorPxY);

        // ★ dynamic padding: exactly enough to never clip the head, whatever the crop, plus a small margin
        int topPadding = Math.max(0, -offsetY) + 30;

        BufferedImage canvas = new BufferedImage(
                jerseyImg.getWidth(),
                jerseyImg.getHeight() + topPadding,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = canvas.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(scaledPlayer, offsetX, offsetY + topPadding, null);
        g.drawImage(jerseyImg, 0, topPadding, null);
        g.dispose();

        return toPngBytes(canvas);
    }

    private BufferedImage loadImage(String url) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            BufferedImage img = ImageIO.read(in);
            if (img == null) throw new IOException("Could not decode image at " + url);
            return img;
        }
    }

    private BufferedImage scaleImage(BufferedImage src, int targetWidth, int targetHeight) {
        BufferedImage scaled = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaled.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(src, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return scaled;
    }

    private byte[] toPngBytes(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        }
    }
}