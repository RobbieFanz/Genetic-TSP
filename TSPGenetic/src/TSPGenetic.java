
public class TSPGenetic {//needs larger dataset 
	
	private static final int map[][] = new int [][] {{0,3,3,8,9},
											  {3,0,2,6,5},
											  {3,2,0,9,7},
											  {8,6,9,0,2},
											  {9,5,7,2,0}};
	
	//static int population[][]= new int[6][5];
	
	public static int fitVal(int[] gene) {
		int distance = 0;
		for(int i=0; i<gene.length-1; i++) {
			distance += map[gene[i]][gene[i+1]];
		}
		distance += map[gene[4]][gene[0]];//loops back to start
		return distance;
	}
	
	public static int[][] resPop(int[][] pop) {
		int best[][]=new int [3][5];
		int fitnesses[]=new int[pop.length];
		
		for(int i = 0; i<pop.length;i++) {
			fitnesses[i] = fitVal(pop[i]);
		}
		for(int j=0;j<3;j++) {
			int fitest = 999;
			int fitNum=10;
			for(int k= 0; k<fitnesses.length;k++) {
				if(fitest>fitnesses[k]) {
					fitest=fitnesses[k];
					fitNum=k;
				}
			}
			best[j]=pop[fitNum];
			fitnesses[fitNum]=999;
		}
		pop[0]=best[0];
		pop[1]=best[1];
		pop[2]=best[2];
		pop[3]=crossOver(best[0],best[1]);
		pop[4]=crossOver(best[1],best[2]);
		pop[5]=crossOver(best[0],best[2]);
		
		return pop;
	}
	
	public static int[] crossOver(int[] path1, int[] path2) {
		int path[] = new int[5];
		for(int i = 0; i<path.length; i++) {
			if(i>1) {
				path[i]=path2[i];
			}else {
				path[i]=path1[i];
			}
		}
		path = mutate(path);
		return path;
	}
	
	public static int[] mutate(int[] path){
		int visited[] = new int[5];
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
		return path;
	}
	

	public static void main(String[] args) {
		int population[][] = {{0,1,2,3,4},
					{4,3,2,1,0},
					{3,1,2,0,4},
					{2,0,4,1,3},
					{0,3,4,1,2},
					{1,2,4,3,0}};
		
		for(int i =0; i<300;i++) {
			population = resPop(population);
		}
		
		for(int j=0; j<population.length; j++) {
			for(int k = 0; k<population[0].length; k++) {
				System.out.print(population[j][k]);
			}
			System.out.println();
		}
		
	}

}
