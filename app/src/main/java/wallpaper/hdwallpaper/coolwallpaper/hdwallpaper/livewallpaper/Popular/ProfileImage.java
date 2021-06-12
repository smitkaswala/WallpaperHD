package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular;

import com.google.gson.annotations.SerializedName;

public class ProfileImage{

	@SerializedName("small")
	public String small;

	@SerializedName("large")
	public String large;

	@SerializedName("medium")
	public String medium;

	public String getSmall(){
		return small;
	}

	public String getLarge(){
		return large;
	}

	public String getMedium(){
		return medium;
	}
}