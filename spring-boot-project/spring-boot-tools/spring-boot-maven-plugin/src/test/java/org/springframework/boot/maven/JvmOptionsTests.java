/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.maven;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.util.ReflectionUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JvmOptionsTests
	extends AbstractMojoTestCase {

	@Override
	protected String getPluginDescriptorLocation() {
		// Need to override because pom version is not resolved properly by harness plugin.
		return "plugin-descriptor.xml";
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testParseJvmOptions() throws Exception {

		URL url = getClass().getResource("/pom_examples/jvm-args-1.xml");

		RunMojo mojo = (RunMojo) lookupMojo("run", new File(url.toURI()));
		assertThat(mojo).isNotNull();

		List<String> jvmOptionsValues = (List<String>) ReflectionUtils
				.getValueIncludingSuperclasses("jvmOptions", mojo);
		assertThat(jvmOptionsValues.size()).isEqualTo(2);

		List<SystemProperty> systemPropertiesValues = (List<SystemProperty>) ReflectionUtils
				.getValueIncludingSuperclasses("systemProperties", mojo);
		assertThat(systemPropertiesValues.size()).isEqualTo(3);

		assertThat(systemPropertiesValues.get(0).asString()).isEqualTo("-DsimpleProp=simple value");
		assertThat(systemPropertiesValues.get(1).asString()).isEqualTo("-DnoValueProp");
		assertThat(systemPropertiesValues.get(2).asString()).isEqualTo("-DemptyValueProp");
	}

}
