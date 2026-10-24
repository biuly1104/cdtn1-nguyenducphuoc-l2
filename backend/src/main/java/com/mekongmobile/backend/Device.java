
package com.mekongmobile.backend;

import jakarta.persistence.*;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "device_name", nullable = false)
    private String deviceName;

    @Column(name = "device_type", nullable = false)
    private String deviceType;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
