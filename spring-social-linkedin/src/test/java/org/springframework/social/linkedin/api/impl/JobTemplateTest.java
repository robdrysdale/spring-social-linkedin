package org.springframework.social.linkedin.api.impl;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.social.test.client.RequestMatchers.body;
import static org.springframework.social.test.client.RequestMatchers.headerContains;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.linkedin.api.Job;
import org.springframework.social.linkedin.api.JobBookmark;
import org.springframework.social.linkedin.api.JobBookmarkResult;
import org.springframework.social.linkedin.api.JobPosition;
import org.springframework.social.linkedin.api.JobSearchParameters;

public class JobTemplateTest extends AbstractLinkedInApiTest {

	
	@Test
	public void search() {
		mockServer.expect(requestTo(JobTemplate.SEARCH_URL
				.replaceFirst("\\{\\&keywords\\}", "keywords=j2ee")
				.replaceFirst("\\{\\&company-name\\}", "")
				.replaceFirst("\\{\\&job-title\\}", "")
				.replaceFirst("\\{\\&country-code\\}", "&country-code=ie")
				.replaceFirst("\\{\\&postal-code\\}", "")
				.replaceFirst("\\{\\&distance\\}", "")
				.replaceFirst("\\{\\&start\\}", "&start=0")
				.replaceFirst("\\{\\&count\\}", "&count=10")
				.replaceFirst("\\{\\&sort\\}", ""))).andExpect(method(GET))
		.andRespond(withResponse(new ClassPathResource("testdata/job_search.json", getClass()), responseHeaders));
		JobSearchParameters parameters = new JobSearchParameters();
		parameters.setCountryCode("ie");
		parameters.setKeywords("j2ee");
		List<Job> jobs = linkedIn.jobOperations().searchJobs(parameters).getJobs();
		
		assertEquals(8, jobs.size());
		Job j = jobs.get(0);
		assertEquals(true, j.isActive());
		assertEquals(139355, j.getCompany().getId());
		assertEquals("Allen Recruitment Consulting", j.getCompany().getName());
		assertEquals("5404", j.getCustomerJobCode());
		assertEquals(2755, j.getDescription().length());
		assertEquals(400, j.getDescriptionSnippet().length());
		assertLinkedInDate(j.getExpirationDate(), 2012, 2, 6);
		assertEquals(new Date(1328549338000l), j.getExpirationTimestamp());
		assertEquals(2160963, j.getId());
		assertProfile(j.getJobPoster(), "U1Gll9nr47", "Director, Allen Recruitment Consulting - Recruitment", "Louise", "Allen (Louise@AllenRec.com)",null, "");
		assertEquals("Dublin", j.getLocationDescription());
		assertLinkedInDate(j.getPostingDate(), 2011, 11, 8);
		assertEquals(new Date(1320773338000l), j.getPostingTimestamp());
		assertEquals("60 - 70K or 350-400/day (EURO)", j.getSalary());
		assertEquals("http://www.linkedin.com/jobs?viewJob=&jobId=2160963&trk=api*a151944*s160233*", j.getSiteJobUrl());
		assertEquals(494, j.getSkillsAndExperience().length());
		
		JobPosition p = j.getPosition();
		assertEquals("4", p.getExperienceLevel().getCode());
		assertEquals("Mid-Senior level", p.getExperienceLevel().getName());
		assertEquals("4", p.getIndustries().get(0).getCode());
		assertEquals("Computer Software", p.getIndustries().get(0).getName());
		assertEquals("it", p.getJobFunctions().get(0).getCode());
		assertEquals("Information Technology", p.getJobFunctions().get(0).getName());
		assertEquals("F", p.getJobType().getCode());
		assertEquals("Full-time", p.getJobType().getName());
		assertEquals("ie", p.getLocation().getCountry());
		assertEquals("Ireland", p.getLocation().getName());
		assertEquals("Java Developer - GWT (Perm or Contract) \u2013 Dublin, Ireland", p.getTitle());
	}
	
