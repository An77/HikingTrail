
public class GeoCoordinate {
	private double lat;
	private double lng;

	public GeoCoordinate(){
		lat = lng = 0;
	}
	
	public void setLat(double newLat){
		lat = newLat;
	}
	
	public double getLat(){
		return lat;
	}
	public void setLng(double newLng){
		lng = newLng;
	}
	
	public double getLng(){
		return lng;
	}
}
