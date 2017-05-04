import java.util.*;

public class Path {
	private int id;
	private String length;
	private List<GeoCoordinate> polyline;
	private String duration;
	private List<Place> places;
	private ContainerNII nii;
	
	public Path(){
		id = -1;
		length = duration = "";
		polyline = new ArrayList<GeoCoordinate>();
		places = new ArrayList<Place>();
	}
	
	public void setId(int pathId){
		id = pathId;
	}

	public int getId(){
		return id;
	}
	
	public void setNii(ContainerNII pathNii){
		nii = pathNii;
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

	public void setLength(String placeLength){
		length = placeLength;
	}
	
	public String getLength(){
		return length;
	}

	public void setPolyline(List<GeoCoordinate> pathPolyline){
		polyline = pathPolyline;
	}
	
	public List<GeoCoordinate> getPolyline(){
		return polyline;
	}
	
	public void setDuration(String pathDuration){
		duration = pathDuration;
	}
	
	public String getDuration(){
		return duration;
	}
	
	public void setPlaces(List<Place> pathPlaces){
		places = pathPlaces;
	}
	
	public List<Place> getPlaces(){
		return places;
	}
}
