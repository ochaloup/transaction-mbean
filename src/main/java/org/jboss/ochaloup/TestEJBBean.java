package org.jboss.ochaloup;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

@Stateless
public class TestEJBBean {
  private static final Logger log = Logger.getLogger(TestEJBBean.class.getName());

  private static final String message = "Hi, hou, I'm a message";
  
  @Resource(mappedName = "java:/JmsXA")
  ConnectionFactory connectionFactory;
  
  @Resource(mappedName = "java:jboss/queue/crashRecoveryQueue")
  Destination destinationQueue;
  
  public void goSend() {
    Connection connection = null;
    
    try {
      connection = connectionFactory.createConnection();
      // for sending messages you don't need to start connection
      // connection.start();
      
      Session session = connection.createSession(true, -1);
     
      MessageProducer producer = session.createProducer(destinationQueue);
      
      log.info("Sending message with text '" + message + "' to queue " + destinationQueue); 
      producer.send(session.createTextMessage(message));
    } catch (Exception e) {
      log.severe("Error in sending a message: " + e);
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception ignored) {}
        }
    }
  }
}
