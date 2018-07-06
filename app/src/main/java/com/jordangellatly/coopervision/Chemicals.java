package com.jordangellatly.coopervision;

public class Chemicals {
    public Long bottleCount;
    public String casNumber,
            expirationDate,
            locationInLab,
            lotOrderNumber,
            manufacturer,
            materialName,
            productCode,
            receiveDate,
            size,
            type;

    public Chemicals() {

    }

    public Chemicals(Long bottleCount, String casNumber, String expirationDate, String locationInLab, String lotOrderNumber, String manufacturer, String materialName, String productCode, String receiveDate, String size, String type) {
        this.bottleCount = bottleCount;
        this.casNumber = casNumber;
        this.expirationDate = expirationDate;
        this.locationInLab = locationInLab;
        this.lotOrderNumber = lotOrderNumber;
        this.manufacturer = manufacturer;
        this.materialName = materialName;
        this.productCode = productCode;
        this.receiveDate = receiveDate;
        this.size = size;
        this.type = type;
    }

    public Long getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(Long bottleCount) {
        this.bottleCount = bottleCount;
    }

    public String getCasNumber() {
        return casNumber;
    }

    public void setCasNumber(String casNumber) {
        this.casNumber = casNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLocationInLab() {
        return locationInLab;
    }

    public void setLocationInLab(String locationInLab) {
        this.locationInLab = locationInLab;
    }

    public String getLotOrderNumber() {
        return lotOrderNumber;
    }

    public void setLotOrderNumber(String lotOrderNumber) {
        this.lotOrderNumber = lotOrderNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Chemicals{" +
                "bottleCount='" + bottleCount + '\'' +
                ", casNumber='" + casNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", locationInLab='" + locationInLab + '\'' +
                ", lotOrderNumber='" + lotOrderNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", materialName='" + materialName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", receiveDate='" + receiveDate + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
