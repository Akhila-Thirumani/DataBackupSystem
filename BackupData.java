package data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BackupData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String backupId;
    private LocalDateTime backupTime;
    private Map<String, Object> data;
    private String description;

    public BackupData(String description, Map<String, Object> data) {
        this.backupId = UUID.randomUUID().toString();
        this.backupTime = LocalDateTime.now();
        this.description = description;
        this.data = new HashMap<>(data);
    }

    public String getBackupId() {
        return backupId;
    }

    public LocalDateTime getBackupTime() {
        return backupTime;
    }

    public Map<String, Object> getData() {
        return new HashMap<>(data);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Backup ID: " + backupId +
                "\nTime: " + backupTime +
                "\nDescription: " + description +
                "\nData Items: " + data.size();
    }
}