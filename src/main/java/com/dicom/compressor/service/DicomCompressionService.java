package com.dicom.compressor.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class DicomCompressionService {

    // Path to the tool
    private static final String TOOL_PATH = "tools/dcmcjpeg.exe";

    public void compressFile(File originalFile) {
        System.out.println("Processing: " + originalFile.getName());

        File compressedFile = new File(originalFile.getAbsolutePath() + ".tmp");

        File tool = new File(TOOL_PATH);
        if (!tool.exists()) {
            System.err.println("❌ CRITICAL ERROR: Could not find dcmcjpeg.exe in 'tools' folder!");
            return;
        }

        try {
            // UPDATED COMMAND:
            // +e1 = Encode Lossless (Non-hierarchical, First-Order Prediction)
            ProcessBuilder pb = new ProcessBuilder(
                    tool.getAbsolutePath(),
                    "+e1",  // <--- CHANGED FROM "+ls" TO "+e1"
                    originalFile.getAbsolutePath(),
                    compressedFile.getAbsolutePath()
            );

            pb.inheritIO();

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // Compression success: Overwrite original
                Files.move(compressedFile.toPath(), originalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✅ Compressed: " + originalFile.getName());
            } else {
                System.err.println("❌ Failed. DCMTK Error Code: " + exitCode);
                if (compressedFile.exists()) compressedFile.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (compressedFile.exists()) compressedFile.delete();
        }
    }
}