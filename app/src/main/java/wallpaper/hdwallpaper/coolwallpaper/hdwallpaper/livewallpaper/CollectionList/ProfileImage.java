package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList;

import com.google.gson.annotations.SerializedName;

public class ProfileImage{

	@SerializedName("small")
	private String small;

	@SerializedName("large")
	private String large;

	@SerializedName("medium")
	private String medium;

	public void setSmall(String small){
		this.small = small;
	}

	public String getSmall(){
		return small;
	}

	public void setLarge(String large){
		this.large = large;
	}

	public String getLarge(){
		return large;
	}

	public void setMedium(String medium){
		this.medium = medium;
	}

	public String getMedium(){
		return medium;
	}
}