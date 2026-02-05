package com.dicom.compressor.controller;

import com.dicom.compressor.dto.CompressionRequest;
import com.dicom.compressor.dto.CompressionResponse;
import com.dicom.compressor.service.DicomCompressionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dicom")
public class DicomController {

    private final DicomCompressionService compressionService;

    public DicomController(DicomCompressionService compressionService) {
        this.compressionService = compressionService;
    }

    @PostMapping("/compress")
    public ResponseEntity<CompressionResponse> compressDicomFiles(@RequestBody CompressionRequest request) {
        try {
            int processedCount = compressionService.compressFolder(request.getFolderPath(), request.getJcode());
            
            CompressionResponse response = new CompressionResponse(
                    true,
                    "Compression completed successfully",
                    request.getJcode(),
                    processedCount
            );
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            CompressionResponse response = new CompressionResponse(
                    false,
                    e.getMessage(),
                    request.getJcode(),
                    0
            );
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            CompressionResponse response = new CompressionResponse(
                    false,
                    "Error during compression: " + e.getMessage(),
                    request.getJcode(),
                    0
            );
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
