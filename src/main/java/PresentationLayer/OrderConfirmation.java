/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author s_ele
 */
public class OrderConfirmation extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)  {
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        String inclination = request.getParameter("shed");
        String roofMaterial = request.getParameter("roofMaterial");
        String shed = request.getParameter("shed");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Order order = new Order(length, width, inclination, roofMaterial, shed, name, address, zipcode, phoneNumber, email, width, email);
        if (!request.getParameter("shedLength").isEmpty())
            order.setShedLength(Integer.parseInt(request.getParameter("shedLength")));
        if (!request.getParameter("shedWidth").isEmpty())
            order.setShedLength(Integer.parseInt(request.getParameter("shedWidth")));
        if (!request.getParameter("comment").isEmpty());
            order.setComment(request.getParameter("comment"));

        
        request.setAttribute("order", order);
        try {
            LogicFacade.submitOrder(order);
        } catch (OrderException ex) {
            Logger.getLogger(OrderConfirmation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "orderconfirmationpage";
    }
    
    
}