import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerService extends Remote{

	public String readRESTAPI() throws RemoteException;
	public void addSensor(int floorNo, String roomNo) throws RemoteException;
}
