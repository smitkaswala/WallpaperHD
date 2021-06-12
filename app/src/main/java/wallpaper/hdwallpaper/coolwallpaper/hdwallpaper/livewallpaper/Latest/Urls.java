package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Latest;

import com.google.gson.annotations.SerializedName;

public class Urls{

	@SerializedName("small")
	private String small;

	@SerializedName("thumb")
	private String thumb;

	@SerializedName("raw")
	private String raw;

	@SerializedName("regular")
	private String regular;

	@SerializedName("full")
	private String full;

	public void setSmall(String small){
		this.small = small;
	}

	public String getSmall(){
		return small;
	}

	public void setThumb(String thumb){
		this.thumb = thumb;
	}

	public String getThumb(){
		return thumb;
	}

	public void setRaw(String raw){
		this.raw = raw;
	}

	public String getRaw(){
		return raw;
	}

	public void setRegular(String regular){
		this.regular = regular;
	}

	public String getRegular(){
		return regular;
	}

	public void setFull(String full){
		this.full = full;
	}

	public String getFull(){
		return full;
	}
}