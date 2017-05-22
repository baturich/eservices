package eservice.pdfHelper;


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
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
import java.net.MalformedURLException;

public class PDFMaker {

    private String DEST;
    private String destination;
    private int sex;
    private StudentsDataSet student;
    private static final String IMAGE1 = "./src/resources/img/1.jpg";
    private static final String IMAGE2 = "./src/resources/img/2.jpg";
    private static final String IMAGE3 = "./src/resources/img/3.jpg";
    private static final String IMAGE4 = "./src/resources/img/4.jpg";

    public PDFMaker(String DEST, StudentsDataSet student, int sex, String destination) {
        this.DEST = DEST;
        this.student = student;
        this.sex = sex;
        this.destination = destination;
    }

    public void createPdf() throws java.io.IOException {
        int yCoord;
        //Initialize PDF writer
        FileOutputStream fos = new FileOutputStream(DEST);
        PdfWriter pdfWriter = new PdfWriter(fos);

        //Initialize pdf document
        PdfDocument pdf = new PdfDocument(pdfWriter);

        //Initialize document
        Document document = new Document(pdf);

        String FONT = "./src/resources/font/TimesNewRomanRegular.ttf";
        String FONT_ITALIC = "./src/resources/font/TimesNewRomanItalic.ttf";
        String FONT_BOLD = "./src/resources/font/TimesNewRomanBold.ttf";
        PdfFont pdfFont = PdfFontFactory.createFont(FONT, "Identity-H", true);
        PdfFont pdfFontItalic = PdfFontFactory.createFont(FONT_ITALIC, "Identity-H", true);
        PdfFont pdfFontBold = PdfFontFactory.createFont(FONT_BOLD, "Identity-H", true);

        //Add the content
        document.add (getImage(3));

        yCoord = 842-195-40;
        document.add(new Paragraph("Д О В І Д К А")
                .setFont(pdfFontBold)
                .setFontSize(14)
                .setFixedPosition((595-70)/2, yCoord, 595)
        );

        document.add(new Paragraph(emptyPlace(11)));

        yCoord -=60;
        document.add(new Paragraph(infoForDocument())
                .setFont(pdfFont)
                .setFontSize(14)
                .setMarginLeft(40)
                .setFirstLineIndent(40));
//                .setFixedPosition(80,yCoord,490));

        yCoord -= 40;
        document.add(new Paragraph(dateOfEnding())
                .setFont(pdfFont)
                .setFontSize(14)
                .setFirstLineIndent(40)
                .setMarginLeft(40)
//                .setFixedPosition(80, yCoord, 490)
        );

        yCoord -= 40;
        document.add(new Paragraph(destination())
                .setFont(pdfFont)
                .setFontSize(14)
                .setFirstLineIndent(40)
                .setMarginLeft(40)
//                .setFixedPosition(80, yCoord, 490)
        );

        yCoord -= 120;
        document.add(getImage(4)
                .setFixedPosition(0, ((float) yCoord)));

        //Close document
        document.close();
    }

    private Image getImage(int numb) throws MalformedURLException {
        Image image = null;
        switch (numb) {
            case 1:
                image = new Image(ImageDataFactory.create(IMAGE1));
                image.scaleToFit(40,40);
                image.setFixedPosition((595 - 40)/2, 842 - 10 - 40);
                break;
            case 2:
                image = new Image(ImageDataFactory.create(IMAGE2));
                image.scaleToFit(60,60);
//                image.setFixedPosition((595 - 40)/2, 842 - 10 - 40);
                break;
            case 3:
                image = new Image(ImageDataFactory.create(IMAGE3));
                image.scaleToFit(595,190);
                image.setFixedPosition(0, 842 - 190);
                break;
            case 4:
                image = new Image(ImageDataFactory.create(IMAGE4));
                image.scaleToFit(595,94);
                break;
        }
        return image;
    }

    private String infoForDocument() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Видана ");
        stringBuilder.append(lNameForm().toUpperCase());
        stringBuilder.append(" ");
        stringBuilder.append(fNameForm());
        stringBuilder.append(" ");
        stringBuilder.append(otchestvoForm());
        stringBuilder.append(", ");
        stringBuilder.append(student.getBirthdate());
        stringBuilder.append(" р.н., про те, що ");
        if(sex == 1)
            stringBuilder.append("він");
        else
            stringBuilder.append("вона");
        stringBuilder.append("  дійсно є ");
        if(sex == 1)
            stringBuilder.append("студентом ");
        else
            stringBuilder.append("студенткою ");
        stringBuilder.append(student.getYear());
        stringBuilder.append("-го курсу ");
        if(student.getFormaNavch() == 1) stringBuilder.append("денної ");
        else stringBuilder.append("заочної ");
        if(student.getBudget() == 1) stringBuilder.append("бюджетної ");
        else stringBuilder.append("контрактної ");
        stringBuilder.append("форми навчання Одеської національної академії зв'язку  ім. О.С. Попова.");


