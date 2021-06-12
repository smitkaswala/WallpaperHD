package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseClass {

	@SerializedName("current_user_collections")
	public List<Object> currentUserCollections;

	@SerializedName("color")
	public String color;

	@SerializedName("sponsorship")
	public Sponsorship sponsorship;

	@SerializedName("created_at")
	public String createdAt;

	@SerializedName("description")
	public Object description;

	@SerializedName("liked_by_user")
	public boolean likedByUser;

	@SerializedName("urls")
	public Urls urls;

	@SerializedName("alt_description")
	public String altDescription;

	@SerializedName("updated_at")
	public String updatedAt;

	@SerializedName("width")
	public int width;

	@SerializedName("blur_hash")
	public String blurHash;

	@SerializedName("links")
	public Links links;

	@SerializedName("id")
	public String id;

	@SerializedName("categories")
	public List<Object> categories;

	@SerializedName("promoted_at")
	public Object promotedAt;

	@SerializedName("user")
	public User user;

	@SerializedName("height")
	public int height;

	@SerializedName("likes")
	public int likes;

	public List<Object> getCurrentUserCollections() {
		return currentUserCollections;
	}

	public String getColor() {
		return color;
	}

	public Sponsorship getSponsorship() {
		return sponsorship;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public Object getDescription() {
		return description;
	}

	public boolean isLikedByUser() {
		return likedByUser;
	}

	public Urls getUrls() {
		return urls;
	}

	public String getAltDescription() {
		return altDescription;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public int getWidth() {
		return width;
	}

	public String getBlurHash() {
		return blurHash;
	}

	public Links getLinks() {
		return links;
	}

	public String getId() {
		return id;
	}

	public List<Object> getCategories() {
		return categories;
	}

	public Object getPromotedAt() {
		return promotedAt;
	}

	public User getUser() {
		return user;
	}

	public int getHeight() {
		return height;
	}

	public int getLikes() {
		return likes;
	}

}

