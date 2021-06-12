package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

public class FavoriteImage {

    private String key_id;
    private String small_image;
    private String full_image;
    private String regular_image;


    public FavoriteImage() {
    }

    public FavoriteImage(String key_id, String small_image, String full_image, String regular_image) {
        this.key_id = key_id;
        this.small_image = small_image;
        this.full_image = full_image;
        this.regular_image = regular_image;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getFull_image() {
        return full_image;
    }

    public void setFull_image(String full_image) {
        this.full_image = full_image;
    }

    public String getRegular_image() {
        return regular_image;
    }

    public void setRegular_image(String regular_image) {
        this.regular_image = regular_image;
    }

}
