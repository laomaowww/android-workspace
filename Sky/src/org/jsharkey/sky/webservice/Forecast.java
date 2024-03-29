/*
 * Copyright (C) 2009 Jeff Sharkey, http://jsharkey.org/
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

package org.jsharkey.sky.webservice;

/**
 * Data for a specific forecast at a point in time.
 */
public class Forecast {
    @Override
	public String toString() {
		return "alter:" + alert +",validStart:" + validStart +
		", tempHigh:" + tempHigh + ", templow:" + tempLow + ", conditions:" + conditions
		+ ", url:" + url;
	}

	boolean alert = false;
    long validStart = Long.MIN_VALUE;
    int tempHigh = Integer.MIN_VALUE;
    int tempLow = Integer.MIN_VALUE;
    String conditions;
    String url;
    
    /**
     * Exception to inform callers that we ran into problems while parsing the
     * forecast returned by the webservice.
     */
    public static final class ParseException extends Exception {
        private static final long serialVersionUID = 1L;

        public ParseException(String detailMessage) {
            super(detailMessage);
        }

        public ParseException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }
    }

}
