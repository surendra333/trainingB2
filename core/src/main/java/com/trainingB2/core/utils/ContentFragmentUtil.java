package com.trainingB2.core.utils;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author arunkumar.p This Util class is used extract data & variations from
 *         the Content fragment
 */
public final class ContentFragmentUtil {

	private ContentFragmentUtil() {
	}

	public static final JSONArray getVariatonsWithoutKeys(JSONObject data) throws JSONException {

		JSONObject contentVariations = data.getJSONObject(TB2Constants.CONTENT_FRAGMENT_VARIATIONS);
		JSONArray contentArray = new JSONArray();
		Iterator<?> keys = contentVariations.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			contentArray.put(contentVariations.getJSONObject(key));
		}
		return contentArray;
	}

	public static final JSONObject getData(JSONObject data) throws JSONException {

		return data.getJSONObject(PWAConstants.CONTENT_FRAGMENT_DATA);
	}

	public static final JSONObject getVariatonsWithKeys(JSONObject data) throws JSONException {

		return data.getJSONObject(TB2Constants.CONTENT_FRAGMENT_VARIATIONS);
	}

}

/*
 * public static final JSONObject getCTA(JSONObject data, String brandName,
 * String offerType, String cromaOfferTypes) throws JSONException {
 * 
 * JSONObject contentVariations =
 * data.getJSONObject(TB2Constants.CONTENT_FRAGMENT_VARIATIONS); Iterator<?>
 * keys = contentVariations.keys(); JSONObject ctaJObject = new JSONObject();
 * while (keys.hasNext()) { String key = (String) keys.next(); JSONObject cta =
 * contentVariations.getJSONObject(key); if
 * (brandName.equalsIgnoreCase(cta.getString(TB2Constants.PROGRAM_ID)) &&
 * cta.getString(TB2Constants.OFFER_TYPE).equals("") &&
 * !cta.getString(TB2Constants.PROGRAM_ID).equals(
 * "73eb6345-9cc9-4c37-a8e8-8620d6d32cf5")) { setCTA(ctaJObject, cta); break; }
 * else if (brandName.equalsIgnoreCase(cta.getString(TB2Constants.PROGRAM_ID))
 * && cta .getString(TB2Constants.PROGRAM_ID).equalsIgnoreCase(
 * "73eb6345-9cc9-4c37-a8e8-8620d6d32cf5")) { if
 * (cta.getString(TB2Constants.OFFER_TYPE).toLowerCase().contains(offerType.
 * toLowerCase())) { setCTA(ctaJObject, cta); break; } else if
 * (cta.getString(TB2Constants.OFFER_TYPE).equals("") &&
 * !cromaOfferTypes.toLowerCase().contains(offerType.toLowerCase())) {
 * setCTA(ctaJObject, cta); break; } }
 * 
 * }
 * 
 * return ctaJObject; }
 * 
 * public static final JSONObject getBundleCTA(JSONObject data, String
 * brandName, String offerType, String cromaOfferTypes) throws JSONException {
 * 
 * JSONObject contentVariations =
 * data.getJSONObject(TB2Constants.CONTENT_FRAGMENT_VARIATIONS); Iterator<?>
 * keys = contentVariations.keys(); JSONObject ctaJObject = new JSONObject();
 * while (keys.hasNext()) { String key = (String) keys.next(); JSONObject cta =
 * contentVariations.getJSONObject(key); if
 * (brandName.equalsIgnoreCase(cta.getString(TB2Constants.PROGRAM_ID)) &&
 * cta.getString(TB2Constants.OFFER_TYPE).equals("") &&
 * !cta.getString(TB2Constants.PROGRAM_ID).equals(
 * "01eac520-bc7a-1a10-8675-cf6516f1f134")) { setCTA(ctaJObject, cta); break; }
 * else if (brandName.equalsIgnoreCase(cta.getString(TB2Constants.PROGRAM_ID))
 * && cta .getString(TB2Constants.PROGRAM_ID).equalsIgnoreCase(
 * "01eac520-bc7a-1a10-8675-cf6516f1f134")) { if
 * (cta.getString(TB2Constants.OFFER_TYPE).toLowerCase().contains(offerType.
 * toLowerCase())) { setCTA(ctaJObject, cta); break; } else if
 * (cta.getString(TB2Constants.OFFER_TYPE).equals("") &&
 * !cromaOfferTypes.toLowerCase().contains(offerType.toLowerCase())) {
 * setCTA(ctaJObject, cta); break; } }
 * 
 * }
 * 
 * return ctaJObject; }
 * 
 * private static void setCTA(JSONObject ctaJObject, JSONObject cta) throws
 * JSONException { ctaJObject.put(TB2Constants.CTA_LABEL,
 * cta.getString(PWAConstants.CTA_LABEL)); ctaJObject.put(PWAConstants.CTA_TYPE,
 * cta.getString(PWAConstants.CTA_TYPE)); }
 * 
 * public static final JSONObject getOfferChannel(JSONObject data, JSONObject
 * storeslabel, JSONObject validChannelsMaster, List<String> programIds) throws
 * JSONException {
 * 
 * String offerChannel = data.getString(PWAConstants.OFFER_CHANNEL);
 * data.put(PWAConstants.OFFER_CHANNEL, new JSONArray(offerChannel));
 * 
 * // Sharability Details String sharabilityDetails =
 * data.getString(PWAConstants.SHARABILITY_DETAILS);
 * data.put(PWAConstants.SHARABILITY_DETAILS, new
 * JSONObject(sharabilityDetails));
 * 
 * data.put(PWAConstants.NEAR_BY_TEXT_LABEL,
 * ContentFragmentUtil.getVariatonsWithKeys(storeslabel)
 * .getJSONObject(data.getString(PWAConstants.STORES_INFO)).getString(
 * PWAConstants.STORES_INFO_LABEL));
 * 
 * JSONObject varWithKeys =
 * ContentFragmentUtil.getVariatonsWithKeys(validChannelsMaster); JSONObject
 * validChannelsList = null; String validChannelsLable =
 * ValidChannelsUtil.storeInfoIabel(data.getJSONArray(PWAConstants.OFFER_CHANNEL
 * )); if (StringUtils.isNotBlank(validChannelsLable) &&
 * varWithKeys.has(validChannelsLable)) { validChannelsList =
 * varWithKeys.getJSONObject(validChannelsLable); } else { if
 * (programIds.contains(data.getString(PWAConstants.PROGRAM_ID)) && varWithKeys
 * .has(data.getString(PWAConstants.PROGRAM_ID) + PWAConstants.UNDERSCORE +
 * validChannelsLable)) { validChannelsList = varWithKeys.getJSONObject(
 * data.getString(PWAConstants.PROGRAM_ID) + PWAConstants.UNDERSCORE +
 * validChannelsLable); } else { validChannelsList =
 * varWithKeys.has(data.getString(PWAConstants.PROGRAM_ID)) ?
 * varWithKeys.getJSONObject(data.getString(PWAConstants.PROGRAM_ID)) : new
 * JSONObject(); } } JSONObject offerChannels = new JSONObject();
 * offerChannels.put(PWAConstants.OFFER_CHANNEL_IDS,
 * data.getJSONArray(PWAConstants.OFFER_CHANNEL));
 * offerChannels.put(PWAConstants.DISPLAY_TEXT,
 * (validChannelsList.has(PWAConstants.CHANNELS_INFO_LABEL) ?
 * validChannelsList.getString(PWAConstants.CHANNELS_INFO_LABEL) : ""));
 * offerChannels.put(PWAConstants.CHANNEL_TYPE,
 * (validChannelsList.has(PWAConstants.CHANNELS_TYPE_LABEL) ?
 * validChannelsList.getString(PWAConstants.CHANNELS_TYPE_LABEL) : ""));
 * 
 * return offerChannels; }
 * 
 * public static final JSONObject getCTAMSD(JSONObject data, JSONObject
 * offerCTAJObj, JSONObject offerChannels) throws JSONException {
 * 
 * StringBuilder programIdChannelType = new StringBuilder();
 * programIdChannelType.append(data.getString(PWAConstants.PROGRAM_ID)).append(
 * PWAConstants.UNDERSCORE)
 * .append(offerChannels.get(PWAConstants.CHANNEL_TYPE));
 * 
 * JSONObject offerCTAKeysBasedonChannelTypes =
 * ContentFragmentUtil.getVariatonsWithKeys(offerCTAJObj); JSONObject
 * brandctaObject = new JSONObject(); if
 * (offerCTAKeysBasedonChannelTypes.has(programIdChannelType.toString().
 * toLowerCase())) { brandctaObject = offerCTAKeysBasedonChannelTypes
 * .getJSONObject(programIdChannelType.toString().toLowerCase()); }
 * 
 * // Cta Details JSONObject ctaObject = new JSONObject();
 * ctaObject.put(PWAConstants.CTA_LABEL,
 * brandctaObject.has(PWAConstants.CTA_LABEL) ?
 * brandctaObject.getString(PWAConstants.CTA_LABEL) : "");
 * ctaObject.put(PWAConstants.CTA_TYPE,
 * brandctaObject.has(PWAConstants.CTA_TYPE) ?
 * brandctaObject.getString(PWAConstants.CTA_TYPE) : "");
 * ctaObject.put(PWAConstants.CTA_URL, data.getString(PWAConstants.CTA_URL));
 * return ctaObject; } }
 */