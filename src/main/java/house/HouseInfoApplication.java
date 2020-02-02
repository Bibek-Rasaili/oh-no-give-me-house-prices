package house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HouseInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseInfoApplication.class, args);
	}

}

/*
	The Story
	As a developer at FooBar Inc.
		I want to access __house price__ paid records in JSON format via a REST API
		So that I can build an automated system using this data
		Acceptance Criteria
		- A list of all records is returned in JSON format via the REST API
		- A single record is returned in JSON format when its ID is provided
		- A list of records of purchases made in a specified time range is returned in JSON format when
		a date time range is provided*/

/*
[
		{
		"address": "heyyy1",
		"postCode": "ls55555",
		"purchases": [{
			"date": "1994-10-09",
			"transactionValue": "300000"
		}],
		"price": 22,
		}
]
*/

