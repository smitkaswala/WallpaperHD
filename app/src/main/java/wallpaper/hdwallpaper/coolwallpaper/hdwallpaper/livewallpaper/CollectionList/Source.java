package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList;

import com.google.gson.annotations.SerializedName;

public class Source{

	@SerializedName("meta_description")
	private String metaDescription;

	@SerializedName("ancestry")
	private Ancestry ancestry;

	@SerializedName("cover_photo")
	private CoverPhoto coverPhoto;

	@SerializedName("meta_title")
	private String metaTitle;

	@SerializedName("subtitle")
	private String subtitle;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	public void setMetaDescription(String metaDescription){
		this.metaDescription = metaDescription;
	}

	public String getMetaDescription(){
		return metaDescription;
	}

	public void setAncestry(Ancestry ancestry){
		this.ancestry = ancestry;
	}

	public Ancestry getAncestry(){
		return ancestry;
	}

	public void setCoverPhoto(CoverPhoto coverPhoto){
		this.coverPhoto = coverPhoto;
	}

	public CoverPhoto getCoverPhoto(){
		return coverPhoto;
	}

	public void setMetaTitle(String metaTitle){
		this.metaTitle = metaTitle;
	}

	public String getMetaTitle(){
		return metaTitle;
	}

	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}

	public String getSubtitle(){
		return subtitle;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}