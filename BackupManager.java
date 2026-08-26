package io;

import data.BackupData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class BackupManager {

    private static final String BACKUP_DIRECTORY = "backups";

    public BackupManager() throws IOException {
        createBackupDirectory();
    }

    private void createBackupDirectory() throws IOException {
        Path backupPath = Paths.get(BACKUP_DIRECTORY);

        if (Files.notExists(backupPath)) {
            Files.createDirectories(backupPath);
        }
    }

    public String createBinaryBackup(String description, Map<String, Object> data)
            throws IOException {

        BackupData backupData = new BackupData(description, data);

        String fileName = "backup_" + backupData.getBackupId() + ".dat";
        Path filePath = Paths.get(BACKUP_DIRECTORY, fileName);

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {

            outputStream.writeObject(backupData);
        }

        return filePath.toString();
    }

    public BackupData restoreFromBinary(String filePath)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(filePath))) {

            return (BackupData) inputStream.readObject();
        }
    }
}