package org.springframework.social.bitbucket.api.impl;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketSshKey;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Cyprian Śniegota
 * @since 2.0.0
 */
public class UsersSshKeysTemplateTest extends BaseTemplateTest {
    private static final String TEST_ACCOUNTNAME = "testaccount";
    private static final long TEST_KEYID = 171052L;
    private static final String TEST_KEY = "123123123";
    private static final String TEST_LABEL = "testgroupslug";
    @Test
    public void testGetKeys() throws Exception {
        //given
        mockServer.expect(requestTo("https://api.bitbucket.org/1.0/users/testaccount/ssh-keys")).andExpect(method(GET)).andRespond(
                withSuccess(jsonResource("get-keys"), MediaType.APPLICATION_JSON));
        //when
        List<BitBucketSshKey> result = bitBucket.usersOperations().usersSshKeysOperations().getKeys(TEST_ACCOUNTNAME);
        //then
        mockServer.verify();
        assertNotNull(result);
        assertEquals(1, result.size());
        BitBucketSshKey firstSshKey = result.iterator().next();
        assertEquals(TEST_KEYID, firstSshKey.getPk());
        assertEquals("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDMl/FZf5AtrJBth+8swfDfJrRWetHHnew/LTwX86OGdcG4sJWE5QpWzO9K+szpxaFmMF72"
                + "9bKAUBMBWNoPrYApayyalirpe7fjzHqIWoq5CsP/wKDVSyMxVOiBwBnXSukS7i9iOiC2J5PyEQwAq7GJXI3E2UWyymW7rVyaDdYKLH9PdUMNmLf"
                + "BpsDUyjdGO40pLjr6KCiyOTLI07Qy5iVz44VTRm6IBlxhee0DV3gw4GADHllSRVVOOngO+3453543sgfsfgsgsffgs3345345DFG346qi4WTeEC"
                + "B6JH87FhdCGS6mFyavpvOnrZdR9jGD auserbb", firstSshKey.getKey());
        assertEquals("home", firstSshKey.getLabel());
    }

    @Test
    public void testPostKey() throws Exception {
        assertTrue(false);
        //post-key
        //given
        //when
        BitBucketSshKey result = bitBucket.usersOperations().usersSshKeysOperations().postKey(TEST_ACCOUNTNAME, TEST_LABEL, TEST_KEY);
        //then
        mockServer.verify();
    }

    @Test
    public void testGetKey() throws Exception {
        //given
        mockServer.expect(requestTo("https://api.bitbucket.org/1.0/users/testaccount/ssh-keys/171052")).andExpect(method(GET)).andRespond(
                withSuccess(jsonResource("get-key"), MediaType.APPLICATION_JSON));
        //when
        BitBucketSshKey result = bitBucket.usersOperations().usersSshKeysOperations().getKey(TEST_ACCOUNTNAME, TEST_KEYID, TEST_LABEL);
        //then
        mockServer.verify();
        assertNotNull(result);
        assertEquals(171052L, result.getPk());
        assertEquals("home", result.getLabel());
        assertNotNull(result.getKey());
    }

    @Test
    public void testRemoveKey() throws Exception {
        //given
        mockServer.expect(requestTo("https://api.bitbucket.org/1.0/users/testaccount/ssh-keys/171052")).andExpect(method(DELETE)).andRespond(withNoContent());
        //when
        bitBucket.usersOperations().usersSshKeysOperations().removeKey(TEST_ACCOUNTNAME, TEST_KEYID);
        //then
        mockServer.verify();
    }
}