package org.jboss.ochaloup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.arjuna.ats.arjuna.common.CoordinatorEnvironmentBean;
import com.arjuna.ats.arjuna.common.CoreEnvironmentBean;
import com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean;
import com.arjuna.ats.arjuna.common.arjPropertyManager;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;

@WebServlet(name="TestingServlet", urlPatterns={"/"})
public class TestingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    TestEJBBean bean;
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.print("Sending message...");
        bean.goSend();
        
        out.print("<h4>Transaction Object Store Environment bean info output</h4>");
        
        StringBuffer paramsMsg = new StringBuffer();
        
        /*
        CoreEnvironmentBean coreEnvBean = arjPropertyManager.getCoreEnvironmentBean();
        CoordinatorEnvironmentBean coordinatorEnvBean = arjPropertyManager.getCoordinatorEnvironmentBean();
        ObjectStoreEnvironmentBean objectStoreBean = arjPropertyManager.getObjectStoreEnvironmentBean();      
        
        String beanName = coreEnvBean.getClass().getSimpleName();
        paramsMsg.append(String.format("<li>%s.%s: %s</li>\n", beanName, "pid", coreEnvBean.getPid()));
        paramsMsg.append(String.format("<li>%s.%s: %s</li>\n", beanName, "timeoutFactor", coreEnvBean.getTimeoutFactor()));
        
        beanName = coordinatorEnvBean.getClass().getSimpleName();
        paramsMsg.append(String.format("<li>%s.%s: %s</li>\n", beanName, "defaultTimeout", coordinatorEnvBean.getDefaultTimeout()));
        paramsMsg.append(String.format("<li>%s.%s: %s</li>\n", beanName, "reaperMode", coordinatorEnvBean.getTxReaperMode()));
        
        beanName = objectStoreBean.getClass().getSimpleName();
        paramsMsg.append(String.format("<li>%s.%s: %s</li>\n", beanName, "objectStoreType", objectStoreBean.getObjectStoreType()));
        paramsMsg.append(String.format("<li>%s.%s: %s</li>\n", beanName, "objectStoreDir", objectStoreBean.getObjectStoreDir()));
        paramsMsg.append(String.format("<li>%s.%s: %s</li>\n", beanName, "localOSRoot", objectStoreBean.getLocalOSRoot()));
        paramsMsg.append(String.format("<li>%s.%s: %d</li>\n", beanName, "txLogSize", objectStoreBean.getTxLogSize()));
        */
        
        String beanName = BeanPopulator.class.getName();
        paramsMsg.append(String.format("<p><pre>bean: %s\n%s</pre></p>", beanName, BeanPopulator.printState()));
        
        out.printf("<ul>\n%s\n</ul>\n<br/><br/>",  paramsMsg);
    }
}
