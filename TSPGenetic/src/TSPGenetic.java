import java.util.Random;


public class TSPGenetic {//needs larger dataset 
	
	private static Random rand = new Random();
	
	final static int sizeOfMap = 11;
	private static final int map[][] = new int [][] {{0,3,3,8,9,10000,6,11,10000,10000,10000},
											  {3,0,2,6,5,7,10000,10000,10000,10000,10000},
											  {3,2,0,9,7,6,3,10000,5,10000,10000},
											  {8,6,9,0,2,10000,10000,1,10000,10000,6},
											  {9,5,7,2,0,6,10000,4,10000,8,2},
											  {10000,7,6,10000,6,0,6,10000,3,3,8},
											  {6,10000,3,10000,10000,6,0,10000,4,10000,10000},
											  {11,10000,10000,1,4,10000,10000,0,10000,10000,8},
											  {10000,10000,5,10000,10000,3,4,10000,0,7,10000},
											  {10000,10000,10000,10000,8,3,10000,10000,7,0,6},
											  {10000,10000,10000,6,2,8,10000,8,10000,6,0}};
	
	//static int population[][]= new int[6][5];
	
	public static int fitVal(int[] gene) {
		int distance = 0;
		for(int i=0; i<gene.length-1; i++) {
			distance += map[gene[i]][gene[i+1]];
		}
		distance += map[gene[gene.length-1]][gene[0]];//loops back to start
		return distance;
	}
	
	public static int[][] resPop(int[][] pop) {
		int best[][]=new int [3][sizeOfMap];
		int fitnesses[]=new int[pop.length];
		
		for(int i = 0; i<pop.length;i++) {
			fitnesses[i] = fitVal(pop[i]);
		}
		for(int j=0;j<3;j++) {
			int fitest = 9999999;
			int fitNum=10;
			for(int k= 0; k<fitnesses.length;k++) {
				if(fitest>fitnesses[k]) {
					fitest=fitnesses[k];
					fitNum=k;
				}
			}
			best[j]=pop[fitNum];
			fitnesses[fitNum]=99999999;
		}
		
//		if(best[1].equals(best[0])||best[1].equals(best[2])){
//			best[1]=mutate(best[1]);
//		}
//		if(best[2].equals(best[0])||best[2].equals(best[1])){
//			best[2]=mutate(best[2]);
//		}
		
		pop[0]=best[0];
		pop[1]=best[1];
		pop[2]=best[2];
		pop[3]=crossOver(best[0],best[1]);
		pop[4]=crossOver(best[1],best[2]);
		pop[5]=crossOver(best[0],best[2]);
		
		return pop;
	}
	
	public static int[] crossOver(int[] path1, int[] path2) {
		int path[] = new int[sizeOfMap];
		for(int i = 0; i<path.length; i++) {
			if(i>(path.length/2)) {
				path[i]=path2[i];
			}else {
				path[i]=path1[i];
			}
		}
		path = checkThenMutate(path);
		return path;
	}
	
	public static int[] checkThenMutate(int[] path){
		//check for repeats
		int visited[] = new int[sizeOfMap];
		for(int i = 0; i<path.length; i++) {
			if(visited[path[i]]==0) {
				visited[path[i]]=1;
			}else {
				//find an unused city
				boolean loop = true;
				int j = 0;
				while(loop) {
					if(visited[j]==0) {
						loop = false;
						path[i]=j;
						visited[j]=1;
					}
					j++;
				}
			}
		}
		
		
		path = mutate(path);
		
		return path;
	}
	
	public static int[] mutate(int[] path) {
		//mutate 50% chance of a mutation 3 times
		for(int i =0; i<3;i++) {
			
				int swap1 = rand.nextInt(sizeOfMap);
				int swap2 = rand.nextInt(sizeOfMap);
				int mid = path[swap1];
				path[swap1]=path[swap2];
				path[swap2]=mid;
		}
		
		return path;
	}
	

	public static void main(String[] args) {
		int population[][] = {{0,1,2,3,4,10,9,8,7,6,5},
					{4,3,10,2,9,1,8,7,0,6,5},
					{3,1,2,0,4,7,10,9,5,8,6},
					{6,2,8,5,9,4,1,3,7,10,0},
					{1,4,3,2,0,9,8,7,6,5,10},
					{1,2,4,3,0,6,8,5,9,10,7}};
		
		for(int i =0; i<3000;i++) {
			population = resPop(population);
		}
		
		for(int j=0; j<population.length; j++) {
			for(int k = 0; k<population[0].length; k++) {
				System.out.print(population[j][k]);
			}
			System.out.print(" "+fitVal(population[j]));
			
			System.out.println();
		}
		
	}

}
