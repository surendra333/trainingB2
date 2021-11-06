package com.trainingB2.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * 
 * @author arunkumar.p This is PWA OSGI Configuration interface
 */
@ObjectClassDefinition(name = "PWA OSGI Configuration", description = "PWA OSGI Configuration")
public @interface PWAOsgiConfiguration {

	@AttributeDefinition(name = "TDL Domain URL", description = "TDL Domain URL")
	String url();

	@AttributeDefinition(name = "API Config CF Path", description = "API Config CF Path")
	String apiConfigCfPath();

	@AttributeDefinition(name = "Offer Type - Croma", description = "Offer Type - Croma")
	String cromaOfferTypes();
	
	@AttributeDefinition(name = "Offer CTA", description = "Offer CTA Configuration")
    String offerCtaConf();
	
	@AttributeDefinition(name = "OffersAndStoresNearMe URL", description = " OffersAndStoresNearMe URL")
    String offersAndStoresNearMe();
	
	@AttributeDefinition(name = "Popularoffer URL ", description = "Popularoffer url")
    String popularoffer();
	
	@AttributeDefinition(name = "RecentAndTrendingOffers URL", description = "RecentAndTrendingOffers url")
    String recentAndTrendingOffers();
	
	@AttributeDefinition(name = "API Domain", description = "API Domain")
	String apiDomain();
	
	@AttributeDefinition(name = "Bundle Offers Path", description = "BundleOffers Path ")
	String bundleOffers();
	
	@AttributeDefinition(name = "Bundle Details Api", description = "BundleDetails Api ")
	String bundleDetails();
	
	@AttributeDefinition(name = "PIM Carts Api", description = "PIM Carts Api")
	String cartApi();
	
	@AttributeDefinition(name = "Cancel Order API", description = "Cancel Order API")
	String cancelOrder();
	
	@AttributeDefinition(name = "Find Order API", description = "Find Order API")
	String findOrder();
	
	@AttributeDefinition(name = "Bundle Carousel API", description = "Bundle Carousel API")
	String bundleCarouselApi();
	
	@AttributeDefinition(name = "Bundle HeroBanner API", description = "Bundle HeroBanner API")
	String heroBannerApi();
	
	@AttributeDefinition(name = "Saved Offer API", description = "Saved Offer API")
	String saveOfferApi();
	
	@AttributeDefinition(name = "Offer By Status Api", description = "Offer By Status Api")
	String offerByStatusApi();
	
	@AttributeDefinition(name = "Offer Zone Api Cf Path", description = "Offer Zone Api Content Fragment")
	String offerZoneApiCf();
	
	@AttributeDefinition(name = "Update Bundle Offer API", description = "Update Bundle Offer API")
	String updateBundleUrl();

	@AttributeDefinition(name = "Brand Logo and Store Info Label", description = "Brand Logo and Store Info Lable Configuration CF Path.")
	String offerBrandLogoStorelabelConf();

	@AttributeDefinition(name = "Valid Channels", description = "Valid Channels Configuration CF Path")
	String offerValidChannelsConf();
	
	@AttributeDefinition(name = "Program IDs For Online Offline Based offers", description = "Program IDs")
	String[] programIdsForOnlineOfflineOffers();

	@AttributeDefinition(name = "Offer MSD Push event", description = "Offer MSD Push event")
	String offerMsdPushEvent();

	@AttributeDefinition(name = "Offers CTA MSD", description = "Offers CTA MSD")
	String offersCTAMSD();
	
	@AttributeDefinition(name = "Bundle APi", description = "Bundle APi")
	String bundleApi();
	
	@AttributeDefinition(name = "Enable Short URI", description = "Enable Short URI")
	boolean enableShortURI();

	@AttributeDefinition(name = "Bundle Cart Offers Url", description = "Bundle Cart Offers Url")
	String bundleOffersUrl();

	@AttributeDefinition(name = "Cart Bundles Url", description = "Cart Bundles Url")
	String cartBundlesUrl();

	@AttributeDefinition(name = "Delete Cart Selected Bundle Url", description = "Delete Cart Selected Bundle Url")
	String deleteSelectedBundleUrl();

	@AttributeDefinition(name = "Target API", description = "Target API")
	String targetApi();

	@AttributeDefinition(name = "Target Client", description = "Target Client")
	String targetClient();

	@AttributeDefinition(name = "Target Version", description = "Target Version")
	String targetVersion();
	
	@AttributeDefinition(name = "Fetch Customeroffer By Status API", description = "Fetch Customeroffer By Status API")
	String fetchCustOfferByStatusApi();
	
	@AttributeDefinition(name = "ServiceablityStatus Api", description = "ServiceAbilityStatus Api")
	String serviceabilityStatusApi();
	
	@AttributeDefinition(name = "Inventory Edd Status Api", description = "Inventory Edd Status Api ")
	String inventoryAndEddStatusApi();
	
	@AttributeDefinition(name = "Add To Cart Api", description = "Add To Cart Api")
	String addToCartApi();

	@AttributeDefinition(name = "SSO Client Id", description = "SSO Client Id")
	String ssoClientId();

	@AttributeDefinition(name = "SSO Client Secret", description = "SSO Client Secret")
	String ssoClientSecret();
	
	@AttributeDefinition(name = "SSO Ocp Token", description = "SSO Ocp Token")
	String ssoOcpToken();

	@AttributeDefinition(name = "Channel SDK Account Id", description = "Channel SDK Account Id")
	String channelSDKAccountId();

	@AttributeDefinition(name = "Channels Base URL", description = "Channels Base URL")
	String channelsBaseUrl();

	@AttributeDefinition(name = "Store Id", description = "Store Id")
	String storeId();

