package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("featured")
	private boolean featured;

	@SerializedName("private")
	private boolean jsonMemberPrivate;

	@SerializedName("cover_photo")
	private CoverPhoto coverPhoto;

	@SerializedName("total_photos")
	private int totalPhotos;

	@SerializedName("share_key")
	private String shareKey;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	@SerializedName("tags")
	private List<TagsItem> tags;

	@SerializedName("preview_photos")
	private List<PreviewPhotosItem> previewPhotos;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("curated")
	private boolean curated;

	@SerializedName("last_collected_at")
	private String lastCollectedAt;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private String id;

	@SerializedName("published_at")
	private String publishedAt;

	@SerializedName("user")
	private User user;

	public void setFeatured(boolean featured){
		this.featured = featured;
	}

	public boolean isFeatured(){
		return featured;
	}

	public void setJsonMemberPrivate(boolean jsonMemberPrivate){
		this.jsonMemberPrivate = jsonMemberPrivate;
	}

	public boolean isJsonMemberPrivate(){
		return jsonMemberPrivate;
	}

	public void setCoverPhoto(CoverPhoto coverPhoto){
		this.coverPhoto = coverPhoto;
	}

	public CoverPhoto getCoverPhoto(){
		return coverPhoto;
	}

	public void setTotalPhotos(int totalPhotos){
		this.totalPhotos = totalPhotos;
	}

	public int getTotalPhotos(){
		return totalPhotos;
	}

	public void setShareKey(String shareKey){
		this.shareKey = shareKey;
	}

	public String getShareKey(){
		return shareKey;
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

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public void setPreviewPhotos(List<PreviewPhotosItem> previewPhotos){
		this.previewPhotos = previewPhotos;
	}

	public List<PreviewPhotosItem> getPreviewPhotos(){
		return previewPhotos;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCurated(boolean curated){
		this.curated = curated;
	}

	public boolean isCurated(){
		return curated;
	}

	public void setLastCollectedAt(String lastCollectedAt){
		this.lastCollectedAt = lastCollectedAt;
	}

	public String getLastCollectedAt(){
		return lastCollectedAt;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}