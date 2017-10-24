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

/**
 * Class for system properties passed from pom file.
 * @author Vadim Shaigorodskiy
 * @see RunMojo
 * @see StartMojo
 */
public class SystemProperty {

	private final static String NO_VALUE_FORMAT = "-D%s";
	private final static String KEY_VALUE_FORMAT = NO_VALUE_FORMAT + "=%s";

	private String key;

	private String value;

	public String getKey() {
		return this.key;
	}

	public SystemProperty setKey(String key) {
		this.key = key;
		return this;
	}

	public String getValue() {
		return this.value;
	}

	public SystemProperty setValue(String value) {
		this.value = value;
		return this;
	}

	public String asString() {
		if (this.key == null) {
			return "";
		}
		if (this.value == null || this.value.trim().isEmpty()) {
			return String.format(NO_VALUE_FORMAT, this.key);
		}
		return String.format(KEY_VALUE_FORMAT, this.key, this.value);
	}
}
