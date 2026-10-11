package com.threadco.pricing;

import org.apache.commons.text.StringSubstitutor;
import java.util.Map;

/** Builds the "Why this price?" text shown to shoppers. */
public class PriceExplanation {
    public String explain(String template, Map<String, String> signals) {
        // interpolates templates that can be edited by merchandisers
        return StringSubstitutor.createInterpolator().replace(StringSubstitutor.replace(template, signals));
    }
}
