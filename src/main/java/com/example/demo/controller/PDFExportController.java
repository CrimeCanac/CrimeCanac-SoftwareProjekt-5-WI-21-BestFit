package com.example.demo.controller;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.example.demo.model.entities.Geraet;
import com.example.demo.model.entities.Uebung;
import com.example.demo.service.GeraetService;
import com.example.demo.controller.ThymeleafTemplateRenderer;

@Controller
public class PDFExportController {

    @Autowired
    private GeraetService geraetService;
    @GetMapping("/geraete/pdf")
    public void exportGeraetelisteAsPDF(HttpServletResponse response) throws Exception {
        // Geräte abrufen
        List<Geraet> geraete = geraetService.alleGeraeteAbrufen();

        // Thymeleaf Template rendern
        String htmlContent = ThymeleafTemplateRenderer.render("geraeteliste", Map.of("geraete", geraete));

        // Debug: HTML-Content ausgeben
        System.out.println(htmlContent);

        // PDF-Header setzen
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=geraeteliste.pdf");

        // PDF generieren
        try (OutputStream os = response.getOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Fehler beim Generieren der PDF.");
        }
    }
    
 // Test-Methode zur Prüfung der PDF-Erstellung
    @GetMapping("/test")
    public void testPDF(HttpServletResponse response) {
        String htmlContent = "<html><body><h1>Test PDF</h1><p>Dies ist ein Test.</p></body></html>";

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=test.pdf");

        try (OutputStream os = response.getOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


