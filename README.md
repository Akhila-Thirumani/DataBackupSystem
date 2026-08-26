# Data Backup System

## Project Overview

The Data Backup System is a Java-based application developed to
create, store, restore, and manage backup data using multiple
file formats.

The project demonstrates Java file handling, object serialization,
CSV and JSON processing, file compression, backup version management,
file comparison, recovery points, and scheduled backups.

## Features

- Binary backup using Java serialization
- Binary backup restoration
- CSV export and import
- JSON export and import using Gson
- Backup version management
- File comparison
- File merging
- GZIP compression and decompression
- Recovery point management
- Scheduled backup support
- File and directory utilities
- Exception handling
- Try-with-resources

## Technologies Used

- Java
- Java I/O
- Java NIO
- Java Serialization
- Gson
- GZIP
- Collections Framework
- ScheduledExecutorService

## Project Structure

```text
src/
├── compression/
│   └── CompressionUtils.java
│
├── data/
│   ├── BackupData.java
│   └── RecoveryPoint.java
│
├── io/
│   ├── BackupManager.java
│   ├── CSVHandler.java
│   ├── FileUtils.java
│   ├── JsonHandler.java
│   └── VersionManager.java
│
├── scheduler/
│   └── BackupScheduler.java
│
├── utility/
│   └── FileComparisonUtils.java
│
└── Main.java
