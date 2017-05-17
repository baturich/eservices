package eservice;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;


@WebServlet(name = "WebSocketInquiryServlet", urlPatterns = {"/eservice"})
public class WebSocketInquiryServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final InquiryService inquiryService;

    public WebSocketInquiryServlet() {
        this.inquiryService = new InquiryService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new InquiryWebSocket(inquiryService));
    }
}
