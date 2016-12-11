
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class backend_Panel extends HttpServlet {

   OkHttpClient client = new OkHttpClient();
    Response res;
    Request req;
   
    
    Response res1;
    Request req1;
    MediaType mediaType;
    RequestBody body;
    String transactionId ;
    String status ;
    String confirmationNumber ;
    String account_no;
    String currency;
    String account_name;
    String statusAcc;
    String available_balance;
    String current_balance ;
     String accNum;
     String transinfo;
   connectionString cn = new connectionString();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        PrintWriter out = response.getWriter();
    
           try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.print("<meta http-equiv=\"refresh\" content=\"5\" />");
            out.println("<title>Admin Panel</title>");  
            out.print("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/css/backend_Panel.css"+"'>");
            
            out.println("</head>");
            out.println("<body>");
           
            out.print("<div   class = 'headformmain'  >" 
            +"UnionBank - U.Go Monitoring System"
           
                                
            +"<div align='right' style='margin-right:220px;'>"
           
         
           
           +"</div></div>"  
            +"<form method='Post' action ='backend_Panel'>"
                
            +"<div id='headBar'>"
                    + "<img src='img/admin.png' style=\"width:17px;height:17px;\">&nbsp;&nbsp;"
                    + "<input type='submit' value='Admin Panel (Dashboard)' id='adminpanel'></div>"
            +"</form>");
            
            
            out.print("<div class='navigation'>"
            +"<div style='border-bottom:thin solid gray ;padding:13px;padding-left:20px;font-weight: bold;'>MAINTENANCE</div><br>"
            + "<br><br><h4 style='margin-top:10px; margin-left:25px;'>Accounts" 
            +"<ul>"
            + "<li><a href='Summary'><img src='img/summary.png' style=\"width:17px;height:17px;\">&nbsp;&nbsp;Summary&nbsp;&nbsp;</a></li> "   
            + "<li><a href='savingHistory'><img src='img/savings.png' style=\"width:17px;height:17px;\">&nbsp;&nbsp;Savings&nbsp;&nbsp;</a></li> "
            + "<li><a href='usageHistory'><img src='img/usages.png' style=\"width:17px;height:15px;\">&nbsp;&nbsp;Usages&nbsp;&nbsp;</a>"
            + "<li><a href='transferHistory'><img src='img/transfer.png' style=\"width:17px;height:15px;\">&nbsp;&nbsp;Transfer&nbsp;&nbsp;</a>"
            
                    +" </li> </ul></h4>"
                  + "<br><br><h4 style='margin-top:10px; margin-left:25px;'>Settings" 
            +"<ul>"
            + "<li><a href='#'><img src='img/report.png' style=\"width:17px;height:17px;\">&nbsp;&nbsp;Reports&nbsp;&nbsp;</a></li> "   
            + "<li><a href='#'><img src='img/password.png' style=\"width:17px;height:17px;\">&nbsp;&nbsp;Password&nbsp;&nbsp;</a></li> "
            + "<li><a href='#'><img src='img/adminlogout.png' style=\"width:17px;height:15px;\">&nbsp;&nbsp;Logout&nbsp;&nbsp;</a>"
           
                    +" </li> </ul></h4>"
                    + "</div>");
            
            
            out.print("<div id='bg'>");
            out.print("<img src='img/bg.png' style=\"width:1350px;height:769px;\">");
            out.print("</div>");
           
            
            
            }
            catch(Exception e){
            out.print(e);
            }
            out.println("</body>");
            out.println("</html>");
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
