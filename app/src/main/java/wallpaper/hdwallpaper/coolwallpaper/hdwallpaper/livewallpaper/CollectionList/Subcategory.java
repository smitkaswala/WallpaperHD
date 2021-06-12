package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList;

import com.google.gson.annotations.SerializedName;

public class Subcategory{

	@SerializedName("pretty_slug")
	private String prettySlug;

	@SerializedName("slug")
	private String slug;

	public void setPrettySlug(String prettySlug){
		this.prettySlug = prettySlug;
	}

	public String getPrettySlug(){
		return prettySlug;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}
}