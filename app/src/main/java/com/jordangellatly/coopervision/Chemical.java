package com.jordangellatly.coopervision;

public class Chemical {

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
