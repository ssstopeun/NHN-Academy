package com.nhnacademy.shoppingmall.data.Address.domain;

import java.util.Objects;

public class Address {
    private int addressId;
    private String userId;
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String address;
    private String request;

    public Address(int addressId, String userId, String addressName, String recipient, String phoneNumber, String address, String request) {
        this.addressId = addressId;
        this.userId = userId;
        this.addressName = addressName;
        this.recipient = recipient;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.request = request;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", userId='" + userId + '\'' +
                ", addressName='" + addressName + '\'' +
                ", recipient='" + recipient + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", request='" + request + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(userId, address1.userId) && Objects.equals(addressName, address1.addressName) && Objects.equals(recipient, address1.recipient) && Objects.equals(phoneNumber, address1.phoneNumber) && Objects.equals(address, address1.address) && Objects.equals(request, address1.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, userId, addressName, recipient, phoneNumber, address, request);
    }

    public int getAddressId() {
        return addressId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
