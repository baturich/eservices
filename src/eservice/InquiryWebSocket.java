package eservice;

import com.google.gson.Gson;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.StudentsDataSet;
import eservice.entity.Student;
import eservice.entity.impl.StudentImpl;
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
        System.out.println(student.toString());

        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
//            long userId = dbService.addUser("tully");
//            System.out.println("Added user id: " + userId);

            StudentsDataSet dataSet = dbService.getUser(student);
            if(dataSet != null) {
                inquiryService.sendMessage("Пользователь найден", this);
                createInquiry(dataSet);
            }
            else inquiryService.sendMessage("Пользователь не найден", this);
//            inquiryService.sendMessage("200 OK", this);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    private void createInquiry (StudentsDataSet student) throws IOException {
        String fileName = student.getlName() + student.getfName() + student.getOtchestvo() + student.getBirthdate() + student.getNapryam();
        String dest = "inquiries/studying/" + fileName + ".pdf";

        File file = new File(dest);
        file.getParentFile().mkdirs();
        new MyPdfWriter(dest, student).createPdf();
    }
}
