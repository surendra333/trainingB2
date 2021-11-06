package com.trainingB2.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;


import com.trainingB2.core.services.OSGIConfigService;
import com.trainingB2.core.services.PWAOsgiConfiguration;

/**
 * 
 * @author arunkumar.p
 *
 */
@Component(service = OSGIConfigService.class, configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true)
@Designate(ocd = PWAOsgiConfiguration.class)
public class BaseOSGIConfigService implements OSGIConfigService {

	private PWAOsgiConfiguration pwaOsgiConfiguration;

	@Activate
	protected void activate(PWAOsgiConfiguration pwaOsgiConfiguration) {
		this.pwaOsgiConfiguration = pwaOsgiConfiguration;
	}


	@Override
	public String getURL() {
		return pwaOsgiConfiguration.url();
	}

	@Override
	public String getApiConfigCfPath() {
		return pwaOsgiConfiguration.apiConfigCfPath();
	}


	@Override
	public String getOfferCtaConf() {
		return pwaOsgiConfiguration.offerCtaConf();
	}


	@Override
	public String getCromaOfferType() {
		return pwaOsgiConfiguration.cromaOfferTypes();
	}
	
	@Override
	public String getPopularOffer() {
		return pwaOsgiConfiguration.popularoffer();
	}
	
	@Override
	public String getOffersAndStoresNearMe() {
		return pwaOsgiConfiguration.offersAndStoresNearMe();
	}
	
	@Override
	public String getRecentAndTrendingOffers() {
		return pwaOsgiConfiguration.recentAndTrendingOffers();
	}


	@Override
	public String getApiDomain() {
		return pwaOsgiConfiguration.apiDomain();
	}


	@Override
	public String getBundleOffers() {
		return pwaOsgiConfiguration.bundleOffers();
	}


	@Override
	public String getBundleDetails() {
		return  pwaOsgiConfiguration.bundleDetails();
	}


	@Override
	public String getCart() {
		return pwaOsgiConfiguration.cartApi();
	}


	@Override
	public String cancelOrder() {
		return pwaOsgiConfiguration.cancelOrder();
	}


	@Override
	public String findOrder() {
		return pwaOsgiConfiguration.findOrder();
	}

	@Override
	public String getBundleCarouselApi() {
		return pwaOsgiConfiguration.bundleCarouselApi();
	}
	
	@Override
	public String getBundleHeroBannerApi() {
		return pwaOsgiConfiguration.heroBannerApi();
	}
	
	@Override
	public String getSavedOfferApi() {
		return pwaOsgiConfiguration.saveOfferApi();
	}

	@Override
	public String getOfferByStatusApi() {
		return pwaOsgiConfiguration.offerByStatusApi();
	}


	@Override
	public String getOfferZoneApiCfPath() {
		return pwaOsgiConfiguration.offerZoneApiCf();
	}


	@Override
	public String getUpdateBundleApi() {
		return pwaOsgiConfiguration.updateBundleUrl();
	}

	@Override
	public String getOfferBrandLogoStorelabelConf() {
		return pwaOsgiConfiguration.offerBrandLogoStorelabelConf();
	}

	@Override
	public String getOfferValidChannelsConf() {
		return pwaOsgiConfiguration.offerValidChannelsConf();
	}

	@Override
	public String[] getProgramIdsForOnlineOfflineOffers() {
		return pwaOsgiConfiguration.programIdsForOnlineOfflineOffers();
	}

	@Override
	public String getOfferMsdPushEvent() {
		return pwaOsgiConfiguration.offerMsdPushEvent();
	}

	@Override
	public String getOffersCTAMSD() {
		return pwaOsgiConfiguration.offersCTAMSD();
	}
	@Override
	public String getBundleApi() {
		return pwaOsgiConfiguration.bundleApi();
	}


	@Override
	public boolean getEnableShortURI() {
		return pwaOsgiConfiguration.enableShortURI();
	}
	
	@Override
	public String getBundleOffersUrl() {
		return pwaOsgiConfiguration.bundleOffersUrl();
	}
	
	@Override
	public String getCartBundlesUrlCart() {
		return pwaOsgiConfiguration.cartBundlesUrl();
	}
	
	@Override
	public String getDeleteSelectedBundleUrl() {
		return pwaOsgiConfiguration.deleteSelectedBundleUrl();
	}

	@Override
	public String getTargetClient() {
		return pwaOsgiConfiguration.targetClient();
	}


	@Override
	public String getTargetApi() {
		return pwaOsgiConfiguration.targetApi();
	}

	@Override
	public String getTargetVersion() {
		return pwaOsgiConfiguration.targetVersion();
	}


	@Override
	public String getFetchCustOfferByStatusApi() {
		return pwaOsgiConfiguration.fetchCustOfferByStatusApi();
	}

	@Override
	public String getServiceabilityStatusApi() {
		return pwaOsgiConfiguration.serviceabilityStatusApi();
	}


	@Override
	public String getInventoryAndEddStatusApi() {
		return pwaOsgiConfiguration.inventoryAndEddStatusApi();
	}
	
	@Override
	public String getAddToCartApi() {
		return pwaOsgiConfiguration.addToCartApi();
	}
	@Override
	public String getSSOClientId() {
		return pwaOsgiConfiguration.ssoClientId();
	}


	@Override
	public String getSSOClientSecret() {
		return pwaOsgiConfiguration.ssoClientSecret();
	}


	@Override
	public String getSSOOcpToken() {
		return pwaOsgiConfiguration.ssoOcpToken();
	}


	@Override
	public String getChannelSDKAccountId() {
		return pwaOsgiConfiguration.channelSDKAccountId();
	}


