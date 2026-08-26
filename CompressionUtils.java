package compression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionUtils {

    public void compress(String sourceFile, String compressedFile)
            throws IOException {

        try (FileInputStream inputStream = new FileInputStream(sourceFile);
             GZIPOutputStream outputStream =
                     new GZIPOutputStream(new FileOutputStream(compressedFile))) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public void decompress(String compressedFile, String destinationFile)
            throws IOException {

        try (GZIPInputStream inputStream =
                     new GZIPInputStream(new FileInputStream(compressedFile));
             FileOutputStream outputStream =
                     new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}