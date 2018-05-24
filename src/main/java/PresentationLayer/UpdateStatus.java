package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wtfak
 */
public class UpdateStatus extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException
    {

        String status = request.getParameter("status");
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        Order order = LogicFacade.getOrder(orderId);
        if (order != null)
        {
            order.setStatus(status);
            LogicFacade.updateOrderStatus(order);
            return "browseorders";
        } else
            throw new OrderException("Der gik noget galt. Prøv igen senere, ellers kontakt support");
    }

}
