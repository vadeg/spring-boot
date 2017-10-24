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

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SystemPropertiesTests {

	@Test
	public void parseEmpty() throws Exception {
		Assertions.assertThat(new SystemProperty().asString()).isEqualTo("");
	}

	@Test
	public void parseOnlyKey() throws Exception {
		Assertions
				.assertThat(
						new SystemProperty()
								.setKey("key1")
								.asString()
				)
				.isEqualTo("-Dkey1");
	}

	@Test
	public void parseKeyWithValue() throws Exception {
		Assertions
				.assertThat(
						new SystemProperty()
								.setKey("key1")
								.setValue("value1")
								.asString()
				)
				.isEqualTo("-Dkey1=value1");
	}

	@Test
	public void parseKeyWithEmptyValue() throws Exception {
		Assertions
				.assertThat(
						new SystemProperty()
								.setKey("key1")
								.setValue("")
								.asString()
				)
				.isEqualTo("-Dkey1");
	}
}
