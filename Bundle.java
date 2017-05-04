import java.util.ArrayList;
import java.util.*;

public class Bundle {
	private int id;
	private List<Path> paths;
	private ContainerNII nii;

	public Bundle(int bundleId){
		id = bundleId;
		paths = new ArrayList<Path>();		
	}
	
	public int getId(){
		return id;
	}
	
	public void setNii(ContainerNII bundleNii){
		nii = bundleNii;
	}
	
	public ContainerNII getNii(){
		return nii;
	}
	
	public void setName(String bundleName){
		nii.setName(bundleName);
	}
	
	public String getName(){
		return nii.getName();
	}

	public void setImage(String bundleImage){
		nii.setImage(bundleImage);
	}
	
	public String getImage(){
		return nii.getImage();
	}
	public void setInfo(String bundleInfo){
		nii.setInfo(bundleInfo);
	}
	
	public String getInfo(){
		return nii.getInfo();
	}
	public void setPath(List<Path> bundlePath ){
		paths = bundlePath;
	}
	
	public List<Path> getPath(){
		return paths;
	}
}