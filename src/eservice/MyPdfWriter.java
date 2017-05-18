package eservice;


import dbService.dataSets.StudentsDataSet;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyPdfWriter {

    private String DEST;
    private StudentsDataSet student;

    public MyPdfWriter(String DEST, StudentsDataSet student) {
        this.DEST = DEST;
        this.student = student;
    }

    public void createPdf() throws IOException {
        //Initialize PDF writer
        FileOutputStream fos = new FileOutputStream(DEST);
        PdfWriter pdfWriter = new PdfWriter(fos);

        //Initialize pdf document
        PdfDocument pdf = new PdfDocument(pdfWriter);

        //Initialize document
        Document document = new Document(pdf);

        String FONT = "./src/resources/font/FreeSans.ttf";

        PdfFont pdfFont = PdfFontFactory.createFont(FONT, "Identity-H", true);

        //Add paragraph to the content
        document.add(new Paragraph(infoForDocument()).setFont(pdfFont));

        //Close document
        document.close();
    }

    private String infoForDocument() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Прізвище: " + student.getlName() + " \n"
                + "Ім\'я: " + student.getfName() + " \n"
                + "По-батькові: " + student.getOtchestvo() + "\n"
                + "Дата народження: " + student.getBirthdate() + "\n"
                + "Номер мобільного: " + student.getMobilePhone() + "\n"
                + "Напрям підготовки: " + student.getNapryam() + "\n"
                + "Курс: " + student.getYear());
        String result = stringBuilder.toString();
        return result;
    }
}
