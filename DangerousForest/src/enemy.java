
public class enemy {
	private int enemyLife;
	enemy(){
		enemyLife = 100;
	}
	public void setEnemyLife(int lifeLoss){
		enemyLife = enemyLife - lifeLoss;
	}
	
	public int getEnemyLife() {
		return enemyLife;
	}
}
