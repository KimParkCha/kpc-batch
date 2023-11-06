package com.ssafy.kpc.retrofit;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface NaverAPIService {
    @GET("/api/complexes/single-markers/2.0")
    Call<List<NaverAPIResponse>> fetchRealEstate(
            @Query("cortarNo")
            Long cortarNo,
            @Query("zoom")
            Integer zoom,
            @Query("priceType")
            String priceType,
            @Query("markerId")
            String markerId,
            @Query("markerType")
            String markerType,
            @Query("selectedComplexNo")
            String selectedComplexNo,
            @Query("selectedComplexBuildingNo")
            String selectedComplexBuildingNo,
            @Query("fakeComplexMarker")
            String fakeComplexMarker,
            @Query("realEstateType")
            String realEstateType,
            @Query("tradeType")
            String tradeType,
//            @Query("rentPriceMin")
//            Long rentPriceMin,
//            @Query("rentPriceMax")
//            Long rentPriceMax,
//            @Query("priceMin")
//            Long priceMin,
//            @Query("priceMax")
//            Long priceMax,
//            @Query("areaMin")
//            Long areaMin,
//            @Query("areaMax")
//            Long areaMax,
            @Query("oldBuildYears")
            String oldBuildYears,
            @Query("recentlyBuildYears")
            String recentlyBuildYears,
            @Query("minHouseHoldCount")
            String minHouseHoldCount,
            @Query("maxHouseHoldCount")
            String maxHouseHoldCount,
            @Query("showArticle")
            Boolean showArticle,
            @Query("sameAddressGroup")
            Boolean sameAddressGroup,
            @Query("minMaintenanceCost")
            String minMaintenanceCost,
            @Query("maxMaintenanceCost")
            String maxMaintenanceCost,
            @Query("directions")
            String directions,
            @Query("leftLon")
            Double leftLon,
            @Query("rightLon")
            Double rightLon,
            @Query("topLat")
            Double topLat,
            @Query("bottomLat")
            Double bottomLat,
            @Query("isPresale")
            Boolean isPresale
    );
}