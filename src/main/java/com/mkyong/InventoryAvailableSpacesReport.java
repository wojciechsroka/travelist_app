package com.mkyong;

public class InventoryAvailableSpacesReport {
    private int claimedSpaces;

    public int getClaimedSpaces() {
        return claimedSpaces;
    }

    public void setClaimedSpaces(int claimedSpaces) {
        this.claimedSpaces = claimedSpaces;
    }

    public int getNominalCapacity() {
        return nominalCapacity;
    }

    public void setNominalCapacity(int nominalCapacity) {
        this.nominalCapacity = nominalCapacity;
    }

    public String getGarageLocationShortName() {
        return garageLocationShortName;
    }

    public void setGarageLocationShortName(String garageLocationShortName) {
        this.garageLocationShortName = garageLocationShortName;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public int getGarageCode() {
        return garageCode;
    }

    public void setGarageCode(int garageCode) {
        this.garageCode = garageCode;
    }

    private int nominalCapacity;
    private String garageLocationShortName;
    private String garageName;
    private int garageCode;
}
