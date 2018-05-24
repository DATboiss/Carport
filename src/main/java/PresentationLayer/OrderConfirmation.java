/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LineItem;
import FunctionLayer.LogicFacade;
import FunctionLayer.Material;
import FunctionLayer.MaterialException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import FunctionLayer.SubmitOrderException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author s_ele
 */
public class OrderConfirmation extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException, MaterialException, SubmitOrderException
    {
        // Carport details
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        String inclination = request.getParameter("inclination");
        int angle = Integer.parseInt(request.getParameter("angle"));
        String roofMaterial = request.getParameter("roofMaterial");
        String shed = request.getParameter("shed");
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));

        // SubmitOrderExceptionCheck
        checkForSubmitOrderException(length, width, inclination, angle, shed, shedLength, shedWidth);
        
        // customer details
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        
        // Order creation
        Order order = new Order(length, width, inclination, angle, roofMaterial, shed, name, address, zipcode, phoneNumber, email);
        order.setShedLength(shedLength);
        order.setShedWidth(shedWidth);

        if (request.getParameter("comment") != null);
        {
            order.setComment(request.getParameter("comment"));
        }
        List<LineItem> BoM = LogicFacade.createBoM(order);
        for (LineItem lineItem : BoM)
        {
            Material material = LogicFacade.getMaterial(lineItem.getIdmaterial());
            lineItem.setPrice(material.getMSRP());
        }
        order.setPrice(LogicFacade.calcPrice(BoM));
        
        // Submit order to database
        LogicFacade.submitOrder(order);

        
        // return 
        request.setAttribute("order", order);
        return "orderconfirmationpage";
    }
    
    private static void checkForSubmitOrderException(int length, int width, String inclination, int angle, String shed, int shedLength, int shedWidth) throws SubmitOrderException 
    {
        if ((shed.equals("shed") && ((shedLength == 0) || (shedWidth == 0))))
        {
            throw new SubmitOrderException("Du valgte med skur, men har ikke indtastet længde og/eller bredde på skuret");
        }
        if ((shed.equals("noShed") && ((shedLength > 0) || (shedWidth > 0))))
        {
            throw new SubmitOrderException("Du valgte uden skur, men har indtastet længde og/eller bredde større end 0 på skuret");
        }
        if (shedLength >= length)
        {
            throw new SubmitOrderException("Længden af dit skur kan ikke være samme længde eller længere end din carport");
        }
        if (shedWidth >= width)
        {
            throw new SubmitOrderException("Bredden af dit skur kan ikke være samme bredde eller bredere end din carport");
        }
        if (inclination.equals("Med rejsning") && angle == 0)
        {
            throw new SubmitOrderException("Du har valgt med rejsning, men har ikke valgt en vinkel større end 0 grader");
        }
        if (inclination.equals("Fladt tag") && angle > 0)
        {
            throw new SubmitOrderException("Du har valgt med fladt tag, men har valgt en vinkel større end 0 grader");
        }
    }

}// CLASS
