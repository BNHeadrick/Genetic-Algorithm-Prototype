package dynamicFitnessFunction;

import java.util.ArrayList;

public class RunEvoBoids implements Config
{
	private GAEval eval;
	private World world;
	public RunEvoBoids()
	{
		
		
		ArrayList<ArrayList<Member>> allSpecies = new ArrayList<ArrayList<Member>>();
		for(int i = 0; i<speciesPopulation; i++)
		{
			ArrayList<Member> population = new ArrayList<Member>();
			
			//fill up population with random attributes.
			for(int k = 0; k<populationSize; k++)
			{
				population.add(new Member(memberSize, mutationRate, crossoverRate, i));
			}
			
	//		System.out.println("added!");
			allSpecies.add(population);
			
	//		world.add(new ArrayList<Member>().add(new Member(memberSize, mutationRate, crossoverRate)));
		}
		
		world = new World();
		for(int i = 0; i<world.getAllAreas().size(); i++)
		{
			System.out.println(world.getAllAreas().get(i).getGenomeToString());
		}
		
	//	for(int i = 0; i<world.size(); i++)
	//	{
	//		System.out.println(world.get(i));
	//	}
		
	//	e = new Eval(population);
		
		
		
		
		eval = new GAEval(allSpecies);
		
		
	}
	
	
	public void runGA()
	{
		eval.executeTakeTurns();
	}
	
	//hacky; fix later.
	public GAEval getGAEval()
	{
		return eval;
		
	}

}
