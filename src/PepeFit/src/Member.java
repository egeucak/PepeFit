
public class Member extends Person {

	private int [] Achievements;
	

	Progress progress = new Progress();
	
	
	public Progress  getProgress() {
		
		return progress;
	}
	
	public void setProgress(Progress progress) {
		
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
