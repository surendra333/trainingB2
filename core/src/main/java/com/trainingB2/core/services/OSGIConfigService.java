package com.trainingB2.core.services;

/**
 * 
 * @author arunkumar.p
 * 
 * This is Clients Configuration Interface
 */
public interface OSGIConfigService {

	/**
	 * 
	 * @return URL
	 */
	public String getURL();
	
	/**
	 * 
	 * @return Path of the Configuration Cf
	 */
	public String getApiConfigCfPath();
	
	/**
	 * 
	 * @return  Brand Logo and Store Label path
	 */
	public String getOfferBrandLogoStorelabelConf();

	/**
	 * 
	 * @return  Valid Channels path
	 */
	public String getOfferValidChannelsConf();
	
	/**
	 * 
	 * @return program ids
	 */
	public String[] getProgramIdsForOnlineOfflineOffers();
	
	/**
	 * 
	 * @return Offer MSD Push Event
	 */
	public String getOfferMsdPushEvent();
	
	/**
	 * 
	 * @return get Offers CTA MSD
	 */
	public String getOffersCTAMSD();
	
	public String getOfferCtaConf();
	
	public String getCromaOfferType();

	public String getOffersAndStoresNearMe();

	public String getRecentAndTrendingOffers();

	public String getPopularOffer();
	
	public String getApiDomain();
	
	public String getBundleOffers();
	
	public String getBundleDetails();
	
	public String getCart();
	
	public String cancelOrder();
	
	public String findOrder();
	
	public String getBundleCarouselApi();
	
	public String getBundleHeroBannerApi();
	
	public String getSavedOfferApi();
	
	public String getOfferByStatusApi();
	
	public String getOfferZoneApiCfPath();
	
	public String getUpdateBundleApi();
	
	public String getBundleApi();
	
	public boolean getEnableShortURI();

	public String getBundleOffersUrl();

	public String getCartBundlesUrlCart();

	public String getDeleteSelectedBundleUrl();

	public String getTargetClient();

	public String getTargetApi();

	public String getTargetVersion();
	
	public String getFetchCustOfferByStatusApi();
	
	public String getServiceabilityStatusApi();
	
	public String getInventoryAndEddStatusApi();
	
	public String getAddToCartApi();

	public String getSSOClientId();
	
	public String getSSOClientSecret();
	
	public String getSSOOcpToken();
	
	public String getChannelSDKAccountId();
	
	public String getChannelsBaseUrl();
	
	public String getStoreId();
	
	public String getStoreIdCroma();
	
	public String getStoreIdIHCLd();
	
	public String getMsdApiCFPath();

	public String getBundleDetailsUrl();
	
	public String getCartCheckoutUrl();
	
	public String getProductDetailsUrl();
	
	public String getSaveToVaultApi();
	
	/**
	 * 
	 * @return Analytics Homepage API
	 */
	public String getAnalyticsHomepageAPI();
	
	/**
	 * 
	 * @return Author Host
	 */
	public String getAuthorHost();
	
	/**
	 * 
	 * @return Authorization Header
	 */
	public String getAuthorizationHeader();

	public String getKeymaServiceabilityApi();
	/**
	 * 
	 * @return Brands CF Path
	 */
	public String getBrandsCFPath();

	public String getMicroSegmentApi();
	
	public String getMicroSegmentApiHttpMethod();
	
	public String getZoneOffset();
	
	public String[] getDaysOffset();

	public String getOrderDetailsApiCF();

	public String getSegmentFolderPath();
	public String getOffersBaseFolderPath();


	
	public long getImageQuality();
	
	/**
	 * 
	 * @return PWA Desktop Host URL
	 */
	public String getPwaDesktopHostUrl();
	
	public String ohClientId();
	
	public String ohAuth();
	public String[] getBbCancellableConditions();

	public String getCliqStatusList();

	public String getCromaVasUrl();

	public String getOrderHistoryThreadPoolCount();

	public String getBbClientId();

	public String getBbAuthorization();

	public String getBbFetchCustumerApi();

	public String getBbStatusTitlePath();

	public String getCromaStatusTitlePath();	
	public String getCartCountApi();

	public String getOrderCancellationCfPath();

	public String getAppKey();
	
	public String getCromaProgramId();

	public String getSsoValidateTockenUrl();
	
	public String getCromaMerchantId();
	
	public String getBBMerchantId();
	
	public String getTataCliqMerchantId();
	
	public String getLuxuryMerchantId();
	
	public String getCsPartnerApiDomain();
	
	public String getTargetApiForLocationModelJson();
}