	@Test
	public void getSuggestions() {
		mockServer.expect(requestTo(JobTemplate.SUGGESTED_URL
				.replaceFirst("\\{\\&start\\}", "start=0")
				.replaceFirst("\\{\\&count\\}", "&count=10"))).andExpect(method(GET))
		.andRespond(withResponse(new ClassPathResource("testdata/job_suggestions.json", getClass()), responseHeaders));
		
		List<Job> jobs = linkedIn.jobOperations().getSuggestions(0, 10).getJobs();
		
		assertEquals(10, jobs.size());
		Job j = jobs.get(6);
		assertEquals(true, j.isActive());
		assertEquals(139355, j.getCompany().getId());
		assertEquals("Allen Recruitment Consulting", j.getCompany().getName());
		assertEquals("5404", j.getCustomerJobCode());
		assertEquals(2755, j.getDescription().length());
		assertEquals(400, j.getDescriptionSnippet().length());
		assertLinkedInDate(j.getExpirationDate(), 2012, 2, 6);
		assertEquals(new Date(1328549338000l), j.getExpirationTimestamp());
		assertEquals(2160963, j.getId());
		assertProfile(j.getJobPoster(), "U1Gll9nr47", "Director, Allen Recruitment Consulting - Recruitment", "Louise", "Allen (Louise@AllenRec.com)",null, "");
		assertEquals("Dublin", j.getLocationDescription());
		assertLinkedInDate(j.getPostingDate(), 2011, 11, 8);
		assertEquals(new Date(1320773338000l), j.getPostingTimestamp());
		assertEquals("60 - 70K or 350-400/day (EURO)", j.getSalary());
		assertEquals("http://www.linkedin.com/jobs?viewJob=&jobId=2160963&trk=api*a151944*s160233*", j.getSiteJobUrl());
		assertEquals(494, j.getSkillsAndExperience().length());
		
		JobPosition p = j.getPosition();
		assertEquals("4", p.getExperienceLevel().getCode());
		assertEquals("Mid-Senior level", p.getExperienceLevel().getName());
		assertEquals("4", p.getIndustries().get(0).getCode());
		assertEquals("Computer Software", p.getIndustries().get(0).getName());
		assertEquals("it", p.getJobFunctions().get(0).getCode());
		assertEquals("Information Technology", p.getJobFunctions().get(0).getName());
		assertEquals("F", p.getJobType().getCode());
		assertEquals("Full-time", p.getJobType().getName());
		assertEquals("ie", p.getLocation().getCountry());
		assertEquals("Ireland", p.getLocation().getName());
		assertEquals("Java Developer - GWT (Perm or Contract) \u2013 Dublin, Ireland", p.getTitle());
	}
	
	@Test
	public void getJob() {
		mockServer.expect(requestTo(JobTemplate.JOB_URL
				.replaceFirst("\\{id\\}", "2160963"))).andExpect(method(GET))
		.andRespond(withResponse(new ClassPathResource("testdata/job.json", getClass()), responseHeaders));
		JobSearchParameters parameters = new JobSearchParameters();
		parameters.setCountryCode("ie");
		parameters.setKeywords("j2ee");
		
		Job j = linkedIn.jobOperations().getJob(2160963);
		
		assertEquals(true, j.isActive());
		assertEquals(139355, j.getCompany().getId());
		assertEquals("Allen Recruitment Consulting", j.getCompany().getName());
		assertEquals("5404", j.getCustomerJobCode());
		assertEquals(2755, j.getDescription().length());
		assertEquals(400, j.getDescriptionSnippet().length());
		assertLinkedInDate(j.getExpirationDate(), 2012, 2, 6);
		assertEquals(new Date(1328549338000l), j.getExpirationTimestamp());
		assertEquals(2160963, j.getId());
		assertProfile(j.getJobPoster(), "U1Gll9nr47", "Director, Allen Recruitment Consulting - Recruitment", "Louise", "Allen (Louise@AllenRec.com)",null, "");
		assertEquals("Dublin", j.getLocationDescription());
		assertLinkedInDate(j.getPostingDate(), 2011, 11, 8);
		assertEquals(new Date(1320773338000l), j.getPostingTimestamp());
		assertEquals("60 - 70K or 350-400/day (EURO)", j.getSalary());
		assertEquals("http://www.linkedin.com/jobs?viewJob=&jobId=2160963&trk=api*a151944*s160233*", j.getSiteJobUrl());
		assertEquals(494, j.getSkillsAndExperience().length());
		
		JobPosition p = j.getPosition();
		assertEquals("4", p.getExperienceLevel().getCode());
		assertEquals("Mid-Senior level", p.getExperienceLevel().getName());
		assertEquals("4", p.getIndustries().get(0).getCode());
		assertEquals("Computer Software", p.getIndustries().get(0).getName());
		assertEquals("it", p.getJobFunctions().get(0).getCode());
		assertEquals("Information Technology", p.getJobFunctions().get(0).getName());
		assertEquals("F", p.getJobType().getCode());
		assertEquals("Full-time", p.getJobType().getName());
		assertEquals("ie", p.getLocation().getCountry());
		assertEquals("Ireland", p.getLocation().getName());
		assertEquals("Java Developer - GWT (Perm or Contract) \u2013 Dublin, Ireland", p.getTitle());
	}
	
