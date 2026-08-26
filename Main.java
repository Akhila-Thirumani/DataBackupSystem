import compression.CompressionUtils;
import data.BackupData;
import data.RecoveryPoint;
import io.BackupManager;
import io.CSVHandler;
import io.JsonHandler;
import io.VersionManager;
import utility.FileComparisonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            BackupManager backupManager = new BackupManager();
            CSVHandler csvHandler = new CSVHandler();
            JsonHandler jsonHandler = new JsonHandler();
            CompressionUtils compressionUtils = new CompressionUtils();
            VersionManager versionManager = new VersionManager();
            FileComparisonUtils comparisonUtils =
                    new FileComparisonUtils();

            Map<String, Object> data = createSampleData();

            boolean running = true;

            while (running) {

                displayMenu();

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        createBackup(backupManager, data);
                        break;

                    case "2":
                        exportCSV(backupManager, csvHandler, data);
                        break;

                    case "3":
                        exportJSON(backupManager, jsonHandler, data);
                        break;

                    case "4":
                        compressBackup(compressionUtils);
                        break;

                    case "5":
                        listVersions(versionManager);
                        break;

                    case "6":
                        createRecoveryPoint(data);
                        break;

                    case "7":
                        compareFiles(comparisonUtils);
                        break;

                    case "8":
                        running = false;
                        System.out.println("Exiting Backup System.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception exception) {
            System.out.println("Application error: "
                    + exception.getMessage());
        }
    }

    private static void displayMenu() {

        System.out.println("\n===== DATA BACKUP SYSTEM =====");
        System.out.println("1. Create Binary Backup");
        System.out.println("2. Export CSV");
        System.out.println("3. Export JSON");
        System.out.println("4. Compress Backup");
        System.out.println("5. List Backup Versions");
        System.out.println("6. Create Recovery Point");
        System.out.println("7. Compare Files");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");
    }

    private static Map<String, Object> createSampleData() {

        Map<String, Object> data = new HashMap<>();

        data.put("username", "Akhila");
        data.put("project", "Data Backup System");
        data.put("version", 7);
        data.put("active", true);

        return data;
    }

    private static void createBackup(
            BackupManager backupManager,
            Map<String, Object> data) {

        try {

            String filePath = backupManager.createBinaryBackup(
                    "Manual backup",
                    data
            );

            System.out.println("Backup created successfully.");
            System.out.println("File: " + filePath);

        } catch (IOException exception) {

            System.out.println(
                    "Backup failed: " + exception.getMessage()
            );
        }
    }

    private static void exportCSV(
            BackupManager backupManager,
            CSVHandler csvHandler,
            Map<String, Object> data) {

        try {

            BackupData backup = new BackupData(
                    "CSV backup",
                    data
            );

            csvHandler.exportToCSV(
                    backup,
                    "backups/backup.csv"
            );

            System.out.println("CSV backup created.");

        } catch (IOException exception) {

            System.out.println(
                    "CSV export failed: " + exception.getMessage()
            );
        }
    }

    private static void exportJSON(
            BackupManager backupManager,
            JsonHandler jsonHandler,
            Map<String, Object> data) {

        try {

            BackupData backup = new BackupData(
                    "JSON backup",
                    data
            );

            jsonHandler.exportToJson(
                    backup,
                    "backups/backup.json"
            );

            System.out.println("JSON backup created.");

        } catch (IOException exception) {

            System.out.println(
                    "JSON export failed: " + exception.getMessage()
            );
        }
    }

    private static void compressBackup(
            CompressionUtils compressionUtils) {

        try {

            String source = "backups/backup.csv";
            String destination = "backups/backup.csv.gz";

            compressionUtils.compress(
                    source,
                    destination
            );

            System.out.println("File compressed successfully.");
            System.out.println("Compressed file: " + destination);

        } catch (IOException exception) {

            System.out.println(
                    "Compression failed: " + exception.getMessage()
            );
        }
    }

    private static void listVersions(
            VersionManager versionManager) {

        try {

            List<BackupData> versions =
                    versionManager.listAllVersions();

            if (versions.isEmpty()) {
                System.out.println("No backup versions found.");
                return;
            }

            System.out.println("\n===== BACKUP VERSIONS =====");

            for (BackupData backup : versions) {
                System.out.println(backup);
                System.out.println("---------------------------");
            }

        } catch (Exception exception) {

            System.out.println(
                    "Unable to list versions: "
                            + exception.getMessage()
            );
        }
    }

    private static void createRecoveryPoint(
            Map<String, Object> data) {

        BackupData backup = new BackupData(
                "Recovery point backup",
                data
        );

        RecoveryPoint recoveryPoint =
                new RecoveryPoint(
                        backup.getBackupId(),
                        "Recovery point created"
                );

        System.out.println("\nRecovery point created:");
        System.out.println(recoveryPoint);
    }

    private static void compareFiles(
            FileComparisonUtils comparisonUtils) {

        try {

            String firstFile = "backups/backup.csv";
            String secondFile = "backups/backup.csv";

            boolean equal =
                    comparisonUtils.areFilesEqual(
                            firstFile,
                            secondFile
                    );

            System.out.println(
                    "Files are equal: " + equal
            );

        } catch (IOException exception) {

            System.out.println(
                    "Comparison failed: "
                            + exception.getMessage()
            );
        }
    }
}