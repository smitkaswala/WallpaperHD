package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.SearchWallpaper;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("total_photos")
	private int totalPhotos;

	@SerializedName("accepted_tos")
	private boolean acceptedTos;

	@SerializedName("twitter_username")
	private String twitterUsername;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("bio")
	private String bio;

	@SerializedName("total_likes")
	private int totalLikes;

	@SerializedName("portfolio_url")
	private String portfolioUrl;

	@SerializedName("profile_image")
	private ProfileImage profileImage;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("for_hire")
	private boolean forHire;

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private String location;

	@SerializedName("links")
	private Links links;

	@SerializedName("total_collections")
	private int totalCollections;

	@SerializedName("id")
	private String id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("instagram_username")
	private String instagramUsername;

	@SerializedName("username")
	private String username;

	public int getTotalPhotos(){
		return totalPhotos;
	}

	public boolean isAcceptedTos(){
		return acceptedTos;
	}

	public String getTwitterUsername(){
		return twitterUsername;
	}

	public String getLastName(){
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

	public String getLocation(){
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