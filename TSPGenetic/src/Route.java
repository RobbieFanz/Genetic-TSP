
public class Route {
	private int[][] directions = new int[4][4];//outer is city inner is ranking of each direction to go

	public Route(int[][] directions) {
	this.directions = directions;
	}
	
	//the chromosomes should be the cities in order
	
	
	
	// def wrong 
//	public int getTime(int[][] map) {
//		int mapSize = map.length;
//		int distanceTraveled=0;//distance traveled so far
//		int pos=0;//current city
//		int[] visited = new int[4];
//		
//		for(int k = 0; k<mapSize; k++) {//works because the starting city is always 0
//			int smallest = 10000;//arb high num
//			
//			for(int i = 0; i<mapSize; i++) {
//				if (pos == i) {
//					i++;
//				}
//				
//				if(smallest > directions[k][i]) {
//					smallest = directions[k][i];// finding the best ranking in the directions available
//				}
//				//find smallest then check if that city is available, then move in that direction
//			visited[k]=smallest;
//			
//				
//		}
//		
//		
//		return distanceTraveled;
//	}
//	
//	
//	}}
