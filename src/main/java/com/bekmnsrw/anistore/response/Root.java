package com.bekmnsrw.anistore.response;

import lombok.Getter;

@Getter
public class Root {
    public Motd motd;
    public boolean success;
    public boolean historical;
    public String base;
    public String date;
    public Rates rates;
}
