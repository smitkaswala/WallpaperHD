package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionPage;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCollection{

	@SerializedName("current_user_collections")
	private List<Object> currentUserCollections;

	@SerializedName("color")
	private String color;

	@SerializedName("sponsorship")
	private Object sponsorship;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("liked_by_user")
	private boolean likedByUser;

	@SerializedName("urls")
	private Urls urls;

	@SerializedName("alt_description")
	private String altDescription;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("width")
	private int width;

	@SerializedName("blur_hash")
	private String blurHash;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private String id;

	@SerializedName("categories")
	private List<Object> categories;

	@SerializedName("promoted_at")
	private String promotedAt;

	@SerializedName("user")
	private User user;

	@SerializedName("height")
	private int height;

	@SerializedName("likes")
	private int likes;

	public void setCurrentUserCollections(List<Object> currentUserCollections){
		this.currentUserCollections = currentUserCollections;
	}

	public List<Object> getCurrentUserCollections(){
		return currentUserCollections;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setSponsorship(Object sponsorship){
		this.sponsorship = sponsorship;
	}

	public Object getSponsorship(){
		return sponsorship;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLikedByUser(boolean likedByUser){
		this.likedByUser = likedByUser;
	}

	public boolean isLikedByUser(){
		return likedByUser;
	}

	public void setUrls(Urls urls){
		this.urls = urls;
	}

	public Urls getUrls(){
		return urls;
	}

	public void setAltDescription(String altDescription){
		this.altDescription = altDescription;
	}

	public String getAltDescription(){
		return altDescription;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setBlurHash(String blurHash){
		this.blurHash = blurHash;
	}

	public String getBlurHash(){
		return blurHash;
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

	public void setCategories(List<Object> categories){
		this.categories = categories;
	}

	public List<Object> getCategories(){
		return categories;
	}

	public void setPromotedAt(String promotedAt){
		this.promotedAt = promotedAt;
	}

	public String getPromotedAt(){
		return promotedAt;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	public void setLikes(int likes){
		this.likes = likes;
	}

	public int getLikes(){
		return likes;
	}
}