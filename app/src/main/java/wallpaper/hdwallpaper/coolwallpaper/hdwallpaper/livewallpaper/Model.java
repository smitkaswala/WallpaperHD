package wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper;

import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionList.CollectionResponse;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.CollectionPage.ResponseCollection;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Latest.ResponseModel;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.Popular.ResponseClass;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.SearchWallpaper.ResponseWallpaper;
import wallpaper.hdwallpaper.coolwallpaper.hdwallpaper.livewallpaper.UserDetail.ResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Model {

   @GET("photos/")
   Call<List<ResponseClass>> getpost(
           @Query("client_id")String clientid,
           @Query("page")String page,
           @Query("order_by")String orderby,
           @Query("per_page")String perpage);

   @GET("photos/")
   Call<List<ResponseModel>> getpost1(
           @Query("client_id")String clientid,
           @Query("page") String page,
           @Query("order_by")String orderby,
           @Query("per_page") String perpage);

   @GET("photos/")
   Call<List<ResponseUser>> getpost2(
           @Query("client_id")String clientid,
           @Query("page")String page1,
           @Query("order_by")String orderby,
           @Query("per_page")String perpage);

   @GET("search/collections/")
   Call<CollectionResponse> getpost3(
           @Query("client_id")String clientid,
           @Query("query")String query,
           @Query("page")String page,
           @Query("per_page")String per_page);

   @GET("search/photos/")
   Call<ResponseWallpaper> getpost4(
           @Query("client_id")String client_id,
           @Query("query")String query,
           @Query("page")String page,
           @Query("per_page")String per_page);

   @GET("collections/{id}/photos")
   Call<List<ResponseCollection>>getpost5(
           @Path("id")String id,
           @Query("client_id")String client_id,
           @Query("page")String page,
           @Query("per_page")String per_page);

}
