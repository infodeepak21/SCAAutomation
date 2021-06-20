package jiraServices;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {
	
	public JiraClient jiraClient;
	public String project;
	
	public JiraServiceProvider(String url , String userName , String password , String project) {
		BasicCredentials credentials = new BasicCredentials(userName, password);
		jiraClient = new JiraClient(url , credentials);
		this.project = project;
	}

	public void createJiraTicket(String issueType, String summary , String description , String reporterName) {
		try {
			FluentCreate create = jiraClient.createIssue(project, issueType);
			create.field(Field.SUMMARY, summary);
			create.field(Field.DESCRIPTION, description);
			create.field(Field.REPORTER, reporterName);
			Issue issue = create.execute();
			System.out.println("New Jira Ticket created with Jira ID " + issue);
		} catch (JiraException e) {
			e.printStackTrace();
		}
		
	}
	
}
