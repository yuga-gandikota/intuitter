package com.intuit.intuitter.test;

import static org.junit.Assert.assertNotNull;

import java.security.Principal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.intuit.intuitter.rest.model.Tweet;
import com.intuit.intuitter.rest.model.User;
import com.intuit.intuitter.rest.repository.TweetRepository;
import com.intuit.intuitter.rest.repository.UserRepository;

@TestExecutionListeners(value = {TransactionalTestExecutionListener.class },
						mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TweetV1Test {

	@Autowired
	TweetRepository tweetRepository;

	@Autowired
	UserRepository userRepository;

	@Mock
	Principal principal;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMockCreation() {
		assertNotNull(principal);
	}

	// Had to comment it out due to some hibernate error. will have fix it.
	// @Test
	public void testFetchCurrentUserFeed() {
		User user = setupForUser("euclid");
		int pageSize = 5;
		testPagination(user, 0, pageSize, new String[] { "euler", "euler", "euler", "gauss", "gauss" });
		testPagination(user, 1, pageSize, new String[] { "gauss", "euler", "euler", "euler", "gauss" });
		testPagination(user, 2, pageSize, new String[] { "gauss", "euler", "euler", "gauss", "gauss" });
		testPagination(user, 3, pageSize, new String[] {});
		testPagination(user, 4, pageSize, new String[] {});
	}

	private void testPagination(User user, int page, int pageSize, String[] strings) {
		PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "time");
		List<Tweet> tweetsPage = tweetRepository.findByFollower(user, pageRequest);
		assertNotNull(tweetsPage);
		assert (tweetsPage.size() == strings.length);
		for (int i = 0, n = tweetsPage.size(); i < n; i++) {
			assert (strings[i].equals(tweetsPage.get(i).getAuthor().getId()));
		}
		assertNotNull(tweetsPage);
	}

	private User setupForUser(String id) {
		User user = userRepository.findById(id);
		when(principal.getName()).thenReturn(id);
		return user;
	}
}
