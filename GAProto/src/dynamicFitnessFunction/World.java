package dynamicFitnessFunction;

import java.util.ArrayList;

/**
 * The world is a wrapper that contains every area (or sub-world) that will contain every species.
 * @author brandon
 *
 */
public class World implements Config
{
	Area area;
	
	ArrayList<Area> allAreas = new ArrayList<Area>();
	public World()
	{
		for(int i = 0; i<totalAreasInWorld; i++)
		{
			allAreas.add(new Area(areaSize));
		}
	}
	
	public String getComGenomeToString()
	{
		return area.getGenomeToString();
	}
	
	public ArrayList<Area> getAllAreas() {
		return allAreas;
	}
	public void setAllAreas(ArrayList<Area> allAreas) {
		this.allAreas = allAreas;
	}
	
	
}
