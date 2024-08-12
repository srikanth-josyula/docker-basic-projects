package com.sample.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

@Service
public class FileService {

    private static final Logger logger = LogManager.getLogger(FileService.class);

    //private static final String TEMP_DIR = "/Users/srikanthjosyula/Documents/workspace/";
    
	// Use system property or default path
    private static final String TEMP_DIR = System.getProperty("temp.path", "/default/temp/path");
	private static final String TEMP_PDF_PATH = TEMP_DIR + "converted.pdf";

    @SuppressWarnings("deprecation")
	public InputStreamResource convertTiffToPdf(MultipartFile file) throws IOException {
        File tempDir = new File(TEMP_DIR);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        File tempTiffFile = File.createTempFile("temp", ".tiff", tempDir);
        File tempPdfFile = new File(TEMP_PDF_PATH);

        RandomAccessFileOrArray myTiffFile = null;

        try {
            // Save the uploaded TIFF file to a temporary location
            file.transferTo(tempTiffFile);
            logger.info("Saved TIFF file to temporary location: {}", tempTiffFile.getAbsolutePath());

            // Convert TIFF to PDF
            myTiffFile = new RandomAccessFileOrArray(tempTiffFile.getAbsolutePath());
            int numberOfPages = TiffImage.getNumberOfPages(myTiffFile);
            Document document = new Document();
            PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(document, new FileOutputStream(tempPdfFile));
                document.open();

                for (int i = 1; i <= numberOfPages; i++) {
                    Image tempImage = TiffImage.getTiffImage(myTiffFile, i);
                    document.add(tempImage);
                }
                logger.info("Successfully added pages to PDF document");
            } catch (FileNotFoundException | DocumentException e) {
                logger.error("Error while converting TIFF to PDF", e);
                throw new IOException("Error while converting TIFF to PDF: " + e.getMessage(), e);
            } finally {
                if (document.isOpen()) {
                    document.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        } finally {
            // Clean up temporary TIFF file
            Files.deleteIfExists(tempTiffFile.toPath());
            logger.info("Deleted temporary TIFF file: {}", tempTiffFile.getAbsolutePath());
            // Close RandomAccessFileOrArray
            if (myTiffFile != null) {
                myTiffFile.close();
            }
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(tempPdfFile.toPath()));
        logger.info("Converted PDF file ready for download: {}", tempPdfFile.getAbsolutePath());
        return new InputStreamResource(bis);
    }
}
