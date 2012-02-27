package org.springframework.social.bitbucket.connect;

import org.springframework.social.ApiException;
import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

public class BitBucketAdapter implements ApiAdapter<BitBucket> {

    @Override
    public boolean test(BitBucket api) {
        try {
            if (null != api.userOperations().getUserWithRepositories()) {
                return true;
            } else {
                return false;
            }
        } catch (ApiException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(BitBucket api, ConnectionValues values) {
        BitBucketUser user = api.userOperations().getUserWithRepositories()
                .getUser();
        values.setImageUrl(user.getAvatarImageUrl());
        values.setProviderUserId(user.getUsername());
        values.setDisplayName(user.getFirstName() + " " + user.getLastName());
        values.setProfileUrl("https://bitbucket.org/" + user.getUsername());
    }

    @Override
    public UserProfile fetchUserProfile(BitBucket api) {
        BitBucketUser user = api.userOperations().getUserWithRepositories()
                .getUser();
        return new UserProfileBuilder().setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUsername(user.getUsername()).build();
    }

    @Override
    public void updateStatus(BitBucket api, String message) {
        // NOOP
    }

}
