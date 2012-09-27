package dynamicFitnessFunction;

import java.util.Random;


public class Area implements Config
{
	Random rand = new Random();
	
	int[] areaAttributes;
	
	public Area(int length)
	{
		areaAttributes = new int[length];
		for(int i = 0; i<areaAttributes.length; i++)
		{
			areaAttributes[i] = rand.nextInt(areaRange[i]);
//			//the following ensures that only 3 combinations of the first two values are ever made. (not 00)
//			if(i == 1)
//				if(areaAttributes[0] + areaAttributes[1] == 0)
//				{
//					i = -1;
//				}
		}
	}

	public String getGenomeToString() 
	{
		String comGene = "";
		for(int i = 0; i<areaAttributes.length; i++)
		{
			comGene = comGene + areaAttributes[i];
		}
		return comGene;
	}
}
