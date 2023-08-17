package org.example;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class PDFreport {
    public static void createReport(String s){
        String report = "report.pdf";
        try {
            PdfWriter pdfWriter = new PdfWriter(report);
            PdfDocument pd = new PdfDocument(pdfWriter);
            pd.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pd);
            document.add(new Paragraph(s));
            document.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
