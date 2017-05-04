
public class Place {
	private ContainerNII nii; 
	private String length;
	private int radius;
	private GeoCoordinate position; 
	private Media media; // additional media

	public Place(){
		length = "";
		radius = 0;
	}
	
	public void setNii(ContainerNII placeNii){
		nii = placeNii;
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

	public void setRadius(int placeRadius ){
		radius = placeRadius;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setPosition(GeoCoordinate placePosition){
		position = placePosition;
	}
	
	public GeoCoordinate getPosition(){
		return position;
	}

	public void setMedia(Media placeMedia ){
		media = placeMedia;
	}
	
	public Media getMedia(){
		return media;
	}
}
