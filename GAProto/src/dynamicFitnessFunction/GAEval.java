package dynamicFitnessFunction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GAEval implements Config
{

	
//	ArrayList<Member> genomePool;
//	ArrayList<Member> tops;
	
	ArrayList<ArrayList<Member>> entirePop;
//	ArrayList<ArrayList<Member>> 
	
	int finalCount = 0;
	
	public GAEval(ArrayList<ArrayList<Member>> entirePop)
	{
		this.entirePop = entirePop;
		
	}
	
//	public GAEval(ArrayList<Species<Member>> entirePop)
//	{
//		this.entirePop = entirePop;
//		
//	}
	
	/**
	 * This method allows attempted convergence of the opposing fitness functions by allowing each species to
	 * take turns at growing for the maximum amount of iterations allowed
	 */
	public void executeTakeTurns()
	{
		int totalTurnsTaken = 0;
		//loop to ensure that the optimization goes through a certain number of turns between each species before terminating.
		while(totalTurnsTaken < totalTurnsToTake)
		{	
//			System.out.println("tot " + totalTurnsToTake);
			//loop for iterating through all species in the population
			for(int n = 0; n<speciesPopulation; n++)
			{
				
//				System.out.println("specPop " + speciesPopulation);
				finalCount = 0;
				ArrayList<Member> singlePop = entirePop.get(n);
				
				//this loop goes until the current species either converges or iterates to a certain maximum
				//number of iterations (totalGenerations).
				while(singlePop.get(n).getCurrentScore() != singlePop.get(n).getSize() && finalCount < totalGenerations)
				{
//					System.out.println("finalCount " + finalCount);
					//get fitness score for all members of the current genome-pool (single species)
					for(int i = 0; i<singlePop.size(); i++)
					{
//						System.out.println("poolSize " + i);
						/**
						 * the following is a test loop that optimizes the population relative to 
						 * other species in the world
						 */
						//make a queue with random indexes so that fitness is done randomly to the world's population.
						int[] randQueue = new int[speciesPopulation];
						for(int m = 0; m<speciesPopulation; m++)
						{
							randQueue[m] = m;
						}
						randomSort(randQueue);
						
						for(int u = 0; u<speciesPopulation; u++)
						{
							
							if(n != randQueue[u])
							{
//								System.out.println(" num is " + randQueue[u]);
								fitness(singlePop.get(i), entirePop.get(randQueue[u]));
							}
						}
						
						
//						System.out.println(genomePool.get(i).getCurrentScore());
					}
					
					//Selection Sort the genomePool
					singlePop = sortGenomePool(singlePop);
					
					//if the terminal conditions have not been met, continue to manipulate and cross-breed the population.
					if(singlePop.get(n).getCurrentScore() == singlePop.get(n).getSize() && finalCount >= totalGenerations)
						break;
//					System.out.println("before breed");
					breed(singlePop);
//					System.out.println("after breed");
					
				}
				//print the final optimization information whether it converged or not.
				System.out.println("final optimization for species " + n + ":" + singlePop.get(0).getComGenomeToString() + " score: " 
						+ singlePop.get(0).getCurrentScore() + " iterations: " + finalCount + " turn: " + totalTurnsTaken);
				
				for(int i = 0; i<speciesPopulation; i++)
				{
					if(n != i)
						System.out.println("based on these:        species " + entirePop.get(i).get(0).getSpeciesType() + ":" + entirePop.get(i).get(0).getComGenomeToString());
				}
						
//						+ entirePop.get(n).get(0).getComGenomeToString() );
//				System.out.println("or       this:                  :" + entirePop.get(0).get(0).getComGenomeToString() );
			}
			totalTurnsTaken++;
		}//end while	
	}
	
	/**
	 * Fitness function used for multiple species in a population.
	 * @param m
	 * @param species
	 */
	public void fitness(Member m, ArrayList<Member> species)
	{
//		speciesA.get(0).getComGenome()[i];
	
//		System.out.println(species.get(0).getSpeciesType());
		
		Member tempCompared;

		tempCompared = species.get(0);
		
		int score = 0;
		m.setCurrentScore(score);

		if(fitnessCall.equals("popComp"))
		{
			//this fitness function desires a genome in opposition to the other (second) species;
			//this assumes only two species and is made for testing purposes only.
			//the opposing values are as follows: 0-5, 1-6, 2-7, 3-8, 4-9.
			for(int i = 0; i<m.getSize(); i++)
			{
				
				if(Math.abs(m.getComGenome()[i] - tempCompared.getComGenome()[i]) == 1)
				{
					score++;
				}
				
			}
		}
		else if(fitnessCall.equals("appearTest"))
		{
			for(int i = 0; i<m.getSize(); i++)
			{
				if(m.getComGenome()[i] == tempCompared.getComGenome()[i])
				{
					score++;
				}
				
			}
		}
		else
		{
			
		}
			
//		System.out.print(score + " :");
//		System.out.println(m.getComGenomeToString());
		m.setCurrentScore(score);
	}
	
	public void breed(ArrayList<Member> genomePool)
	{
		
		finalCount++;
//		System.out.println("Iter Again " + finalCount);
		
		ArrayList<Member> temp = (ArrayList<Member>) genomePool.clone();
		
		genomePool.clear();
		
		int elitistSize = (int) (generationRetention * populationSize);
//		System.out.println(" !! elite !! " + elitistSize);
		//want to keep best percentage of the population based on the variable "generationRetention".
		for(int i = 0; i<elitistSize; i++)
		{
			genomePool.add(temp.get(i));
		}
		
		for(int i = 0; i<temp.size()-elitistSize; i++)
		{
			Random rand = new Random();
			
			int index1 = rand.nextInt(elitistSize);
			int index2 = rand.nextInt(elitistSize);
			while(index1 == index2)
			{
				index2 = rand.nextInt(elitistSize);
			}
			
			//make new kids.
			genomePool.add(new Member(genomePool.get(index1), genomePool.get(index2)));

		}

	}
	
	/**
	 * Sort the genomePool (arraylist of all members of a species) in descending order.
	 * this allows for more easy elitist breeding selection.
	 * @param genomePool
	 * @return
	 */
	public ArrayList<Member> sortGenomePool(ArrayList<Member> genomePool)
	{
		Member mTemp;
		for(int i = 0; i<genomePool.size(); i++)
		{
			for(int k = 0; k<genomePool.size(); k++)
			{
				if(genomePool.get(i).getCurrentScore() > genomePool.get(k).getCurrentScore())
				{
					mTemp = genomePool.get(i);
					
					genomePool.set(i, genomePool.get(k));
					genomePool.set(k, mTemp);
				}
			}
		}
		return genomePool;
	}
	
	/**
	 * Randomizes an integer array.  Used to ensure a uniformly random species selection
	 * when choosing a species to run the fitness function off of.
	 * @param array
	 * @return
	 */
	public int[] randomSort(int[] array)
	{

	        int i;
	        int rand;
	        int temp;
	        Random random;

	        random = new Random(System.currentTimeMillis());
	        for (i = 0; i < array.length; i++) 
	        {
	            rand = (random.nextInt() & 0x7FFFFFFF) % array.length;
	            temp = array[i];
	            array[i] = array[rand];
	            array[rand] = temp;
	        }
	        
		return array;
	}
	

	public ArrayList<ArrayList<Member>> getEntirePop() 
	{
		return entirePop;
	}
	
}
