package scheduler;

import io.BackupManager;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackupScheduler {

    private final ScheduledExecutorService scheduler;
    private final BackupManager backupManager;

    public BackupScheduler() throws IOException {
        scheduler = Executors.newScheduledThreadPool(1);
        backupManager = new BackupManager();
    }

    public void scheduleBackup(String description,
                               Map<String, Object> data,
                               long delay,
                               long period) {

        scheduler.scheduleAtFixedRate(() -> {
            try {
                String filePath = backupManager.createBinaryBackup(
                        description,
                        data
                );

                System.out.println("Scheduled backup created: " + filePath);

            } catch (IOException exception) {
                System.out.println(
                        "Scheduled backup failed: " + exception.getMessage()
                );
            }
        }, delay, period, TimeUnit.SECONDS);
    }

    public void stopScheduler() {
        scheduler.shutdown();

        System.out.println("Backup scheduler stopped.");
    }
}