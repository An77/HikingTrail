import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;

public class HikingTrail {
	private static int a = 0;
	private static int index = 0;
	private static int operation = -1;
	private static String strBundle = "bundle";
	private static String strPath = "path";
	private static String tab2 = "\t\t";
	private static String tab4 = tab2 + tab2;
	private static String tab6 = tab4 + tab2;
	private static String tab8 = tab4 + tab4;
		
	// private static List<Bundle> myBundles = new ArrayList<Bundle>();
	private static Map<Integer, Bundle> bundles = new HashMap<Integer, Bundle>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (a != 1) {

			System.out.println("What do you want to do?\n");
			System.out.println("1: Create  new bundle\n" + "2. Update bundle \n" + "3. List bundles\n"
					+ "4. Delete bundle\n" + "5. Exit\n");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				operation = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("You did not choose a number. Make a new choice.");

			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Error message: " + e.getMessage());
			}
			System.out.print("Operation is ");

			if (operation == 1) {
				createBundle();

			} else if (operation == 2) {
				updateBundle();

			} else if (operation == 3) {
				listBundle();

			} else if (operation == 4) {
				deleteBundle();

			} else if (operation == 5) {
				a = 1;

			} else {
				System.out.println("Incorrect input. Make a new choice.");
			}
		}
	}

	/**
	 * Create bundle object and insert values
	 */
	public static void createBundle() {
		int finished = 1;
		System.out.println("createBundle");

		Bundle b = new Bundle(index); // send id in
		List<Path> pathList = new ArrayList<Path>();
		ContainerNII bundleNii = new ContainerNII();
		Path path;
		
		insertNIIValues(bundleNii, strBundle);
		b.setNii(bundleNii);

		while (finished == 1){
			path = new Path();
			createPath(path);
			pathList.add(path);
			finished = checkContinue("path");
		}		
		b.setPath(pathList);
		// Add bundle to map
		bundles.put(b.getId(), b);
		index++;
	}
	/**
	 * Operation to change existing bundle
	 */
	private static void updateBundle() {
		System.out.println("updateBundle");
		int id = -1;
		
		if (bundles.isEmpty()){
			System.out.println("There are no bundls added.");
			return;
		}
		
		System.out.println("Enter the id for the bundle you want to change: \n");
				
		id = insertInteger("id");
		if (!bundles.containsKey(id)){
			System.out.println("There are no bundles with id ");
			return;
		}
		
		Bundle b = bundles.get(id);
		printBundle(b);
		System.out.println("What do you want to change? ");
		
		int operation = 0;
		while ((operation = insertInteger(" ")) != 4){
			if (operation == 1){
				System.out.println("Change bundle ");
			} else if (operation == 2){
				System.out.println("Change path ");
			} else if (operation == 3){
				System.out.println("Change place ");
			} else {
				System.out.println(" 1. Change bundle information: \n 2. Change path information 3. Change place information\n 4. Cancel");
			}
		}
	}

	/**
	 * Operation to list existing bundles
	 */
	private static void listBundle() {
		System.out.println("listBundle");

		if (bundles.isEmpty()){
			System.out.println("There are no bundles");
			return;
		}
		
		Set<Integer> bundleIds = bundles.keySet();
		Iterator<Integer> itr = bundleIds.iterator();

		while (itr.hasNext()) {
			Integer bundleId = (Integer) itr.next();
			Bundle b = bundles.get(bundleId);

			printBundle(b);
		}
	}

	/**
	 * Operation to delete a bundle
	 */
	public static void deleteBundle() {
		System.out.println("deleteBundle");
		if (bundles.isEmpty()){
			System.out.println("There are no bundles");
			return;
		}
		
		System.out.println("Enter the id for the bundle you want to delete: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int id = Integer.parseInt(br.readLine());

			if (bundles.remove(id) == null){
				System.out.println("A bundle with id " + id + " did not exist");
			}

		} catch (NumberFormatException e) {
			System.out.println("A bundle with that id did not exist");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create path object and insert values
	 * 	
	 * @param path
	 */
	private static void createPath(Path path) {
		String tempStr = "";
		int finished = 1;
		int tempId = -1;
		List<GeoCoordinate> polyline = new ArrayList<GeoCoordinate>();
		List<Place> placeList = new ArrayList<Place>();
		ContainerNII pathNii = new ContainerNII();
		Place place;
		
		while (finished == 1){
			
			place = new Place();
			createPlace(place);
			placeList.add(place);
			finished = checkContinue("place");
		}
		path.setPlaces(placeList);
		
		if ((tempId = insertInteger("path id: ")) != -1)
			path.setId(tempId);	
		
		insertNIIValues(pathNii, "path");
		path.setNii(pathNii);
		
		if ((tempStr = insertString("path length: ")) != null)
			path.setLength(tempStr);
		
		insertPolyline(polyline, strPath);
		path.setPolyline(polyline);
		
		if ((tempStr = insertString("path duration: ")) != null)
			path.setDuration(tempStr);
		
	}
	/**
	 * Create place object and insert values
	 * 
	 * @param place
	 */
	private static void createPlace(Place place) {
		int tempInt = -1;
		ContainerNII placeNii = new ContainerNII();
		GeoCoordinate position = new GeoCoordinate();
		Media media = new Media();
		
		insertNIIValues(placeNii, "place");
		place.setNii(placeNii);
				
		if ((tempInt = insertInteger("place radius: ")) != -1)
				place.setRadius(tempInt);
		
		insertPosition(position);
		place.setPosition(position);
		
		insertMedia(media);
		place.setMedia(media);
	}	

	
	private static void insertPolyline(List<GeoCoordinate> polyline, String str) {
		GeoCoordinate position;
		
		int finished = 1;
		
		System.out.println("Insert first polyline point for " + str +": \n");
		
		while (finished == 1){
			position = new GeoCoordinate();
			insertPosition(position);
			polyline.add(position);
			finished = checkContinue("polyline");
		}
	}

	private static int checkContinue(String str) {
		int answer = 0;
		boolean finished = false;
		
		while (!finished){
			System.out.println("1. Add an other " + str);
			System.out.println("2. To continue ");
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				try {
					answer = Integer.parseInt(br.readLine());
					finished = true;
					
				} catch (NumberFormatException e) {
					finished = false;

				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		return answer;
	}


	private static void insertMedia(Media media) {
		String tempStr = "";

		if ((tempStr = insertString("media type: ")) != null)
			media.setType(tempStr);		

		if ((tempStr = insertString("media contents: ")) != null)
			media.setContents(tempStr);		

		if ((tempStr = insertString("media name: ")) != null)
			media.setName(tempStr);
		
		if ((tempStr = insertString("media image: ")) != null)
			media.setImage(tempStr);
	}

	private static void insertPosition(GeoCoordinate position) {
		
		position.setLat(insertCoordinate("Insert latitude: "));
		position.setLng(insertCoordinate("Insert longitude: "));
	}

	private static double insertCoordinate(String str) {
		double param = -1;
		boolean finished = false;
		
		System.out.println(str);
		
		while(!finished){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				param = Double.parseDouble(br.readLine());
				finished = true;
				
			} catch (NumberFormatException e) {
				finished = false;
				System.out.println("Insert " + str + "as a number" );
		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return param;
	}

	private static void insertNIIValues(ContainerNII nii, String obj) {
		String tempStr = "";

		if ((tempStr = insertString("name for " + obj + ": \n")) != null)
			nii.setName(tempStr);
		
		if ((tempStr = insertString("image for " + obj + ": \n")) != null)
			nii.setImage(tempStr);
		
		if ((tempStr = insertString("info for " + obj + ": \n")) != null)
			nii.setInfo(tempStr);				
	}

	private static String insertString(String param) {
		String strLine = "";
		
		System.out.println("Insert " + param + ": ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			if ((strLine = br.readLine()) == null){
				br = new BufferedReader(new InputStreamReader(System.in));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return strLine;
	}

	private static int insertInteger(String param) {
		int intLine = -1;
		boolean finished = false;
		
		System.out.println("Insert " + param + ": ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(!finished){
			try {
				intLine = Integer.parseInt(br.readLine());
				finished = true;
				
			} catch (NumberFormatException e) {
				System.out.println("You didn't type a number. Please try again.");
				finished = false;
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return intLine;
	}

	private static void printBundle(Bundle b) {		
		System.out.println("[\n\t{");
		System.out.println(tab2 + "id: " + b.getId());
		printContainerNii(b.getNii(), tab2);

		System.out.println(tab2 + "paths: [");
		System.out.println(tab2 + "\t{");

		List<Path> path = b.getPath();
		for (int i = 0; i <path.size(); i++) {
			printPath(path.get(i));
		}
		System.out.println("\t\t\t}\n" +"\t\t]\n" +"\t}" + "\n] \n");		
	}

	/**
	 * 
	 * @param path
	 */
	private static void printPath(Path path) {
		System.out.println(tab4 + "places: [");
		System.out.println(tab4 + "\t{");
		List<Place> places = path.getPlaces();
		
		if (!places.isEmpty()){
			for (int i = 0; i < places.size(); i++) {
				printPlace(places.get(i));
			}
		}
		System.out.println(tab4 + "\t}\n" + tab4 + "]");

		System.out.println(tab4 + "id: " + path.getId());
		checkString(tab4 + "name: ", path.getName()); 
		checkString(tab4 + "info: ", path.getInfo());
		checkString(tab4 + "length: ", path.getLength());
		
		System.out.println(tab4 + "polyline: [");
		
		
		for (int i = 0; i < path.getPolyline().size(); i++){
			System.out.println(tab4 + "\t{");
			printPosition(path.getPolyline().get(i), tab6);
			System.out.println(tab4 + "\t}");
		}
		System.out.println(tab4 + "]");
		
		checkString(tab4 + "duration: ", path.getDuration());
		checkString(tab4 + "image: ", path.getImage());
	}

	private static void printPlace(Place place) {
		printContainerNii(place.getNii(), tab6);
		System.out.println(tab6 + "radius: " + place.getRadius());
		System.out.println(tab6 + "position: ");
		System.out.println(tab6 + "\t{");
		printPosition(place.getPosition(), tab8);
		System.out.println(tab6 + "\t}");
		System.out.println(tab6 + "media: [");
		printMedia(place.getMedia());
		System.out.println(tab6 + "]");
	}

	private static void printContainerNii(ContainerNII nii, String str){
		checkString(str + "name: ", nii.getName());
		checkString(str + "info: ", nii.getInfo());
		checkString(str + "image: ", nii.getImage());
	}
	
	private static void printPosition(GeoCoordinate position, String str) {
		System.out.println(str + "lat: " + position.getLat());
		System.out.println(str + "lng: " + position.getLng());
	}

	private static void printMedia(Media media) {
		System.out.println(tab6 + "\t{");
		checkString(tab8 + "type: ", media.getType());
		checkString(tab8 + "contents: ", media.getContents());
		checkString(tab8 + "name: ", media.getName());
		checkString(tab8 + "image: ", media.getImage());
		System.out.println(tab6 + "\t");
	}

	/**
	 * 
	 * @param title
	 * @param value
	 */
	private static void checkString(String title, String value) {

		if (value == null) {
			System.out.println(title + "is empty");
		} else {
			System.out.println(title + value);
		}
	}
}
