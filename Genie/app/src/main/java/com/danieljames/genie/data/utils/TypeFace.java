package com.danieljames.genie.data.utils;

/**
 * An Enum of custom font typfaces.
 */
public enum TypeFace {
    RalewayBold("fonts/Raleway-Bold.ttf"),
    RalewayThin("fonts/Raleway-Thin.ttf"),
    RalewayLight("fonts/Raleway-Light.ttf"),
    RalewaySemiBold("fonts/Raleway-SemiBold.ttf");


    private String path;

    TypeFace(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
