package com.foodworld.pojo;

import org.joda.time.DateTime;

/**
 * 
 * @author raja maragani
 *
 */
public class Otp {

    private String phoneNumber;
    private int otp;
    private DateTime otpValidUpto;
    private boolean isOtpValidated;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public DateTime getOtpValidUpto() {
        return otpValidUpto;
    }

    public void setOtpValidUpto(DateTime otpValidUpto) {
        this.otpValidUpto = otpValidUpto;
    }

    public boolean isOtpValidated() {
        return isOtpValidated;
    }

    public void setOtpValidated(boolean isOtpValidated) {
        this.isOtpValidated = isOtpValidated;
    }

}
