package io;

import data.BackupData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVHandler {

    public void exportToCSV(BackupData backupData, String filePath)
            throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("Key,Value,Type");
            writer.newLine();

            for (Map.Entry<String, Object> entry : backupData.getData().entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                writer.write("\"" + key + "\",\"" + value + "\",\""
                        + value.getClass().getSimpleName() + "\"");
                writer.newLine();
            }
        }
    }

    public Map<String, Object> importFromCSV(String filePath)
            throws IOException {

        Map<String, Object> data = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = parseCSVLine(line);

                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    String type = parts.length >= 3 ? parts[2] : "String";

                    data.put(key, convertValue(value, type));
                }
            }
        }

        return data;
    }

    private String[] parseCSVLine(String line) {

        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replace("\"", "").trim();
        }

        return parts;
    }

    private Object convertValue(String value, String type) {

        try {
            switch (type) {
                case "Integer":
                    return Integer.parseInt(value);

                case "Double":
                    return Double.parseDouble(value);

                case "Boolean":
                    return Boolean.parseBoolean(value);

                default:
                    return value;
            }
        } catch (NumberFormatException exception) {
            return value;
        }
    }
}