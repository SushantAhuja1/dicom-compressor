
# DICOM Compression Service (DCMTK Wrapper)

A high-performance **Spring Boot–based command-line utility** for performing **JPEG-LS Lossless compression** on medical DICOM images.  
This project follows a **Hybrid Wrapper Architecture**, combining Java’s portability and file system management with the optimized native C++ compression engine provided by **DCMTK**.

The objective is to significantly reduce storage usage **without any diagnostic image quality loss**, making it suitable for clinical and archival workflows.

---

## 🚀 How It Works

The compression workflow consists of the following steps:

1. **Directory Scanning**  
   The Java service scans the user-provided folder recursively to locate `.dcm` files.

2. **Native Tool Invocation**  
   For each file found, Java uses `ProcessBuilder` to invoke DCMTK’s native **JPEG-LS compressor** (`dcmcjpls.exe`) from the local `tools/` directory.

3. **Lossless Compression**  
   The tool applies **JPEG-LS Lossless compression** which provides:

   - ✔ Faster compression speed  
   - ✔ Lower CPU usage  
   - ✔ Better compression ratio than classic JPEG Lossless
   - 
   Typical size reduction: **40–70% (varies by modality)**

4. **Atomic File Replacement**  
   The compressed output safely replaces the original file using atomic file move operations to avoid corruption and partial writes.

---

## 🛠️ Technology Stack

- **Java 17** (Amazon Corretto / OpenJDK)
- **Spring Boot 3.4.1**
- **DCMTK 3.7.0**
- **Maven**

---

## ⚙️ Prerequisites

### Required

- **Java 17**
- **Windows OS (64-bit)**
- **Microsoft Visual C++ Redistributable (x64)**

---

## 🏃 Getting Started

### 1️⃣ Clone Repository

git clone https://github.com/SushantAhuja1/dicom-compressor.git  
cd dicom-compressor

---

### 2️⃣ Build Executable JAR

mvn clean install

Generated file location:

target/compressor-0.0.1-SNAPSHOT.jar

---

### 3️⃣ Run Application

java -jar .\target\compressor-0.0.1-SNAPSHOT.jar <source_folder>

---

Open compressed files using:

- RadiAnt DICOM Viewer  
- MicroDicom  
- Horos  
