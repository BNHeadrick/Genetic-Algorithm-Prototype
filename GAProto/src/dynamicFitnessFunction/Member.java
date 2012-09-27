package dynamicFitnessFunction;
import java.util.ArrayList;
import java.util.Random;


public class Member implements Config
{
	int mSize;
	int mutRate;
	int crossRate;
	int speciesType;
	
	Genome genome;
	public int currentScore;
	
	Random rand = new Random();
	
	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	
	public Member(int mSize, int mutRate, int crossRate, int speciesType)
	{
		this.mSize = mSize;
		this.mutRate = mutRate;			//percent
		this.crossRate = crossRate;		//percent
		this.speciesType = speciesType;	//what species this current member belongs to.
		
		genome = new Genome(mSize);
	}
	
	/**
	 * This member will be generated from a previous member.
	 * Essentially, the member being passed on is the parent to this new "child" member.
	 * @param m
	 */
	public Member(Member m1, Member m2)
	{
//		System.out.println("inBreed");
		//populate the new member with either parent's mutation, crossover, and size attributes
		this.mSize = m1.getmSize();
		this.mutRate = m1.getMutRate();
		this.crossRate = m1.getCrossRate();
		this.speciesType = m1.getSpeciesType();
		
		int[] gA = new int[mSize];
		int[] gB = new int[mSize];
		
		//first, get the 2 substrings to cross over to make the new member.
		int xOverIndex = rand.nextInt(mSize);
		
		for(int i = 0; i<xOverIndex; i++)
		{
			gA[i] = m1.getGenomeIndex(i);
		}
		
		int gBCount = 0;
		for(int i = xOverIndex; i<mSize; i++)
		{
			gB[gBCount] = m2.getGenomeIndex(i);
			gBCount++;
			
		}

		//call the genome constructor to make a new genome from the two parent's existing genomes.
		genome = new Genome(mSize, xOverIndex, gA, gB, m1);
		
//		System.out.println(genome.getGenomeToString());
	}

	public int getSize()
	{
		return mSize;
	}
	
	public Genome getGenome()
	{
		return genome;
	}
	
	public int getmSize() {
		return mSize;
	}

	public void setmSize(int mSize) {
		this.mSize = mSize;
	}

	public int getMutRate() {
		return mutRate;
	}

	public void setMutRate(int mutRate) {
		this.mutRate = mutRate;
	}

	public int getCrossRate() {
		return crossRate;
	}

	public void setCrossRate(int crossRate) {
		this.crossRate = crossRate;
	}

	public int[] getComGenome()
	{
		return genome.getCompleteGenome();
	}
	
	public String getComGenomeToString()
	{
		return genome.getGenomeToString();
	}
	
	public int getGenomeIndex(int i)
	{
		return genome.getIndex(i);
		
	}
	
	public int getSpeciesType() {
		return speciesType;
	}

	public void setSpeciesType(int speciesType) {
		this.speciesType = speciesType;
	}

	public int compareTo(Object p)
	{
		return (this.currentScore - ((Member)p).currentScore);
	}
    
	
	private class Genome
	{
		Random rand = new Random();
		private int[] completeGenome;
		
		public int[] getCompleteGenome() 
		{
			return completeGenome;
		}
		
		public String getGenomeToString() 
		{
			String comGene = "";
			for(int i = 0; i<completeGenome.length; i++)
			{
				comGene = comGene + completeGenome[i];
			}
			return comGene;
		}
		
		public void setCompleteGenome(int[] completeGenome) {
			this.completeGenome = completeGenome;
		}
		/**
		 * The genome contains numbers in the range of the passed in value "genomeRange"
		 * @param length
		 */
		Genome(int length)
		{
			completeGenome = new int[length];
			
			for(int i = 0; i<completeGenome.length; i++)
			{
				completeGenome[i] = rand.nextInt(genomeRange[i]);
			}
		}
		
		/**
		 * a new genome made from crossing over two existing genomes.
		 * @param m1
		 * @param m2
		 */
		Genome(int length, int crossOver, int[] m1, int[] m2, Member orig)
		{
			completeGenome = new int[length];
			int willCrossover = rand.nextInt(100);
			
			int willMutate = rand.nextInt(100);
			
			if(willCrossover < crossRate)
			{
				for(int i = 0; i<crossOver; i++)
				{
					completeGenome[i] = m1[i];
				}
				
				int m2Count = 0;
				for(int i = crossOver; i<m2.length; i++)
				{
					completeGenome[i] = m2[m2Count];
					m2Count++;
				}
			}
			else
			{
				for(int i = 0; i<mSize; i++)
				{
					completeGenome[i] = orig.getGenomeIndex(i);
				}
			}
			
			for(int i = 0; i<mSize; i++)
			{
				willMutate = rand.nextInt(100);
				if(willMutate < mutRate)
				{
					int tempNum = rand.nextInt(genomeRange[i]);
					if(tempNum != completeGenome[i])
						completeGenome[i] = tempNum;
					else
					{
						while(tempNum == completeGenome[i])
						{
							tempNum = rand.nextInt(genomeRange[i]);
						}
						completeGenome[i] = tempNum;
					}
				}
			}
		}
		
		public int getIndex(int index)
		{
			return completeGenome[index];
		}
		
	}
	
	/**
	 * NEVER USED;
	 * @author brandon
	 *
	 */
	private class GenomePool
	{
		ArrayList allBitGenes = new ArrayList();
		
		/**
		 * Assume bitstring pool if default constructor used.
		 */
		GenomePool()
		{
			
			allBitGenes.add(0);
			allBitGenes.add(1);
		}
		
		/**
		 * incomplete
		 * @param genes
		 */
		GenomePool(String[] genes)
		{
			for(int i = 0; i<genes.length; i++)
			{
				
			}
		}
		
		public ArrayList getAllBitGenes() {
			return allBitGenes;
		}

		public void setAllBitGenes(ArrayList allBitGenes) {
			this.allBitGenes = allBitGenes;
		}

		/**
		 * incomplete
		 * @param genes
		 */
		GenomePool(Integer[] genes)
		{
			for(int i = 0; i<genes.length; i++)
			{
				
			}
		}
		
		
	}
}

