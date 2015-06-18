/**
 * Copyright (C) 2012 Eric Bottard / Guillaume Lederrey (eric.bottard+ghpublic@gmail.com / guillaume.lederrey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.bitbucket.api;

import java.util.List;

/**
 * emails Resource
 * @see "https://confluence.atlassian.com/display/BITBUCKET/emails+Resource"
 *
 * @author Cyprian Śniegota
 * @since 2.0.0
 */
public interface EmailsOperations {

    /**
     * Gets the email addresses associated with the account.
     *
     * @param accountName The name of an individual or team account.
     * @return list of email addresses
     */
    List<BitBucketEmailAddress> getListOfUserEmailAddresses(String accountName);

    /**
     * Gets an individual email address associated with an account.
     *
     * @param accountName The name of an individual or team account.
     * @param emailAddress The email address to get.
     * @return email address
     */
    BitBucketEmailAddress getAnEmailAddress(String accountName, String emailAddress);

    /**
     * Adds additional email addresses to an account.
     *
     * @param accountName The name of an individual or team account.
     * @param emailAddress The email address to post.
     * @return list of email addresses
     */
    List<BitBucketEmailAddress> postANewEmailAddress(String accountName, String emailAddress);

    /**
     * Sets an individual email address associated with an account to primary.
     * The primary email address is the main email contact for the account.
     * Only a single address on an account can be primary.
     * If another address had primary set prior to this call, after it is no longer primary.
     *
     * @param accountName The name of an individual or team account.
     * @param emailAddress The email address to modify.
     * @return modified email address
     */
    BitBucketEmailAddress updateAnEmailAddress(String accountName, String emailAddress);

}

/*- GET a list of user's email addresses
GET https://bitbucket.org/api/1.0/users/{accountname}/emails
- GET an email address
GET https://bitbucket.org/api/1.0/users/{accountname}/emails/{email_address}
- POST a new email address
POST https://bitbucket.org/api/1.0/users/{accountname}/emails/{email_address} --data"email=value"
- Update an email address
PUT https://bitbucket.org/api/1.0/users/{accountname}/emails/{email_address} --data "primary=true"

X1. create interface
X2. update javadoc (description + url from docs)
X3. model (if necessary)
X4. create empty implementation
X5. optional - connect new operations class to base template
6. create tests
7. template implementation
8. file headers
9. real test (on connection with all permissions)
*/