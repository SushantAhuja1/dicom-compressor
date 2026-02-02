package com.dicom.compressor.runner;

import com.dicom.compressor.service.DicomCompressionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DicomRunner implements CommandLineRunner {

    private final DicomCompressionService compressionService;

    public DicomRunner(DicomCompressionService compressionService) {
        this.compressionService = compressionService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==========================================");
        System.out.println("   DICOM LOSSLESS COMPRESSOR (DCMTK)      ");
        System.out.println("==========================================");

        if (args.length == 0) {
            System.out.println("Usage: java -jar compressor.jar <folder_path>");
            System.out.println("Example: java -jar compressor.jar \"C:\\Images\\Batch1\"");
            return;
        }

        // Clean and normalize the input path to handle Windows/PowerShell quote issues
        String inputPath = args[0].replace("\"", "").trim();
        Path path = Paths.get(inputPath).toAbsolutePath().normalize();

        if (Files.exists(path) && Files.isDirectory(path)) {
            File folder = path.toFile();
            File[] files = folder.listFiles();

            int processedCount = 0;

            if (files != null && files.length > 0) {
                System.out.println("Found directory: " + path);
                System.out.println("Starting compression process...");

                for (File file : files) {
                    // Only process files that likely have DICOM data
                    if (file.isFile() && isDicomFile(file)) {
                        compressionService.compressFile(file);
                        processedCount++;
                    }
                }
                System.out.println("\n✅ Task Finished!");
                System.out.println("Total files processed: " + processedCount);
            } else {
                System.out.println("ℹ️ The folder is empty.");
            }
        } else {
            System.err.println("❌ Invalid folder path: " + path);
            System.err.println("Please check if the folder exists and you have permissions.");
        }
    }

    /**
     * Basic check to identify DICOM files.
     * In a real clinical environment, you'd check for the 'DICM' preamble,
     * but checking extension is sufficient for this utility.
     */
    private boolean isDicomFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".dcm") || name.endsWith(".dicom") || !name.contains(".");
    }
}