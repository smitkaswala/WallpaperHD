package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList;

import com.google.gson.annotations.SerializedName;

public class Ancestry{

	@SerializedName("type")
	private Type type;

	@SerializedName("category")
	private Category category;

	@SerializedName("subcategory")
	private Subcategory subcategory;

	public void setType(Type type){
		this.type = type;
	}

	public Type getType(){
		return type;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setSubcategory(Subcategory subcategory){
		this.subcategory = subcategory;
	}

	public Subcategory getSubcategory(){
		return subcategory;
	}
}