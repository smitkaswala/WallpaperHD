package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular;

import com.google.gson.annotations.SerializedName;

public class Sponsor{

	@SerializedName("total_photos")
	public int totalPhotos;

	@SerializedName("accepted_tos")
	public boolean acceptedTos;

	@SerializedName("twitter_username")
	public String twitterUsername;

	@SerializedName("last_name")
	public Object lastName;

	@SerializedName("bio")
	public String bio;

	@SerializedName("total_likes")
	public int totalLikes;

	@SerializedName("portfolio_url")
	public String portfolioUrl;

	@SerializedName("profile_image")
	public ProfileImage profileImage;

	@SerializedName("updated_at")
	public String updatedAt;

	@SerializedName("for_hire")
	public boolean forHire;

	@SerializedName("name")
	public String name;

	@SerializedName("location")
	public Object location;

	@SerializedName("links")
	public Links links;

	@SerializedName("total_collections")
	public int totalCollections;

	@SerializedName("id")
	public String id;

	@SerializedName("first_name")
	public String firstName;

	@SerializedName("instagram_username")
	public String instagramUsername;

	@SerializedName("username")
	public String username;

	public int getTotalPhotos(){
		return totalPhotos;
	}

	public boolean isAcceptedTos(){
		return acceptedTos;
	}

	public String getTwitterUsername(){
		return twitterUsername;
	}

	public Object getLastName(){
		return lastName;
	}

	public String getBio(){
		return bio;
	}

	public int getTotalLikes(){
		return totalLikes;
	}

	public String getPortfolioUrl(){
		return portfolioUrl;
	}

	public ProfileImage getProfileImage(){
		return profileImage;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public boolean isForHire(){
		return forHire;
	}

	public String getName(){
		return name;
	}

	public Object getLocation(){
		return location;
	}

	public Links getLinks(){
		return links;
	}

	public int getTotalCollections(){
		return totalCollections;
	}

	public String getId(){
		return id;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getInstagramUsername(){
		return instagramUsername;
	}

	public String getUsername(){
		return username;
	}
}