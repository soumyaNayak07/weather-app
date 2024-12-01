package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.DisplayMode;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		
//		API setup
		
		String ApiKey="b4de6d96f0421c7433cbc9a8f194a5ea";
		
		String City=request.getParameter("input");
		
		if (City == null || City.isEmpty()) {
		    response.getWriter().println("Error: City input is missing.");
		    
		    return;
		}
	
		
		String ApiUrl="https://api.openweathermap.org/data/2.5/weather?q="+City+"&appid="+ApiKey;
		
		URL url=new URL(ApiUrl);
		
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        InputStream inputstream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputstream);
        
        StringBuilder responseContent=new StringBuilder();
        
        Scanner sc =new Scanner(reader);
        
        
        while (sc.hasNext()) {
			responseContent.append(sc.nextLine());
		}
        
        sc.close();
        
        Gson gson=new Gson();
        JsonObject jsonobject=gson.fromJson(responseContent.toString(), JsonObject.class);
        
        
        
        
        
      //Date & Time
        long dateTimestamp = jsonobject.get("dt").getAsLong() * 1000;
        String time=new Time(dateTimestamp).toString();
        String date = new Date(dateTimestamp).toString();
        
        
        //Temperature
        double temperatureKelvin = jsonobject.getAsJsonObject("main").get("temp").getAsDouble();
        int temperatureCelsius = (int) (temperatureKelvin - 273.15);
        
       
        //Humidity
        int humidity = jsonobject.getAsJsonObject("main").get("humidity").getAsInt();
       
        
        //Wind Speed
        double windSpeed = jsonobject.getAsJsonObject("wind").get("speed").getAsDouble();
        
        //Weather Condition
        String weatherCondition = jsonobject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
        
        
        request.setAttribute("city", City.toUpperCase());
        request.setAttribute("date", date);
        request.setAttribute("time", time);
        request.setAttribute("temp", temperatureCelsius);
        request.setAttribute("humi", humidity);
        request.setAttribute("wind", windSpeed);
        request.setAttribute("weather", weatherCondition);
        
        connection.disconnect();
        
        request.getRequestDispatcher("FinalPage.jsp").forward(request, response);
        
        
        
        
		
		
	}

}
