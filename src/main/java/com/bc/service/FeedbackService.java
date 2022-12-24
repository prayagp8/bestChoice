package com.bc.service;

import java.util.List;

import com.bc.exception.CustomerException;
import com.bc.exception.FeedbackException;
import com.bc.model.Feedback;



public interface FeedbackService {

	public Feedback addFeedback(Feedback feedback, Long customerId) throws FeedbackException,CustomerException;

	public Feedback findByFeeedbackId(Integer feedbackId) throws FeedbackException;

	public List<Feedback> findByCustomerId(Long customerId) throws FeedbackException,CustomerException;

	public List<Feedback> viewAllFeedbacks() throws FeedbackException;
	
	

}
