package com.ssafy.kpc.retrofit;

import java.io.Serializable;

public class NaverAPIResponse implements Serializable {
    public NaverAPIResponse(String markerId, String markerType, float latitude, float longitude, String complexName, String realEstateTypeCode, String realEstateTypeName, String completionYearMonth, float totalDongCount, float totalHouseholdCount, float floorAreaRatio, String minArea, String maxArea, float priceCount, float representativeArea, boolean isPresales, float photoCount, float dealCount, float leaseCount, float rentCount, float shortTermRentCount, float totalArticleCount, boolean existPriceTab) {
        this.markerId = markerId;
        this.markerType = markerType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.complexName = complexName;
        this.realEstateTypeCode = realEstateTypeCode;
        this.realEstateTypeName = realEstateTypeName;
        this.completionYearMonth = completionYearMonth;
        this.totalDongCount = totalDongCount;
        this.totalHouseholdCount = totalHouseholdCount;
        this.floorAreaRatio = floorAreaRatio;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.priceCount = priceCount;
        this.representativeArea = representativeArea;
        this.isPresales = isPresales;
        this.photoCount = photoCount;
        this.dealCount = dealCount;
        this.leaseCount = leaseCount;
        this.rentCount = rentCount;
        this.shortTermRentCount = shortTermRentCount;
        this.totalArticleCount = totalArticleCount;
        this.existPriceTab = existPriceTab;
    }
    private String markerId;
    private String markerType;
    private float latitude;
    private float longitude;
    private String complexName;
    private String realEstateTypeCode;
    private String realEstateTypeName;
    private String completionYearMonth;
    private float totalDongCount;
    private float totalHouseholdCount;
    private float floorAreaRatio;
    private String minArea;
    private String maxArea;
    private float priceCount;
    private float representativeArea;
    private boolean isPresales;
    private float photoCount;
    private float dealCount;
    private float leaseCount;
    private float rentCount;
    private float shortTermRentCount;
    private float totalArticleCount;
    private boolean existPriceTab;


    // Getter Methods

    public String getMarkerId() {
        return markerId;
    }

    public String getMarkerType() {
        return markerType;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getComplexName() {
        return complexName;
    }

    public String getRealEstateTypeCode() {
        return realEstateTypeCode;
    }

    public String getRealEstateTypeName() {
        return realEstateTypeName;
    }

    public String getCompletionYearMonth() {
        return completionYearMonth;
    }

    public float getTotalDongCount() {
        return totalDongCount;
    }

    public float getTotalHouseholdCount() {
        return totalHouseholdCount;
    }

    public float getFloorAreaRatio() {
        return floorAreaRatio;
    }

    public String getMinArea() {
        return minArea;
    }

    public String getMaxArea() {
        return maxArea;
    }

    public float getPriceCount() {
        return priceCount;
    }

    public float getRepresentativeArea() {
        return representativeArea;
    }

    public boolean getIsPresales() {
        return isPresales;
    }

    public float getPhotoCount() {
        return photoCount;
    }

    public float getDealCount() {
        return dealCount;
    }

    public float getLeaseCount() {
        return leaseCount;
    }

    public float getRentCount() {
        return rentCount;
    }

    public float getShortTermRentCount() {
        return shortTermRentCount;
    }

    public float getTotalArticleCount() {
        return totalArticleCount;
    }

    public boolean getExistPriceTab() {
        return existPriceTab;
    }

    // Setter Methods

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }

    public void setMarkerType(String markerType) {
        this.markerType = markerType;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }

    public void setRealEstateTypeCode(String realEstateTypeCode) {
        this.realEstateTypeCode = realEstateTypeCode;
    }

    public void setRealEstateTypeName(String realEstateTypeName) {
        this.realEstateTypeName = realEstateTypeName;
    }

    public void setCompletionYearMonth(String completionYearMonth) {
        this.completionYearMonth = completionYearMonth;
    }

    public void setTotalDongCount(float totalDongCount) {
        this.totalDongCount = totalDongCount;
    }

    public void setTotalHouseholdCount(float totalHouseholdCount) {
        this.totalHouseholdCount = totalHouseholdCount;
    }

    public void setFloorAreaRatio(float floorAreaRatio) {
        this.floorAreaRatio = floorAreaRatio;
    }

    public void setMinArea(String minArea) {
        this.minArea = minArea;
    }

    public void setMaxArea(String maxArea) {
        this.maxArea = maxArea;
    }

    public void setPriceCount(float priceCount) {
        this.priceCount = priceCount;
    }

    public void setRepresentativeArea(float representativeArea) {
        this.representativeArea = representativeArea;
    }

    public void setIsPresales(boolean isPresales) {
        this.isPresales = isPresales;
    }

    public void setPhotoCount(float photoCount) {
        this.photoCount = photoCount;
    }

    public void setDealCount(float dealCount) {
        this.dealCount = dealCount;
    }

    public void setLeaseCount(float leaseCount) {
        this.leaseCount = leaseCount;
    }

    public void setRentCount(float rentCount) {
        this.rentCount = rentCount;
    }

    public void setShortTermRentCount(float shortTermRentCount) {
        this.shortTermRentCount = shortTermRentCount;
    }

    public void setTotalArticleCount(float totalArticleCount) {
        this.totalArticleCount = totalArticleCount;
    }

    public void setExistPriceTab(boolean existPriceTab) {
        this.existPriceTab = existPriceTab;
    }
}