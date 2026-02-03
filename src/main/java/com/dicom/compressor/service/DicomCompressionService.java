package com.dicom.compressor.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class DicomCompressionService {

    // IMPORTANT: Point directly to the .exe file, not just the folder
    private static final String TOOL_NAME = "dcmcjpls.exe";
    private static final String TOOL_FOLDER = "tools";

    public void compressFile(File originalFile) {
        System.out.println("Processing: " + originalFile.getName());

        // Construct the absolute path to the .exe relative to where the JAR is running
        File toolFile = new File(System.getProperty("user.dir") + File.separator + TOOL_FOLDER + File.separator + TOOL_NAME);

        if (!toolFile.exists()) {
            System.err.println("ERROR: Executable not found at: " + toolFile.getAbsolutePath());
            return;
        }

        File compressedFile = new File(originalFile.getAbsolutePath() + ".tmp");

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    toolFile.getAbsolutePath(), // Use the verified full path to the .exe
                    originalFile.getAbsolutePath(),
                    compressedFile.getAbsolutePath()
            );

            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                Files.move(compressedFile.toPath(), originalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✅ Compressed: " + originalFile.getName());
            } else {
                System.err.println("❌ DCMTK Error Code: " + exitCode);
                if (compressedFile.exists()) compressedFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}