	@Test
	public void getBookmarks() {
		mockServer.expect(requestTo(JobTemplate.BOOKMARKS_URL
				.replaceFirst("\\{\\&start\\}", "start=0")
				.replaceFirst("\\{\\&count\\}", "&count=10"))).andExpect(method(GET))
		.andRespond(withResponse(new ClassPathResource("testdata/job_bookmarks.json", getClass()), responseHeaders));
		
		JobBookmarkResult r = linkedIn.jobOperations().getBookmarks(0, 10);
		assertEquals(0,r.getCount());
		assertEquals(0,r.getStart());
		assertEquals(1,r.getTotal());
		
		JobBookmark b = r.getJobBookmarks().get(0);
		assertEquals(false, b.isApplied());
		assertEquals(true, b.isSaved());
		assertEquals(new Date(1322150498000l), b.getSavedTimestamp());
		
		Job j = b.getJob();
		assertEquals(true, j.isActive());
		assertEquals(139355, j.getCompany().getId());
		assertEquals("Allen Recruitment Consulting", j.getCompany().getName());
		assertEquals(2160963, j.getId());
		assertEquals("Java Developer - GWT (Perm or Contract) \u2013 Dublin, Ireland", j.getPosition().getTitle());
		assertEquals(new Date(1320773338000l), j.getPostingTimestamp());
		assertEquals("Our Client has an excellent opportunity for a number Java GWT Developers. You will be based out of Dublin City Centre office. Overseas candidates are welcome to apply, but ideally you should be currently eligible to work in the EU / Ireland � Responsibilities: Ownership of the design and the implementation (estimation, breakdown of tasks) for complex business functional specifications through the ", j.getDescriptionSnippet());
	}
	
	@Test
	public void bookmark() {
		mockServer.expect(requestTo(JobTemplate.BOOKMARK_URL))
		.andExpect(method(POST))
		.andExpect(body("{\"job\":{\"id\":123456}}"))
		.andExpect(headerContains("Authorization", "OAuth oauth_version=\"1.0\", oauth_nonce=\""))
		.andExpect(headerContains("Authorization", "oauth_signature_method=\"HMAC-SHA1\", oauth_consumer_key=\"API_KEY\", oauth_token=\"ACCESS_TOKEN\", oauth_timestamp=\""))
		.andExpect(headerContains("Authorization", "oauth_signature=\""))
		.andRespond(withResponse("", responseHeaders));
		
		linkedIn.jobOperations().bookmarkJob(123456);
	}
	
	@Test
	public void unbookmark() {
		mockServer.expect(requestTo(JobTemplate.UNBOOKMARK_URL.replaceFirst("\\{job-id\\}", "123456")))
		.andExpect(method(DELETE))
		.andExpect(body(""))
		.andExpect(headerContains("Authorization", "OAuth oauth_version=\"1.0\", oauth_nonce=\""))
		.andExpect(headerContains("Authorization", "oauth_signature_method=\"HMAC-SHA1\", oauth_consumer_key=\"API_KEY\", oauth_token=\"ACCESS_TOKEN\", oauth_timestamp=\""))
		.andExpect(headerContains("Authorization", "oauth_signature=\""))
		.andRespond(withResponse("", responseHeaders));
		
		linkedIn.jobOperations().unbookmarkJob(123456);
	}

}
