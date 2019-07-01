
package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 * 
 * You might:
 * Display all the airports in the world as features, and then display additional information about them when the user hovers over them.
 * Display only a subset of the airports in the world, based on some criteria.
 * Display airports, and when the user clicks on one, display the routes out of that airport.
 * Display all the airports that you have been to.
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	private boolean been = false;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	//New
	public void setBeen(boolean been) {
		this.been = been;
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		if(been == true)
			pg.fill(255,0,0);
		else
			pg.fill(0,255,255);
		pg.ellipse(x, y, 5, 5);
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		 // show rectangle with title
		
		// show routes
		String title = getName()+"  "+getCity()+"  "+ getCountry();
		pg.pushStyle();
		
		pg.rectMode(PConstants.CORNER);
		
		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y + 15, pg.textWidth(title) +6, 18, 5);
		
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0);
		pg.text(title, x + 3 , y +18);
		
		pg.popStyle();
		
	}
	
	public String getName() {
		return (String) getProperty("name");	
	}
	
	public String getCity() {
		return (String) getProperty("city");	
	}
	
	public String getCountry() {
		return (String) getProperty("country");			
	}
	
}
