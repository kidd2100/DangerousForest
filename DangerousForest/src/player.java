
public class player {
	String playerDirection;
	final int BEGINNING_PLAYER_HEALTH = 100;
	int playerHealth = 100;
	int bonusHealth = 20;
	int playerX = 6;
	int playerY = 0;
	
	public void setPlayerLocation(int x, int y) {
		playerX = x;
		playerY = y;
	}
	
	public void setPlayerHealth(int lifeLoss) {
		playerHealth = playerHealth - lifeLoss;
	}
	
	public void setPlayerHealthPotion() {
		
	}
	public void setBonusHealth() {
		bonusHealth = (BEGINNING_PLAYER_HEALTH - playerHealth)/2;
		playerHealth = playerHealth + bonusHealth;
	}
	
	
	public void setDirection(String Direction) {
		if(Direction == "NORTH")
			playerDirection = "NORTH";
		else if(Direction == "WEST")
			playerDirection = "WEST";
		else if(Direction == "SOUTH")
			playerDirection = "SOUTH";
		else if(Direction == "EAST")
			playerDirection = "EAST";
	}
	
	public int getPlayerX() {
		return playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	
	public int getBonusHealth() {
		return bonusHealth;
	}
	
	public int getPlayerHealth() {
		return playerHealth;
	}
	
	public String getDirection() {
		return playerDirection;
	}
}
