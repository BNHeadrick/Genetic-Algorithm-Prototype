package dynamicFitnessFunction;  

public interface Config 
{

	int totalAreasInWorld = 4;				//value indicates the number of areas to populate the world with; this should probably be correlated with the number of species in the world. (speciesPopulation)
	
	int genomeRangeConst = 2;				//the range of the numbers of each gene in a genome; example: a value of 2 makes each gene binary (0 or 1).
	int[] genomeRange = {3,6,6,3,6,6,3,6,6,2,3,6,6,3,6,6,3,6,6,9,9,9,9,9,9,9,9,9,9,9};//R,R,R,G,G,G,B,B,B,secondColorBoolean,R,R,R,G,G,G,B,B,B,vertx1,verty1,vertx2,verty2,vertx3,verty3,vertx4,verty4,vertx5,verty5,size
	
//	int[] genomeRange = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
	
	int areaSize = genomeRange.length;						//the length of the area genome; in other words, the number of bits, or strings, in each area.
	
	int areaRangeConst = 2;						//the range of the numbers of each gene in the genome for an Area in the world.
	int[] areaRange = genomeRange;
//	int[] areaRange = {3,6,6,3,6,6,2,3,6,6,3,6,6,7,9};
	
//	int[] areaRange = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
	
	/********************************/
	int totalTurnsToTake = 4;
	
	int speciesPopulation = 20;				//the number of species that will exist in the world; default 10
	
	//the floor of this value is relative to the value of generationRetention; for example, if generationRetention is .05, this value needs to be at least 20
	//the ceiling of this value is relative to the value of species population; for example, if populationSize is 20, then species population needs to be at least 20.
	int populationSize = 20;				//the amount of creatures in a species between breeds.
	
	int memberSize = areaSize;				//the number of bits (or strings) in each member; default 20
	int mutationRate = 1;					//percent; the rate at which individual bits (or strings) will mutate; default 1
	int crossoverRate = 70;					//percent; the rate at which individual bits (or strings) will crossover (breed); default 70
	double generationRetention = .15;		//percent; the percentage of the total population to retain for breeding; default .05
	int totalGenerations = 1;				//the maximum number of generations the application will run for; default 150
	
//	String fitnessCall = "popComp";			//this value is used to select which fitness function to use. using binary only genome
	String fitnessCall = "appearTest";		//this is for testing using GAs for a more complicated genome; assuming non-binary numeric chars in genome.
	
	
	
}
