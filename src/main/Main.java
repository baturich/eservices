package main;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.StudentsDataSet;
import eservice.WebSocketInquiryServlet;
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

        server.start();
        server.join();
    }
}
