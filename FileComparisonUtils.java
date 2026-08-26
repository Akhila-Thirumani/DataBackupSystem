package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FileComparisonUtils {

    public boolean areFilesEqual(String firstFile, String secondFile)
            throws IOException {

        Path firstPath = Paths.get(firstFile);
        Path secondPath = Paths.get(secondFile);

        if (!Files.exists(firstPath) || !Files.exists(secondPath)) {
            return false;
        }

        return Files.mismatch(firstPath, secondPath) == -1;
    }

    public List<String> findDifferences(String firstFile, String secondFile)
            throws IOException {

        List<String> firstLines = Files.readAllLines(Paths.get(firstFile));
        List<String> secondLines = Files.readAllLines(Paths.get(secondFile));

        Set<String> differences = new LinkedHashSet<>();

        for (String line : firstLines) {
            if (!secondLines.contains(line)) {
                differences.add("Only in first file: " + line);
            }
        }

        for (String line : secondLines) {
            if (!firstLines.contains(line)) {
                differences.add("Only in second file: " + line);
            }
        }

        return differences.stream().toList();
    }

    public void mergeFiles(String firstFile, String secondFile,
                            String outputFile) throws IOException {

        Set<String> mergedLines = new LinkedHashSet<>();

        mergedLines.addAll(Files.readAllLines(Paths.get(firstFile)));
        mergedLines.addAll(Files.readAllLines(Paths.get(secondFile)));

        Files.write(Paths.get(outputFile), mergedLines);
    }
}