/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package thymeleafexamples.stsm.web.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LocalDateFormatter implements Formatter<LocalDate> {

    @Autowired
    private MessageSource messageSource;


    public LocalDateFormatter() {
        super();
    }

    public LocalDate parse(final String text, final Locale locale) throws ParseException {
        final DateTimeFormatter localDateFormatter = createLocalDateFormat(locale);
        // return localDateFormatter.parse(text);
        return LocalDate.parse(text, localDateFormatter);
    }

    public String print(final LocalDate object, final Locale locale) {
        final DateTimeFormatter localDateFormatter = createLocalDateFormat(locale);
        // return localDate.format(localDateFormatter);
        return localDateFormatter.format(object);
    }

    private DateTimeFormatter createLocalDateFormat(final Locale locale) {
        final String format = this.messageSource.getMessage("localdate.format", null, locale);
        final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(format);
        return localDateFormatter;
    }

}
