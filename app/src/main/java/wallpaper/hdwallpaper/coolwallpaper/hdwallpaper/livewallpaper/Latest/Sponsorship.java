package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Latest;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Sponsorship{

	@SerializedName("sponsor")
	private Sponsor sponsor;

	@SerializedName("tagline_url")
	private Object taglineUrl;

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

	public void setTaglineUrl(Object taglineUrl){
		this.taglineUrl = taglineUrl;
	}

	public Object getTaglineUrl(){
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