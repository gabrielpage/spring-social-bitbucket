/**
 * Copyright (C) 2012 Eric Bottard (eric.bottard+ghpublic@gmail.com)
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Container class that holds basic information about an account as well as a
 * list of repositories.
 *
 * @author Eric Bottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWithRepositories {

    @JsonProperty
    private BitBucketUser user;

    @JsonProperty
    private List<BitBucketRepository> repositories;

    public List<BitBucketRepository> getRepositories() {
        return repositories;
    }

    public BitBucketUser getUser() {
        return user;
    }

}