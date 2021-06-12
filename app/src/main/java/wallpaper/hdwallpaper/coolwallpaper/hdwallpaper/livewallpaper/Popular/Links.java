package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("followers")
	public String followers;

	@SerializedName("portfolio")
	public String portfolio;

	@SerializedName("following")
	public String following;

	@SerializedName("self")
	public String self;

	@SerializedName("html")
	public String html;

	@SerializedName("photos")
	public String photos;

	@SerializedName("likes")
	public String likes;

	@SerializedName("download")
	public String download;

	@SerializedName("download_location")
	public String downloadLocation;

	public String getFollowers(){
		return followers;
	}

	public String getPortfolio(){
		return portfolio;
	}

	public String getFollowing(){
		return following;
	}

	public String getSelf(){
		return self;
	}

	public String getHtml(){
		return html;
	}

	public String getPhotos(){
		return photos;
	}

	public String getLikes(){
		return likes;
	}

	public String getDownload(){
		return download;
	}

	public String getDownloadLocation(){
		return downloadLocation;
	}
}