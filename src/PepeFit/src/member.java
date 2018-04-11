
public class member extends person{

	private int [] Achievements;
	

	progress progress = new progress();
	
	
	public progress  getProgress() {
		
		return progress;
	}
	
	public void setProgress(progress progress) {
		
		this.progress=progress;
	}
	
	
	public void loadStudent(String userName) {
		
		
	}
	
	public int [] getAchievements() {
		return Achievements;
	}
	
	public void setAchievements(int[] achievements) {
		Achievements = achievements;
	}
	
	
}
