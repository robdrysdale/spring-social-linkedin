package org.springframework.social.linkedin.api;

/**
 * Profile Field
 * 
 * @author Robert Drysdale
 *
 */
public enum ProfileField {
	ID,
	FIRST_NAME,
	LAST_NAME,
	HEADLINE,
	LOCATION,
	INDUSTRY,
	DISTANCE,
	RELATION_TO_VIEWER,
	CURRENT_SHARE,
	CONNECTIONS,
	NUM_CONNECTIONS,
	NUM_CONNECTIONS_CAPPED,
	SUMMARY,
	SPECIALTIES,
	PROPOSAL_COMMENTS,
	ASSOCIATIONS,
	HONORS,
	INTERESTS,
	POSITIONS,
	PUBLICATIONS,
	PATENTS,
	LANGUAGES,
	SKILLS,
	CERTIFICATIONS,
	EDUCATIONS,
	THREE_CURRENT_POSITIONS,
	THREE_PAST_POSITIONS,
	NUM_RECOMMENDERS,
	RECOMMENDATIONS_RECEIVED,
	PHONE_NUMBERS,
	IM_ACCOUNTS,
	TWITTER_ACCOUNTS,
	DATE_OF_BIRTH,
	MAIN_ADDRESS,
	MEMBER_URL_RESOURCES,
	PICTURE_URL,
	SITE_STANDARD_PROFILE_REQUEST_URL,
	API_PUBLIC_PROFILE_REQUEST_URL,
	SITE_PUBLIC_PROFILE_REQUEST_URL,
	API_STANDARD_PROFILE_REQUEST,
	PUBLIC_PROFILE_URL
	;
	
	public String toString() {
		switch (this) {
		case SITE_STANDARD_PROFILE_REQUEST_URL:
			return "site-standard-profile-request:(url)";
		case API_PUBLIC_PROFILE_REQUEST_URL:
			return "api-public-profile-request:(url)";
		case SITE_PUBLIC_PROFILE_REQUEST_URL:
			return "site-public-profile-request:(url)";
		default:
			return this.name().toLowerCase().replace('_', '-');
		}
	}
}
