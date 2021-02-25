Feature: Premier inn Application

  Scenario Outline: Search for a hotel room and save
  Given Load the page www.premierinn.com
	When In the home page, click on manage booking
	Then Load the booking details booking reference <BookingReference>, lastname <LastName>, arrivaldate <ArrivalDate>
	And In the booking information page, click on Amend booking
	Then In the Amend booking page, change the arrival date to a <differentdate>
	And Capture the information in the Rooms available <differentdate>
	Then Write the information capture to a csv file

    Examples: 
      | BookingReference   | FirstName  	| LastName | ArrivalDate |differentdate|
      | AKTR278839 				| Automation  	|  Tester 	| '21-May-2021'   | '22-May-2021'|
      | BBDR182669 				| Automation 	|  Tester 	| '21-May-2021'   | '23-May-2021'|