package com.cavbleu.kh.webcontroller.export.impl;

import com.cavbleu.kh.webcontroller.export.ExportPdfService;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-12-25 22:17
 */

@Service
@RequiredArgsConstructor
public class ExportPdfServiceImpl  implements ExportPdfService {
    private Logger logger = LoggerFactory.getLogger(ExportPdfServiceImpl.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public ByteArrayInputStream exportReceiptPdf(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);
//
//        Document document = Jsoup.parse(htmlContent,"UTF-8");
//        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream, false);
            renderer.finishPDF();
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (DocumentException e) {
            logger.error(e.getMessage(), e);
        }

        return byteArrayInputStream;
    }
}
