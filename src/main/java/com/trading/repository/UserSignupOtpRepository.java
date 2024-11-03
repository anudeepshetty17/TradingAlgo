package com.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trading.model.UserSignupOtp;

public interface UserSignupOtpRepository extends JpaRepository<UserSignupOtp, Long> {

	UserSignupOtp findByOtp(int otp);
}
