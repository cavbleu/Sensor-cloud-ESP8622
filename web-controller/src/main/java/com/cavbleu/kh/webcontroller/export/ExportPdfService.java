package com.cavbleu.kh.webcontroller.export;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-11-27 22:17
 */


public interface ExportPdfService {
    ByteArrayInputStream exportReceiptPdf(String templateName, Map<String, Object> data);
}
