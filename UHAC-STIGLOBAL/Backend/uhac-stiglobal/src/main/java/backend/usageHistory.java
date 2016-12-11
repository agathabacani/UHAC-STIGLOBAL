
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class usageHistory extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
       try{
           connectionString cn = new connectionString();
           cn.connectDB();
           DecimalFormat decimalForm = new DecimalFormat("#.##");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Usage History</title>");  
            out.print("<meta http-equiv=\"refresh\" content=\"5\" />");
            out.print("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/css/backend_Panel.css"+"'>");
            
            out.println("</head>");
            out.println("<body>");
           
            out.print("<div   class = 'headformmain'  >" 
           +"UnionBank - U.Go Monitoring System"
           
                                
            +"<div align='right' style='margin-right:220px;'>"
           
         
           
           +"</div></div>"  
            +"<form method='Post' action ='backend_Panel'>"
            +"<input type='hidden' name='userID' value=''>"  
            +"<input type='hidden' name='password' value=''>"         
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
            
             out.print("<div id='summary'>");
            out.print("<label>Usage History</lable>");
            
            out.print("<div class='tab_container' style='margin-top:65px;'> ");
                out.print("<table class='tablesorter' >  ");
                out.print("<thead>  ");
                out.print("<tr>  ");
                
                out.print("<th class='header'>Transaction Number</th>  ");
                out.print("<th class='header'>Amount</th>  ");
                out.print( "<th class='header'>Location</th>  "); 
                out.print( "<th class='header'>Date</th>  ");
                out.print( "<th class='header'>AccountID</th>  ");
                out.print("<th class='header'>Name</th>  ");
                 out.print("</tr>  ");
                out.print("</thead>  ");
                String select = "select usageID, Amount,Location, Date, usagehist.accountID as 'id'  ,Name  from accountdetails, usagehist where usagehist.accountID = accountdetails.accountID ";
                cn.stmt = cn.conn.prepareStatement(select);
                cn.rs = cn.stmt.executeQuery();
                
                out.print("<tbody>  ");
               while(cn.rs.next()){
               out.print("<tr>");
               out.print("<td>"+cn.rs.getString("usageID")+"</td>");
               out.print("<td>"+cn.rs.getDouble("Amount")+"</td>");
               out.print("<td>"+cn.rs.getString("Location")+"</td>");
               out.print("<td>"+cn.rs.getString("Date")+"</td>");
               out.print("<td>"+cn.rs.getString("id")+"</td>");
               out.print("<td>"+cn.rs.getString("name")+"</td>");
               out.print("</tr>");
               }
              
             out.print("</tbody>");
             out.print("</table>");
            
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
