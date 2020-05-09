Feature: Validating  Place API

@AddPlace
Scenario Outline: Verify if place being successfully added using AddPlace API
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" Http request
    Then API call success with statuscode 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"
Examples:
	| name | language | address |
	| Mayur| English  | Pune	|
#	| Test | Marathi  | Nagpur  |
#	| Sel  | Hindi    | Mumbai |

@DeletePlace
Scenario: Verify if delete place functionality working fine or not
	Given Deleteplace payload
	When user calls "DeletePlaceAPI" with "POST" Http request
    Then API call success with statuscode 200
    And "status" in response body is "OK"