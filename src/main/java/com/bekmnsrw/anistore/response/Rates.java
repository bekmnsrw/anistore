package com.bekmnsrw.anistore.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {
    @JsonProperty("USD")
    public double USD;
    @JsonProperty("EUR")
    public double EUR;
}
