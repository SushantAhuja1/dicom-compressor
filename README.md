```markdown
# DICOM Compression Service (DCMTK Wrapper)

A robust Spring Boot utility designed to perform **Lossless JPEG Compression** on medical DICOM images. This project uses a "Hybrid Wrapper" approach, combining the ease of Java for file management with the high-performance C++ engine of **DCMTK**.

## 🚀 How It Works

The application operates as a command-line utility that automates the medical imaging workflow:

1.  **Scanning:** The Java layer scans a user-provided directory for `.dcm` files.
2.  **Execution:** For each file found, the application triggers a native process using `ProcessBuilder` to call the `dcmcjpeg.exe` tool located in the `/tools` folder.
3.  **Compression:** The tool applies **Lossless JPEG (Process 14)** compression (flag `+e1`). This ensures that 100% of the medical image quality is preserved while reducing file size by up to 50-70%.
4.  **In-Place Saving:** The service uses an atomic move operation to overwrite the original file with the compressed version, maintaining the same file name and directory structure.

## 🛠️ Project Stack

* **Java 17** (Amazon Corretto / OpenJDK)
* **Spring Boot 3.4.1**
* **DCMTK (3.7.0)** - Native C++ DICOM Toolkit
* **Maven** - Dependency Management

## 📂 Folder Structure

```text
compressor/
├── src/main/java/      # Java Source Code
├── tools/              # Native Binaries
│   ├── dcmcjpeg.exe    # The Compression Engine
│   └── *.dll           # Required Dynamic Link Libraries
├── pom.xml             # Maven Project Configuration
└── README.md           # Project Documentation

```

## ⚙️ Prerequisites

* **Java 17** installed.
* **Windows OS** (Current binaries are for Windows x86_64).
* **Microsoft Visual C++ Redistributable**: Required to run DCMTK's native `.dll` files.

## 🏃 Getting Started

1. **Clone the Repository:**
```bash
git clone [https://github.com/SushantAhuja1/dicom-compressor.git](https://github.com/SushantAhuja1/dicom-compressor.git)
cd dicom-compressor

```
2. **Build the Project:**
Using your IDE (IntelliJ) or the command line:
```bash
mvn clean install

```
3. **Run the Application:**
Run `DicomCompressorApplication.java`. The console will prompt you:
`Enter DICOM folder path: `
4. **Enter Path:**
Provide the full path to your DICOM images (e.g., `C:\Users\Name\Downloads\images`).

## ✅ Verification

Once the process finishes with `✅ Compressed: filename.dcm`, you can verify the result by checking the file properties. You should see a significant reduction in **Size on Disk** while the image remains readable by any standard DICOM viewer (like RadiAnt or Horos).
