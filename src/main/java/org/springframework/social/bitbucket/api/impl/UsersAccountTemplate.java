package org.springframework.social.bitbucket.api.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.social.bitbucket.api.BitBucketEvent;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.bitbucket.api.UserWithRepositories;
import org.springframework.social.bitbucket.api.UsersAccountOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Cyprian Śniegota
 * @since 2.0.0
 */
public class UsersAccountTemplate extends AbstractBitBucketOperations implements UsersAccountOperations {
    public UsersAccountTemplate(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V1);
    }

    @Override
    public final UserWithRepositories getProfile(String accountName) {
        return getRestTemplate().getForObject(buildUrl("/users/{accountName}"), UserWithRepositories.class, accountName);
    }

    @Override
    public final long getPlan(String accountName) {
        return getRestTemplate().getForObject(buildUrl("/users/{accountName}/plan"), GetPlanResponseHolder.class, accountName).getCount();
    }

    @Override
    public final List<BitBucketUser> getFollowers(String accountName) {
        return getRestTemplate().getForObject(buildUrl("/users/{accountname}/followers"), FollowersResponseHolder.class, accountName).getFollowers();
    }

    @Override
    public final List<BitBucketEvent> getEvents(String accountName) {
        return getRestTemplate().getForObject(buildUrl("/users/{accountname}/events"), EventsResponseHolder.class, accountName).getEvents();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GetPlanResponseHolder {
        @JsonProperty @Getter
        private long count;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class FollowersResponseHolder {

        @JsonProperty @Getter
        private List<BitBucketUser> followers;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class EventsResponseHolder {

        @JsonProperty @Getter
        private List<BitBucketEvent> events;
    }
}
