/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 **/
@TypeDefs({ @TypeDef(name = "ApiKeyType", typeClass = ApiKeyType.class),
		@TypeDef(name = "AsyncServiceInstanceType", typeClass = AsyncServiceInstanceType.class),
		@TypeDef(name = "DataResourceType", typeClass = DataResourceType.class),
		@TypeDef(name = "DeploymentGroupType", typeClass = DeploymentGroupType.class),
		@TypeDef(name = "DeploymentType", typeClass = DeploymentType.class), @TypeDef(name = "JobType", typeClass = JobType.class),
		@TypeDef(name = "LeaseType", typeClass = LeaseType.class), @TypeDef(name = "ServiceJobType", typeClass = ServiceJobType.class),
		@TypeDef(name = "ServiceType", typeClass = ServiceType.class),
		@TypeDef(name = "UserProfileType", typeClass = UserProfileType.class),
		@TypeDef(name = "UserThrottlesType", typeClass = UserThrottlesType.class) })

package org.venice.piazza.common.hibernate.util;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.venice.piazza.common.hibernate.usertype.ApiKeyType;
import org.venice.piazza.common.hibernate.usertype.AsyncServiceInstanceType;
import org.venice.piazza.common.hibernate.usertype.DataResourceType;
import org.venice.piazza.common.hibernate.usertype.DeploymentGroupType;
import org.venice.piazza.common.hibernate.usertype.DeploymentType;
import org.venice.piazza.common.hibernate.usertype.JobType;
import org.venice.piazza.common.hibernate.usertype.LeaseType;
import org.venice.piazza.common.hibernate.usertype.ServiceJobType;
import org.venice.piazza.common.hibernate.usertype.ServiceType;
import org.venice.piazza.common.hibernate.usertype.UserProfileType;
import org.venice.piazza.common.hibernate.usertype.UserThrottlesType;
