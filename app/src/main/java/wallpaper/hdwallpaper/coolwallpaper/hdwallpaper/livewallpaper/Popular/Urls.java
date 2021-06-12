package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular;

import com.google.gson.annotations.SerializedName;

public class Urls{

	@SerializedName("small")
	public String small;

	@SerializedName("thumb")
	public String thumb;

	@SerializedName("raw")
	public String raw;

	@SerializedName("regular")
	public String regular;

	@SerializedName("full")
	public String full;

	public String getSmall(){
		return small;
	}

	public String getRaw(){
		return raw;
	}

	public String getRegular(){
		return regular;
	}

	public String getFull(){
		return full;
	}


}