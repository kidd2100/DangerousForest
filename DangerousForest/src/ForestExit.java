
public class ForestExit {
	private int exitX;
	private int exitY;
	
	ForestExit(){
		double randomX;
		exitY = 12;
		randomX = (Math.random() * (12 - 1 + 1) + 1);
		exitX = (int)randomX;
	}
	
	public int getExitX() {
		return exitX;
	}
	
	public int getExitY() {
		return exitY;
	}
}
