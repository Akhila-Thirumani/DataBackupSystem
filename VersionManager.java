package io;

import data.BackupData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VersionManager {

    private static final String VERSION_DIRECTORY = "backups/versions";

    private final BackupManager backupManager;

    public VersionManager() throws IOException {
        backupManager = new BackupManager();
        createVersionDirectory();
    }

    private void createVersionDirectory() throws IOException {
        Path versionPath = Paths.get(VERSION_DIRECTORY);

        if (Files.notExists(versionPath)) {
            Files.createDirectories(versionPath);
        }
    }

    public void saveVersion(BackupData backupData) throws IOException {

        String fileName = "version_" + backupData.getBackupId() + ".dat";
        Path filePath = Paths.get(VERSION_DIRECTORY, fileName);

        try (var outputStream = new java.io.ObjectOutputStream(
                new java.io.FileOutputStream(filePath.toFile()))) {

            outputStream.writeObject(backupData);
        }
    }

    public List<BackupData> listAllVersions()
            throws IOException, ClassNotFoundException {

        List<BackupData> versions = new ArrayList<>();

        Path versionPath = Paths.get(VERSION_DIRECTORY);

        if (Files.exists(versionPath)) {

            try (var files = Files.list(versionPath)) {

                files.filter(path -> path.toString().endsWith(".dat"))
                        .forEach(path -> {
                            try {
                                versions.add(
                                        backupManager.restoreFromBinary(path.toString())
                                );
                            } catch (IOException | ClassNotFoundException exception) {
                                System.out.println(
                                        "Unable to read version: " + path.getFileName()
                                );
                            }
                        });
            }
        }

        versions.sort(
                Comparator.comparing(
                        BackupData::getBackupTime
                ).reversed()
        );

        return versions;
    }

    public void deleteVersion(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}