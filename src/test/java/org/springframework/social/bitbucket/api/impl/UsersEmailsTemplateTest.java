/**
 * Copyright (C) 2012 Eric Bottard / Guillaume Lederrey (eric.bottard+ghpublic@gmail.com / guillaume.lederrey@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.bitbucket.api.impl;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketEmailAddress;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Cyprian Śniegota
 * @since 2.0.0
 */
public class UsersEmailsTemplateTest extends BaseTemplateTest {

    @Test
    public void testGetListOfUserEmailAddresses() throws Exception {
        //given
        mockServer.expect(requestTo("https://api.bitbucket.org/1.0/users/sampleuser/emails"))
                .andExpect(method(GET)).andRespond(withSuccess(jsonResource("get-list-of-users-email-addresses"),
                MediaType.APPLICATION_JSON));
        //when
        List<BitBucketEmailAddress> listOfUserEmailAddresses = bitBucket.usersOperations().usersEmailsOperations()
                .getEmailAddresses("sampleuser");
        //then
        mockServer.verify();
        assertEquals(2, listOfUserEmailAddresses.size());
        BitBucketEmailAddress firstEmailAddress = listOfUserEmailAddresses.iterator().next();
        assertEquals("2team.bb@gmail.com", firstEmailAddress.getEmail());
        assertEquals(true, firstEmailAddress.getActive());
        assertEquals(true, firstEmailAddress.getPrimary());
    }

    @Test
    public void testGetAnEmailAddress() throws Exception {
        //given
        mockServer.expect(requestTo("https://api.bitbucket.org/1.0/users/sampleuser/emails/ourteam@gmail.com"))
                .andExpect(method(GET)).andRespond(withSuccess(jsonResource("get-an-email-address"),
                MediaType.APPLICATION_JSON));
        //when
        BitBucketEmailAddress sampleuser = bitBucket.usersOperations().usersEmailsOperations()
                .getEmailAddress("sampleuser", "ourteam@gmail.com");
        //then
        mockServer.verify();
        assertEquals("ourteam@gmail.com", sampleuser.getEmail());
        assertEquals(false, sampleuser.getActive());
        assertEquals(false, sampleuser.getPrimary());
    }

    @Test
    public void testPostNewEmailAddress() throws Exception {
        assertTrue(false);
        //post-new-email-address
        //given
        //when
        //then
    }

    @Test
    public void testUpdateEmailAddress() throws Exception {
        assertTrue(false);
        //update-email-address
        //given
        //when
        //then
    }
}