	@Override
	public String getChannelsBaseUrl() {
		return pwaOsgiConfiguration.channelsBaseUrl();
	}


	@Override
	public String getStoreId() {
		return pwaOsgiConfiguration.storeId();
	}


	@Override
	public String getStoreIdCroma() {
		return pwaOsgiConfiguration.storeIdCroma();
	}


	@Override
	public String getStoreIdIHCLd() {
		return pwaOsgiConfiguration.storeIdIHCL();
	}


	@Override
	public String getMsdApiCFPath() {
		return pwaOsgiConfiguration.msdApiCFPath();
	}

	@Override
	public String getBundleDetailsUrl() {
		return pwaOsgiConfiguration.bundleDetailsUrl();
	}


	@Override
	public String getCartCheckoutUrl() {
		return pwaOsgiConfiguration.cartCheckoutUrl();
	}
	
	@Override
	public String getProductDetailsUrl() {
		return pwaOsgiConfiguration.productDetailsUrl();
	}
	
	@Override
	public String getSaveToVaultApi() {
		return pwaOsgiConfiguration.saveToVaultApi();
	}

	@Override
	public String getAnalyticsHomepageAPI() {
		return pwaOsgiConfiguration.analyticsHomepageAPI();
	}

	@Override
	public String getAuthorHost() {
		return pwaOsgiConfiguration.authorHost();
	}

	@Override
	public String getAuthorizationHeader() {
		return pwaOsgiConfiguration.authorizationHeader();
	}


	@Override
	public String getKeymaServiceabilityApi() {		
		return pwaOsgiConfiguration.keymaServiceabilityApi();
	}
	
	@Override
	public String getBrandsCFPath() {		
		return pwaOsgiConfiguration.brandsCFPath();
	}
	
	public String getMicroSegmentApi() {		
		return pwaOsgiConfiguration.microSegmentApi();
	}
	
	@Override
	public String getMicroSegmentApiHttpMethod() {		
		return pwaOsgiConfiguration.microSegmentApiHttpMethod();
	}
	
	@Override
	public String getZoneOffset() {		
		return pwaOsgiConfiguration.zoneOffset();
	}
	
	@Override
	public String[] getDaysOffset() {		
		return pwaOsgiConfiguration.daysOffset();
	}
	
	@Override
	public String getOrderDetailsApiCF() {
		return pwaOsgiConfiguration.orderDetailsApiConfig();
	}
	
	@Override
	public String getSegmentFolderPath() {
		return pwaOsgiConfiguration.segmentFolderPath();
	}
	
	@Override
	public String getOffersBaseFolderPath() {
		return pwaOsgiConfiguration.offersBaseFolderPath();

	}
	@Override
	public long getImageQuality() {
		return pwaOsgiConfiguration.imageQuality();
	}
	
	@Override
	public String getPwaDesktopHostUrl() {
		return pwaOsgiConfiguration.pwaDesktopHostUrl();
	}


	@Override
	public String ohClientId() {
		return pwaOsgiConfiguration.ohClientId();
	}


	@Override
	public String ohAuth() {
		return pwaOsgiConfiguration.ohAuth();
	}

	@Override
	public String[] getBbCancellableConditions() {
		return pwaOsgiConfiguration.bbCancellableConditions();
	}


	@Override
	public String getCliqStatusList() {
		return pwaOsgiConfiguration.cliqStatusList();
	}


	@Override
	public String getCromaVasUrl() {
		return pwaOsgiConfiguration.cromaVasUrl();
	}


	@Override
	public String getOrderHistoryThreadPoolCount() {
		return pwaOsgiConfiguration.orderHistoryThreadPoolCount();
	}


	@Override
	public String getBbClientId() {
		return pwaOsgiConfiguration.bbClientId();
	}


	@Override
	public String getBbAuthorization() {		
		return pwaOsgiConfiguration.bbAuthorization();
	}


	@Override
	public String getBbFetchCustumerApi() {
		return pwaOsgiConfiguration.bbFetchCustumerApi();
	}


	@Override
	public String getBbStatusTitlePath() {
		return pwaOsgiConfiguration.bbStatusTitlePath();
	}


	@Override
	public String getCromaStatusTitlePath() {
		return pwaOsgiConfiguration.cromaStatusTitlePath();
	}
	
	
	@Override
	public String getCartCountApi() {
		return pwaOsgiConfiguration.cartCountApi();
	}


	@Override
	public String getOrderCancellationCfPath() {
		return pwaOsgiConfiguration.orderCancellationCfPath();
	}


	@Override
	public String getAppKey() {
		return pwaOsgiConfiguration.appKey();
	}


	@Override
	public String getCromaProgramId() {
		return pwaOsgiConfiguration.cromaProgramId();
	}


	@Override
	public String getSsoValidateTockenUrl() {
		return pwaOsgiConfiguration.ssoValidateTockenUrl();
	}
	
	@Override
	public String getCromaMerchantId() {
		return pwaOsgiConfiguration.cromaMerchantId();
	}


	@Override
	public String getBBMerchantId() {
		return pwaOsgiConfiguration.bbMerchantId();
	}


	@Override
	public String getTataCliqMerchantId() {
		return pwaOsgiConfiguration.cliqMerchantId();
	}


	@Override
	public String getLuxuryMerchantId() {
		return pwaOsgiConfiguration.luxuryMerchantId();
	}


	@Override
	public String getCsPartnerApiDomain() {
		return pwaOsgiConfiguration.partnerApiDomain();
	}

	@Override
	public String getTargetApiForLocationModelJson() {
		return pwaOsgiConfiguration.targetApiForLocationModelJson();
	}
}
