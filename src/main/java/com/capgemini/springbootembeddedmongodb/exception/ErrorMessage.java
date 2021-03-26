package com.capgemini.springbootembeddedmongodb.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorMessage {

		public ErrorMessage()
		{
			super();
		}

		private Date timestamp;
		private String message;
		private String description;


		public Date getTimestamp()
		{
			return timestamp;
		}

		public void setTimestamp(Date timestamp)
		{
			this.timestamp = timestamp;
		}

		public String getMessage()
		{
			return message;
		}

		public void setMessage(String message)
		{
			this.message = message;
		}

		public String getDescription()
		{
			return description;
		}

		public void setDescription(String description)
		{
			this.description = description;
		}

	
}
