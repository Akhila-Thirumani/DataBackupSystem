package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.BackupData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonHandler {

    private final Gson gson;

    public JsonHandler() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void exportToJson(BackupData backupData, String filePath)
            throws IOException {

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(backupData, writer);
        }
    }

    public BackupData importFromJson(String filePath)
            throws IOException {

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, BackupData.class);
        }
    }
}