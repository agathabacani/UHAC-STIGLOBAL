
package backend;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Summary extends HttpServlet {

    
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
     connectionString cn;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        transact();
       try{
           cn = new connectionString();
           cn.connectDB();
           DecimalFormat decimalForm = new DecimalFormat("#.##");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.print("<meta http-equiv=\"refresh\" content=\"5\" />");
            out.println("<title>Accounts Summary</title>");  
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
            out.print("<label>Summary record of users</lable>");
            
            out.print("<div class='tab_container' style='margin-top:65px;'> ");
                out.print("<table class='tablesorter' >  ");
                out.print("<thead>  ");
                out.print("<tr>  ");
                
                out.print("<th class='header'>Account ID</th>  ");
                out.print("<th class='header'>Name</th>  ");
                out.print("<th class='header'>UB Account Balance</th>  ");
                out.print( "<th class='header'>Ugo Account Balance</th>  ");  
                 out.print("</tr>  ");
                out.print("</thead>  ");
                String select = "select accountID, Name, Balance,UgoBalance from accountdetails";
                cn.stmt = cn.conn.prepareStatement(select);
                cn.rs = cn.stmt.executeQuery();
                
                out.print("<tbody>  ");
               while(cn.rs.next()){
               out.print("<tr>");
               out.print("<td>"+cn.rs.getString("accountID")+"</td>");
               out.print("<td>"+cn.rs.getString("Name")+"</td>");
               out.print("<td>"+cn.rs.getDouble("Balance")+"</td>");
               out.print("<td>"+cn.rs.getDouble("UgoBalance")+"</td>");
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
    
    
     void getInfo(String info)
    {   
        transinfo=info;
        info = info.replace("]","").replace("{","").replace("}","").replace("\"","");
        String[] collection_info = info.split(",");

        for(int ctr = 0; ctr < collection_info.length-1; ctr++)
        {
            String[] temp = collection_info[ctr].split(":");
            collection_info[ctr] = temp[1];
        }

        transactionId = collection_info[1];
        status = collection_info[2];
        confirmationNumber = collection_info[3];
    }
    public void getInfoBal(String info)
    {
        transinfo=info;
        info = info.replace("[","").replace("]","").replace("{","").replace("}","").replace("\"","");
        String[] collection_info = info.split(",");

        for(int ctr = 0; ctr < collection_info.length; ctr++)
        {
            String[] temp = collection_info[ctr].split(":");
            collection_info[ctr] = temp[1];
        }

        account_no = collection_info[0];
        currency = collection_info[1];
        account_name = collection_info[2];
        statusAcc = collection_info[3];
        available_balance = collection_info[4];
        current_balance = collection_info[5];
        try{
         
       
        
        }
        catch(Exception e){
        e.printStackTrace();
        }
      
    }
    void transact(){
       
        try{
      
            
        
        req = new Request.Builder()
                .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/getAccount?account_no=101049990806")
                .get()
                .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                       
                .build();
                     
        res= client.newCall(req).execute();
        getInfoBal(res.body().string());
        String insert = "update accountdetails set  UgoBalance= "+available_balance+" where accountID = '101930186016'";
        
        cn.connectDB();
        cn.stmt = cn.conn.prepareStatement(insert);
        cn.stmt.executeUpdate();
        
        
       
            
            req = new Request.Builder()
                .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/getAccount?account_no=101050943579")
                .get()
                .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                       
                .build();
                     
            res= client.newCall(req).execute();
            getInfoBal(res.body().string());
            insert = "update accountdetails set  UgoBalance= "+available_balance+" where accountID = '101065294818'";
            connectionString cn = new connectionString();
            cn.connectDB();
            cn.stmt = cn.conn.prepareStatement(insert);
            cn.stmt.executeUpdate();
        
        
               req1 = new Request.Builder()
                .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/getAccount?account_no=101930186016")
                .get()
                .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                       
                .build();
               
               res1 = client.newCall(req1).execute();
               getInfoBal(res1.body().string());
             
           String insert2 = "update accountdetails set  Balance= "+available_balance+" where accountID = '101930186016'";
            cn.stmt = cn.conn.prepareStatement(insert2);
            cn.stmt.executeUpdate();
            
             req1 = new Request.Builder()
                .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/getAccount?account_no=101065294818")
                .get()
                .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                       
                .build();
               
               res1 = client.newCall(req1).execute();
               getInfoBal(res1.body().string());
             
           insert2 = "update accountdetails set  Balance= "+available_balance+" where accountID = '101065294818'";
            cn.stmt = cn.conn.prepareStatement(insert2);
            cn.stmt.executeUpdate();
        }
        catch(Exception e){
             e.printStackTrace();
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
