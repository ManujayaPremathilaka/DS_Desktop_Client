import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ReadRESTAPI extends UnicastRemoteObject implements ServerService{

	protected ReadRESTAPI() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String readRESTAPI() {
		String output = null;
		
		try {

            URL url = new URL("http://localhost:8000/api/sensors");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            output = br.readLine();
            

            System.out.println(output);

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
		
		return output;
	}
	
	public void addSensor(int floorNo, String roomNo) {
		
		String POST_PARAMS = "{\n" + "\"floorNo\": "+floorNo+",\r\n" +
		        "    \"roomNo\": \""+roomNo+"\",\r\n" +
		        "    \"smokeLevel\": 0,\r\n" +
		        "    \"Co2Level\": 0,\r\n" +
		        "	\"status\": \"Inactive\"" + "\n}";
		
		System.out.println(POST_PARAMS);
		//String jsonString = "{\"floorNo\":"+floorNo+",\"roomNo\":"+roomNo+", \"smokeLevel\":\"0\", \"Co2Level\":\"0\", \"status\":\"Inactive\"}";
		
		try {
			URL url = new URL("http://localhost:8000/api/sensors");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json;utf-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(POST_PARAMS.getBytes());
			outputStream.flush();
			outputStream.close();
			
			int responseCode = conn.getResponseCode();
		    System.out.println("POST Response Code :  " + responseCode);
		    System.out.println("POST Response Message : " + conn.getResponseMessage());
		    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		            conn.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in .readLine()) != null) {
		            response.append(inputLine);
		        } in .close();
		        // print result
		        System.out.println(response.toString());
		    } else {
		        System.out.println("POST NOT WORKED");
		    }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//your url i.e fetch data from .
		
	}
}
