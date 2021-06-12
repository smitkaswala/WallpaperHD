package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Sponsorship{

	@SerializedName("sponsor")
	public Sponsor sponsor;

	@SerializedName("tagline_url")
	public String taglineUrl;

	@SerializedName("tagline")
	public String tagline;

	@SerializedName("impression_urls")
	public List<Object> impressionUrls;

	public Sponsor getSponsor(){
		return sponsor;
	}

	public String getTaglineUrl(){
		return taglineUrl;
	}

	public String getTagline(){
		return tagline;
	}

	public List<Object> getImpressionUrls(){
		return impressionUrls;
	}
}