package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.UserDetail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Sponsorship{

	@SerializedName("sponsor")
	private Sponsor sponsor;

	@SerializedName("tagline_url")
	private String taglineUrl;

	@SerializedName("tagline")
	private String tagline;

	@SerializedName("impression_urls")
	private List<String> impressionUrls;

	public void setSponsor(Sponsor sponsor){
		this.sponsor = sponsor;
	}

	public Sponsor getSponsor(){
		return sponsor;
	}

	public void setTaglineUrl(String taglineUrl){
		this.taglineUrl = taglineUrl;
	}

	public String getTaglineUrl(){
		return taglineUrl;
	}

	public void setTagline(String tagline){
		this.tagline = tagline;
	}

	public String getTagline(){
		return tagline;
	}

	public void setImpressionUrls(List<String> impressionUrls){
		this.impressionUrls = impressionUrls;
	}

	public List<String> getImpressionUrls(){
		return impressionUrls;
	}
}