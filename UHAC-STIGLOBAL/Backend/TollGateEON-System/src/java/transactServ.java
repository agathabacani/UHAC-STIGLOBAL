
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import okhttp3.*;

/**
 *
 * @author Jace Christian
 */
public class transactServ extends HttpServlet {
    
    
   
    static  String disp ="none";
    static  String disp2 ="none";
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        accNum=request.getParameter("accNum");
        transact(accNum);
        PrintWriter out = response.getWriter();
       try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
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
            
            
          
            
            out.print("<div id='bg'>");
            out.print("<img src='img/bg.png' style=\"width:1350px;height:769px;\">");
            out.print("</div>");
           out.println(available_balance);
           
       
        
            response.sendRedirect("Main");
            
            }
            catch(Exception e){
            out.print(e);
            }
         
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
         connectionString cn = new connectionString();
        cn.connectDB();
         if(account_no.equals("101049990806")){
        account_no = "101930186016";
        }
        else if(account_no.equals("101050943579")){
        account_no="101065294818";
        }
        String insert = "update accountdetails set  ugoBalance= "+available_balance+"   where accountID = '"+account_no+"'";
        cn.stmt = cn.conn.prepareStatement(insert);
        cn.stmt.executeUpdate();
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf = 
        new java.text.SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

        String currentTime = sdf.format(dt);
        String insert2 = "insert into usagehist (Amount,Location,Date,accountID) values  (150.0,'Bacoor Gate','"+currentTime+"','"+account_no+"')";
        cn.stmt = cn.conn.prepareStatement(insert2);
        cn.stmt.executeUpdate();
        
        
        }
        catch(Exception e){
        e.printStackTrace();
        }
      
    }
    void transact(String accNum){
       
        try{
        if(accNum.equals("101930186016")){
        accNum = "101049990806";
        }
        else if(accNum.equals("101065294818")){
        accNum="101050943579";
        }
        long chNum = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        long transactID = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        mediaType = MediaType.parse("application/json");
        body = RequestBody.create(mediaType, "{\"channel_id\":\""+chNum+"\",\"transaction_id\":\""+transactID+"\",\"source_account\":\""+accNum+"\",\"source_currency\":\"php\",\"target_account\":\"101914855613\",\"target_currency\":\"php\",\"amount\":150.00}");
        req = new Request.Builder()
          .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/transfer")
          .post(body)
          .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
          .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
          .addHeader("content-type", "application/json")
          .addHeader("accept", "application/json")
          .build();

        res= client.newCall(req).execute();
        getInfo(res.body().string());
        if(status.equals("S")){
               req1 = new Request.Builder()
                .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/getAccount?account_no="+accNum+"")
                .get()
                .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                       
                .build();
               
               res1 = client.newCall(req1).execute();
               getInfoBal(res1.body().string());
               disp="block";
               disp2="none";
            }
            else{
              disp="none";
               disp2="block";
               
            }
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
