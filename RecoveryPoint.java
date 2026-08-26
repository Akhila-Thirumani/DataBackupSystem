package data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class RecoveryPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String recoveryPointId;
    private String backupId;
    private LocalDateTime createdTime;
    private String description;

    public RecoveryPoint(String backupId, String description) {
        this.recoveryPointId = UUID.randomUUID().toString();
        this.backupId = backupId;
        this.createdTime = LocalDateTime.now();
        this.description = description;
    }

    public String getRecoveryPointId() {
        return recoveryPointId;
    }

    public String getBackupId() {
        return backupId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Recovery Point ID: " + recoveryPointId +
                "\nBackup ID: " + backupId +
                "\nCreated Time: " + createdTime +
                "\nDescription: " + description;
    }
}