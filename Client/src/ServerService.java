import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.model.FloorDetails;

public interface ServerService extends Remote{

	public String readRESTAPI() throws RemoteException;
	public void addSensor(int floorNo, String roomNo) throws RemoteException;
}