	@AttributeDefinition(name = "Croma Store Id", description = "Croma Store Id")
	String storeIdCroma();

	@AttributeDefinition(name = "IHCL Store Id", description = "IHCL Store Id")
	String storeIdIHCL();
	
	@AttributeDefinition(name = "MSD API CF path", description = "MSD API CF path")
	String msdApiCFPath();
	
	@AttributeDefinition(name = "Bundle Details Url", description = "Bundle Details Url")
	String bundleDetailsUrl();
	
	@AttributeDefinition(name = "Cart Checkout Url", description = "Cart Checkout Url")
	String cartCheckoutUrl();
	
	@AttributeDefinition(name = "Product Details Url", description = "Product Details Url")
	String productDetailsUrl();
	
	@AttributeDefinition(name = "Save To Vault Api", description = "Save To Vault Api")
	String saveToVaultApi();
	
	@AttributeDefinition(name = "Analytics Homepage Api", description = "Analytics Homepage Api")
	String analyticsHomepageAPI();
	
	@AttributeDefinition(name = "Author Host", description = "Author Host")
	String authorHost();
	
	@AttributeDefinition(name = "Authorization Header", description = "Authorization Header")
	String authorizationHeader();
	
	@AttributeDefinition(name = "Kyema Servicibility API", description = "Kyema Servicibility API")
	String keymaServiceabilityApi();
	
	@AttributeDefinition(name = "Brands CF Path", description = "Brands CF Path")
	String brandsCFPath();

	@AttributeDefinition(name = "Micro Segments API", description = "Micro Segments API")
	String microSegmentApi();
	
	@AttributeDefinition(name = "Micro Segments API HTTP Method", description = "Micro Segments API HTTP Method")
	String microSegmentApiHttpMethod();
	
	@AttributeDefinition(name = "Zone Offset", description = "Offset")
	String zoneOffset();
	
	@AttributeDefinition(name = "Days Offset", description = "Offset")
	String[] daysOffset();
	
	@AttributeDefinition(name = "Order Details Api CF", description = "Order Details Api CF")
	String orderDetailsApiConfig();

    @AttributeDefinition(name = "Segment Folder Path", description = "This is segment folder path. Offers pushed from OE will be in this folder.")
    String segmentFolderPath();
    @AttributeDefinition(name = "Offers Folder Path", description = "This is offers folder path. Offers pushed from OE will be in this folder.")
    String offersBaseFolderPath();
	
	@AttributeDefinition(name = "Image Quality", description = "Image Quality")
	long imageQuality();
	
	@AttributeDefinition(name = "PWA Desktop Host URL", description = "PWA Desktop Host URL")
	String pwaDesktopHostUrl();
	
	@AttributeDefinition(name = "Order History clientId", description = "Order History clientId")
    String ohClientId();
    
	@AttributeDefinition(name = "Order History Authorization", description = "Order History Authorization")
    String ohAuth();

	@AttributeDefinition(name = "BB Cancellable Conditions", description = "Cancellation Conditons For BigBasket Brand")
	String[] bbCancellableConditions();

	@AttributeDefinition(name = "TataCliq Status List Path", description = "TataCliq Status List Path")
	String cliqStatusList();

	@AttributeDefinition(name = "Croma VAS URL", description = "Croma site Host URL")
	String cromaVasUrl();

	@AttributeDefinition(name = "Order History Thread Pool Count", description = "Thread Pool Count For OrderHistory APIs")
	String orderHistoryThreadPoolCount();

	@AttributeDefinition(name = "BB fectch custumer clientid", description = "BB fectch custumer clientid")
	String bbClientId();

	@AttributeDefinition(name = "BB fectch custumer authorization", description = "BB fectch custumer authorization")
	String bbAuthorization();

	@AttributeDefinition(name = "BB fetch custumer API", description = "BB fetch custumer API")
	String bbFetchCustumerApi();

	@AttributeDefinition(name = "BB Status Title for Frontend", description = "BB Status Title for Frontend")
	String bbStatusTitlePath();

	@AttributeDefinition(name = "Croma Status Title for Frontend", description = "Croma Status Title for Frontend")
	String cromaStatusTitlePath();
	
	@AttributeDefinition(name = "Cart Count Api", description = "Cart Count Api") 
	String cartCountApi();

	@AttributeDefinition(name = "Order Cancellation CF path", description = "Order Cancellation CF path") 
	String orderCancellationCfPath();

	@AttributeDefinition(name = "Appkey for APP dynamics", description = "Appkey for APP dynamics") 
	String appKey();
	
	@AttributeDefinition(name = "Croma SWT ProgramId", description = "Croma SWT ProgramId")
	String cromaProgramId();

	@AttributeDefinition(name = "SSO Validation URL", description = "SSO Validation URL")
	String ssoValidateTockenUrl();
	
	@AttributeDefinition(name = "Croma Merchant Id", description = "Croma Merchant Id")
	String cromaMerchantId();
	
	@AttributeDefinition(name = "BB Merchant Id", description = "BB Merchant Id")
	String bbMerchantId();
	
	@AttributeDefinition(name = "Cliq Merchant Id", description = "Cliq Merchant Id")
	String cliqMerchantId();
	
	@AttributeDefinition(name = "Luxury Merchant Id", description = "Luxury Merchant Id")
	String luxuryMerchantId();
	
	@AttributeDefinition(name = "Target API For Location Model JSON", description = "Target API For Location Model JSON")
	String targetApiForLocationModelJson();
	
	@AttributeDefinition(name = "Partner Api Domain", description = "Partner Api Domain")
	String partnerApiDomain();
	
}