package com.jordangellatly.coopervision;

public class Chemical {
    public String getChemicalArea() {
        return chemicalArea;
    }

    public void setChemicalArea(String chemicalArea) {
        this.chemicalArea = chemicalArea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getLotOrder() {
        return lotOrder;
    }

    public void setLotOrder(String lotOrder) {
        this.lotOrder = lotOrder;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getChemicalOwner() {
        return chemicalOwner;
    }

    public void setChemicalOwner(String chemicalOwner) {
        this.chemicalOwner = chemicalOwner;
    }

    public String getCasNumber() {
        return casNumber;
    }

    public void setCasNumber(String casNumber) {
        this.casNumber = casNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(String bottleCount) {
        this.bottleCount = bottleCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPhysicalState() {
        return physicalState;
    }

    public void setPhysicalState(String physicalState) {
        this.physicalState = physicalState;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String chemicalArea,
            location,
            purchaseOrder,
            lotOrder,
            receiveDate,
            expirationDate,
            chemicalOwner,
            casNumber,
            materialName,
            type,
            manufacturer,
            catalogNumber,
            bottleCount,
            size,
            comments,
            physicalState,
            temperature,
            pressure,
            containerType;

    public Chemical(String chemicalArea, String location, String purchaseOrder, String lotOrder, String receiveDate, String expirationDate, String chemicalOwner, String casNumber, String materialName, String type, String manufacturer, String catalogNumber, String bottleCount, String size, String comments, String physicalState, String temperature, String pressure, String containerType) {
        this.chemicalArea = chemicalArea;
        this.location = location;
        this.purchaseOrder = purchaseOrder;
        this.lotOrder = lotOrder;
        this.receiveDate = receiveDate;
        this.expirationDate = expirationDate;
        this.chemicalOwner = chemicalOwner;
        this.casNumber = casNumber;
        this.materialName = materialName;
        this.type = type;
        this.manufacturer = manufacturer;
        this.catalogNumber = catalogNumber;
        this.bottleCount = bottleCount;
        this.size = size;
        this.comments = comments;
        this.physicalState = physicalState;
        this.temperature = temperature;
        this.pressure = pressure;
        this.containerType = containerType;
    }

    @Override
    public String toString() {
        return "Chemical{" +
                "chemicalArea='" + chemicalArea + '\'' +
                ", location='" + location + '\'' +
                ", purchaseOrder='" + purchaseOrder + '\'' +
                ", lotOrder='" + lotOrder + '\'' +
                ", receiveDate='" + receiveDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", chemicalOwner='" + chemicalOwner + '\'' +
                ", casNumber='" + casNumber + '\'' +
                ", materialName='" + materialName + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", catalogNumber='" + catalogNumber + '\'' +
                ", bottleCount='" + bottleCount + '\'' +
                ", size='" + size + '\'' +
                ", comments='" + comments + '\'' +
                ", physicalState='" + physicalState + '\'' +
                ", temperature='" + temperature + '\'' +
                ", pressure='" + pressure + '\'' +
                ", containerType='" + containerType + '\'' +
                '}';
    }
}
