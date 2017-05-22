package main;

import dbService.dataSets.StudentsDataSet;
import eservice.InquiryService;
import eservice.InquiryWebSocket;
import eservice.WebSocketInquiryServlet;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new WebSocketInquiryServlet()), "/eservice");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        /*//Создание записи в БД
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long studentId = dbService.addStudentExmple();
            System.out.println("Added user id: " + studentId);

            StudentsDataSet studentsDataSet = dbService.getUser(studentId);
            System.out.println(studentsDataSet.toString());

        } catch (DBException e) {
            e.printStackTrace();
        }*/
//        new InquiryWebSocket(new InquiryService()).createInquiry(new StudentsDataSet("Юрій", "Бишляга", "Олегович", "02.11.1996", "566-45-82", "Телекоммунікації та радіотехніка", 1, 3, 1, 1, 1), 1, "Управління Пенсійного фонду України в Приморському районі м. Одеси");

        server.start();
        server.join();


    }
}
