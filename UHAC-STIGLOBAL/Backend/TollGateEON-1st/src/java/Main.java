
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SONY
 */
public class Main extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Toll Gate</title>");  
            out.print("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/css/backend_Panel.css"+"'>");
            
            out.println("</head>");
            out.println("<body>");
           
            out.print("<div   class = 'headformmain'  >" 
            +"Toll Gate System"
           
                                
            +"<div align='right' style='margin-right:220px;'>"
           
         
           
           +"</div></div>"  
            +"<form method='Post' action ='backend_Panel'>"
                
            +"<div id='headBar'>"
                  
                    + "<input type='text' value='TG-01 (Manual Mode)' id='adminpanel'></div>"
            +"</form>");
            
            
          
            
            out.print("<div id='bg'>");
            out.print("<img src='img/bg.png' style=\"width:1350px;height:769px;\">");
            out.print("</div>");
            out.print("        <div></div>\n" +
"        <form Method =\"POST\" action =\"transactServ\">\n" +
"            <label id='idacct'>Account ID</label><input type=\"text\" name=\"accNum\" id='rfid'/>\n" +
"            <button type=\"submit\" id ='rfidbtn'>submit</button>\n" +
"        </form>\n");
            out.print("<label style='display:"+transactServ.disp+";font-size:40px;color:Green;top:360px;left:440px;position:absolute;'>Verification Successful! Gate Open!</label>");
            out.print("<label style='display:"+transactServ.disp2+"; font-size:40px;color:Red;top:360px;left:470px;position:absolute;'>Not enough funds!</label>");
            out.println("</body>");
            out.println("</html>");
        
            }
            catch(Exception e){
            out.print(e);
            }

            
    }
    
            



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
