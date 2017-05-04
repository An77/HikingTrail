
public class Media {
	private String type;
	private String contents;
	private String name;
	private String image;

	public Media(){
		type = contents = name = image = "";
	}
	public void setName(String bundleName){
		name = bundleName;
	}
	
	public String getName(){
		return name;
	}

	public void setImage(String bundleImage){
		image = bundleImage;
	}
	
	public String getImage(){
		return image;
	}

	public void setContents(String mediaContents){
		contents = mediaContents;
	}
	
	public String getContents(){
		return contents;
	}

	public void setType(String mediaType){
		type = mediaType;
	}
	
	public String getType(){
		return type;
	}
}