        return stringBuilder.toString();
    }

    public String emptyPlace(int j) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i<j; i++)
            builder.append("\n");
        return builder.toString();
    }

    private String dateOfEnding() {
        return "\t Термін закінчення навчання " +
                "30.06.2018" +
                " року.";
    }

    private String destination() {
        return "\t Дана для подання до " +
                destination +
                ".";
    }

    private String fNameForm(){
        if(student.getfName().equals("Ігор")) return "Ігорю";
        //перша відміна
        if(student.getfName().endsWith("а") || student.getfName().endsWith("я")) {
            if(student.getfName().endsWith("іа") || student.getfName().endsWith("ія"))
                return (student.getfName().substring(0, student.getfName().length() - 2) + "ії");
            return (student.getfName().substring(0, student.getfName().length() - 1) + "і");
        }
        //друга відміна
        if(sex == 1) {
            if(student.getfName().endsWith("о"))
                return (student.getfName().substring(0, student.getfName().length() - 1) + "у");
            if(student.getfName().endsWith("й") || student.getfName().endsWith("ь"))
                return (student.getfName().substring(0, student.getfName().length() - 1) + "ю");
            if(student.getfName().endsWith("іш"))
                return (student.getfName().substring(0, student.getfName().length() - 2) + "ошеві");
            return student.getfName() + "у";
        }
        //третя відміна
        else
            return student.getfName() + "і";
    }

    private String lNameForm(){
        if(student.getlName().endsWith("га") || student.getlName().endsWith("ґа"))
            return student.getlName().substring(0,student.getlName().length() - 2) + "зі";
        if(student.getlName().endsWith("єць"))
            return (student.getlName().substring(0, student.getlName().length() - 3) + "йцю");
        if(student.getlName().endsWith("іс"))
            return (student.getlName().substring(0, student.getlName().length() - 2) + "осу");
        if(student.getlName().endsWith("ідь"))
            return (student.getlName().substring(0, student.getlName().length() - 3) + "едю");
        if(student.getlName().endsWith("ська"))
            return (student.getlName().substring(0, student.getlName().length() - 4) + "ській");
        if(student.getlName().endsWith("цька"))
            return (student.getlName().substring(0, student.getlName().length() - 4) + "цькій");

        //прикметникового типу на -ов, -ев (-єв), -ів (-їв), -ин, -ін (-їн)
        if(student.getlName().endsWith("ов") || student.getlName().endsWith("ев") || student.getlName().endsWith("єв")
                || student.getlName().endsWith("ів")  || student.getlName().endsWith("їв")  || student.getlName().endsWith("ин")
                || student.getlName().endsWith("ін")  || student.getlName().endsWith("їн") )
            return student.getlName() + "у";

        //прикметникового типу на -ий, -ій
        if(student.getlName().endsWith("ий") || student.getlName().endsWith("ій"))
            return student.getlName().substring(0,student.getlName().length() - 2) + "ому";

        //перша відміна
        if(student.getlName().endsWith("а") || student.getlName().endsWith("я")) {
            if(student.getlName().endsWith("іа") || student.getlName().endsWith("ія"))
                return (student.getlName().substring(0, student.getlName().length() - 2) + "ії");
            return (student.getlName().substring(0, student.getlName().length() - 1) + "і");
        }
        //друга відміна
        if(sex == 1) {
            if(student.getlName().endsWith("о")) {
                if(student.getlName().endsWith("ьо"))
                    return student.getlName().substring(0, student.getlName().length() - 2) + "ю";
                return (student.getlName().substring(0, student.getlName().length() - 1) + "у");
            }
            if(student.getlName().endsWith("й") || student.getlName().endsWith("ь"))
                return (student.getlName().substring(0, student.getlName().length() - 1) + "ю");
            if(student.getlName().endsWith("іш"))
                return (student.getlName() + "у");
            return student.getlName() + "у";
        }
        else
            return student.getlName();
    }

    private String otchestvoForm() {
        if(sex == 1)
            return student.getOtchestvo() + "у";
        else
            return student.getOtchestvo().substring(0, student.getOtchestvo().length() - 1) + "і";
    }
}
