
public class ContainerNII {
	private String name;
	private String info;
	private String image;

	public ContainerNII(){
		name = image = info = "";
	}

	public void setName(String niiName){
		name = niiName;
	}
	
	public String getName(){
		return name;
	}

	public void setImage(String niiImage){
		image = niiImage;
	}
	
	public String getImage(){
		return image;
	}
	public void setInfo(String niiInfo){
		info = niiInfo;
	}
	
	public String getInfo(){
		return info;
	}
}
