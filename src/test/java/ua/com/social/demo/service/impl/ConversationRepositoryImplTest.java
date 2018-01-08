package ua.com.social.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.entity.impl.*;
import ua.com.social.demo.repository.impl.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class ConversationRepositoryImplTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository detailsRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    private Account account;
    private Profile profile;
    private ProfileDetails profileDetails;
    private Conversation firstConversation;
    private Conversation secondConversation;

    public ConversationRepositoryImplTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        this.profile = new Profile();
        this.profileDetails = new ProfileDetails("testName", "testLastNAme", Sex.male, 25);
        this.firstConversation = new Conversation();
        this.secondConversation = new Conversation();
    }

    @Test
    public void createAcc_createConversation_reviewConversation_reviewConversations() throws Exception {
        Integer accountId = accountRepository.persistAndRetrieveId(account);
        profile.setAccountId(accountId);
        profile.setOnlineStatus(0);
        Integer profileId = profileRepository.persistAndRetrieveId(profile);
        profileDetails.setProfileId(profileId);
        Integer profileDetailsId = detailsRepository.persistAndRetrieveId(profileDetails);
        ProfileDetails actualProfileDetails = detailsRepository.get(profileId);
        firstConversation.setProfileId(profileId);
        firstConversation.setCompanionId(1);
        secondConversation.setProfileId(profileId);
        secondConversation.setCompanionId(2);
        Integer conversationId = conversationRepository.persistAndRetrieveId(firstConversation);
        Integer secondConversationId = conversationRepository.persistAndRetrieveId(secondConversation);
        assertEquals(new Integer(1), conversationId);
        assertEquals(new Integer(2),secondConversationId);
        Conversation certainConversation = conversationRepository.getByProfileIdCompanionId(profileId, 1);
        Conversation certainSecondConversation = conversationRepository.getByProfileIdCompanionId(profileId, 2);
        assertEquals(firstConversation.getProfileId(), certainConversation.getProfileId());
        assertEquals(firstConversation.getCompanionId(), certainConversation.getCompanionId());
        assertEquals(secondConversation.getProfileId(), certainSecondConversation.getProfileId());
        assertEquals(secondConversation.getCompanionId(), certainSecondConversation.getCompanionId());
        List<Conversation> conversations = conversationRepository.getAll(profileId);
        assertEquals(2, conversations.size());//Some bug!
    }

}
