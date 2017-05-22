package eservice;

import com.google.gson.Gson;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.StudentsDataSet;
import eservice.emailHelper.EmailSenderSSL;
import eservice.entity.Student;
import eservice.entity.impl.StudentImpl;
import eservice.pdfHelper.PDFMaker;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class InquiryWebSocket {
    private InquiryService inquiryService;
    private Session session;

    public InquiryWebSocket(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
//        inquiryService.add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        Gson gson = new Gson();
        Student student = gson.fromJson(data, StudentImpl.class);
        System.out.println("!!!!!!!!!!!!!!!!" + student.toString());

        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            StudentsDataSet dataSet = dbService.getUser(student);
            if(dataSet != null) {
                inquiryService.sendMessage("Пользователь найден, email ушел", this);
                String dest = createInquiry(dataSet, student.getiSex(), student.getsDestination());
                sendMessage(student.getsEmail(), dest);
            }
            else inquiryService.sendMessage("Пользователь не найден", this);
//            inquiryService.sendMessage("200 OK", this);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (DBException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        inquiryService.remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String createInquiry (StudentsDataSet student, int sex, String destination) throws IOException {
        String fileName = student.getlName() + student.getfName() + student.getOtchestvo() + student.getFormaNavch() + student.getYear() + student.getNapryam();
        String dest = "inquiries/studying/" + fileName + ".pdf";

        File file = new File(dest);
        file.getParentFile().mkdirs();
        new PDFMaker(dest, student, sex, destination).createPdf();

        return dest;
    }

    private void sendMessage(String toEmail, String dest) {
        EmailSenderSSL sslSender = new EmailSenderSSL("onatservices@gmail.com", "publicstatic");
        sslSender.send("Електронна послуга", "Ваша довідка прикріплена до цього листа.", toEmail, dest);
    }
}
