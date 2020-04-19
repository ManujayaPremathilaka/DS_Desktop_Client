import java.rmi.Naming;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.model.FloorDetails;

public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerService serverService;
		ArrayList<FloorDetails> floorDetails = new ArrayList<FloorDetails>();
		
		try {
			serverService = (ServerService) Naming.lookup("rmi://localhost/ABC");
			
			String output = serverService.readRESTAPI();
			
			JSONObject jsonObject = new JSONObject(output);
          
			JSONArray array= jsonObject.getJSONArray("data");
          
			for(int i = 0; i < array.length(); i++) {
      	
				FloorDetails details = new FloorDetails();
				details.setId(array.getJSONObject(i).getInt("Id"));
				details.setStatus(array.getJSONObject(i).getString("status"));
				details.setFloorNo(array.getJSONObject(i).getInt("floorNo"));
				details.setRoomNo(array.getJSONObject(i).getString("roomNo"));
				details.setSmokeLevel(array.getJSONObject(i).getInt("smokeLevel"));
				details.setCo2Level(array.getJSONObject(i).getInt("Co2Level"));
          
				floorDetails.add(details);
			}
			
			for(int i = 0; i < floorDetails.size(); i++) {
				
				System.out.println(floorDetails.get(i).getId());
			}
			
			serverService.addSensor(3, "A3");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	

}
