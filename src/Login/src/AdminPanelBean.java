import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AdminPanelBean {

	String memberName, memberTC, memberGender, memberEmail, memberAddress, memberPhone;
	String trainerName, trainerGender, trainerPhone, trainerEmail, trainerDateOfBirth;
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberTC() {
		return memberTC;
	}
	public void setMemberTC(String memberTC) {
		this.memberTC = memberTC;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getTrainerGender() {
		return trainerGender;
	}
	public void setTrainerGender(String trainerGender) {
		this.trainerGender = trainerGender;
	}
	public String getTrainerPhone() {
		return trainerPhone;
	}
	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
	}
	public String getTrainerEmail() {
		return trainerEmail;
	}
	public void setTrainerEmail(String trainerEmail) {
		this.trainerEmail = trainerEmail;
	}
	public String getTrainerDateOfBirth() {
		return trainerDateOfBirth;
	}
	public void setTrainerDateOfBirth(String trainerDateOfBirth) {
		this.trainerDateOfBirth = trainerDateOfBirth;
	}
}
