package com.dicom.compressor.runner;

import com.dicom.compressor.service.DicomCompressionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class DicomRunner implements CommandLineRunner {

    private final DicomCompressionService compressionService;

    public DicomRunner(DicomCompressionService compressionService) {
        this.compressionService = compressionService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==========================================");
        System.out.println("   DCMTK COMPRESSION WRAPPER STARTED      ");
        System.out.println("==========================================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter DICOM folder path: ");
        String folderPath = scanner.nextLine().replace("\"", "");

        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && !file.getName().endsWith(".tmp")) {
                        compressionService.compressFile(file);
                    }
                }
            }
        } else {
            System.out.println("❌ Invalid folder path.");
        }
    }
}