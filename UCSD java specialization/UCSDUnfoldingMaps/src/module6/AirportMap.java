package module6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	
	//TODO parameters about mouseMOVE and mouseCLICK
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;
	
	public void setup() {
		// setting up PAppler
		size(800,600, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 50, 50, 750, 550);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
			m.setId(feature.getId());
			m.setRadius(5);
			
			//just show the airport in American and Japan
			System.out.println(m.getCountry());
			//if(m.getCountry().equals("\"United States\"")) 
			airportList.add(m);
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker lineMarker = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(lineMarker.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			// routeList contains the <SimpleLinesMarker>
			routeList.add(lineMarker);
		}
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		//All lines is now invisible.
		map.addMarkers(routeList);
		for(Marker m: routeList) {	
			m.setHidden(true);
		}
		
		map.addMarkers(airportList);
		
		//TODO NEW
		List<PointFeature> newfeatures = ParseFeed.parseAirports(this, "airportsHaveBeen.dat");
		List<Marker> airportHaveBeenList = new ArrayList<Marker>();
		
		// create markers from features
		for(PointFeature feature : newfeatures) {
			AirportMarker m = new AirportMarker(feature);
			m.setBeen(true);
			airportHaveBeenList.add(m);
		}
		
		map.addMarkers(airportHaveBeenList);
	}
	
	public void draw() {
		background(0);
		map.draw();
		
	}
	
	//TODO NEW
	@Override
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(airportList);
		//loop();
	}
	
	// If there is a marker selected 
	private void selectMarkerIfHover(List<Marker> markers)
	{
		// Abort if there's already a marker selected
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			CommonMarker marker = (CommonMarker)m;
			if (marker.isInside(map,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}

	
	/** The event handler for mouse clicks
	 * It will display an earthquake and its threat circle of cities
	 * Or if a city is clicked, it will display all the earthquakes 
	 * where the city is in the threat circle
	 */
	@Override
	public void mouseClicked()
	{
		if (lastClicked != null) {
			hideLineMarkers();
			lastClicked = null;
		}
		else if (lastClicked == null) 
		{
			checkRoutesForClick();
		}
	}
	
	// Helper method that will show the destination of this airport, hide all others.
		private void checkRoutesForClick()
		{	
			System.out.println("clicked");
			if (lastClicked != null) return;
			// Loop over the earthquake markers to see if one of them is selected
			
			for (Marker m : airportList) {
				AirportMarker airport = (AirportMarker)m;
				
				if (airport.isInside(map, mouseX, mouseY)) {
					lastClicked = airport;
					// check all the route, display the relating routes.
					
					for (Marker route : routeList) {
						//CAUTION! be careful of the type of data!!!
						if (airport.getId().equals((String)route.getProperty("source"))) 
							route.setHidden(false);
						else
							route.setHidden(true);
					}
					return;
				}
			}
		}
		
		// loop over and hide all line markers
		private void hideLineMarkers() {
			for(Marker marker : routeList) {
				marker.setHidden(true);
			}
		}
}
