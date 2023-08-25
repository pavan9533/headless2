package Utility;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;


public class ValidationKeyword extends GenericKeyword{
	
	public void validateTitle(String locatorkey) {
		WebElement e = driver.findElement(getLocator(locatorkey));
		if(e.isDisplayed()) {
			test.log(Status.PASS, "Title is Present");
			generateScreenshots();
		}else {
			test.log(Status.FAIL, "Title is not present");
			reportFailure("Title not displayed", true);
		}
	}
	
	public void validateNotLoginText(String locatorkey) {
		if(locatorkey.toLowerCase().contains("An error has occurred. Please check the input and try again.".toLowerCase())) {
			test.log(Status.FAIL, "Text is correct");
			generateScreenshots();
		}else {
			test.log(Status.FAIL, "Text is not correct");
			reportFailure("Text not displayed", true);
		}
	}

	public void validateSignInPopup() {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String popupText = alert.getText();
	        
	        String expectedText = "Signed in Successfully";
	        
	        if (popupText.equals(expectedText)) {
	            test.log(Status.PASS, "Popup text is as expected: " + popupText);
	        } else {
	            test.log(Status.FAIL, "Popup text is not as expected. Expected: " + expectedText + ", Actual: " + popupText);
	        }
	        
	        alert.accept();
	    } catch (Exception e) {
	        System.out.println("No popup found.");
	    }
	}
	
	public void validateAddToCartPopup() {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String popupText = alert.getText();
	        
	        String expectedText = "Item successfully Added to Cart.";
	        
	        if (popupText.equals(expectedText)) {
	            test.log(Status.PASS, "Popup text is as expected: " + popupText);
	        } else {
	            test.log(Status.FAIL, "Popup text is not as expected. Expected: " + expectedText + ", Actual: " + popupText);
	        }
	        
	        alert.accept();
	    } catch (Exception e) {
	        System.out.println("No popup found.");
	    }
	}
	
	public void validateOrderStatus(String locatorkey) {
		WebElement e = driver.findElement(getLocator(locatorkey));
		String text = e.getText();
		if(text.toLowerCase().equals("pending payment")) {
			test.log(Status.INFO, "Order status is Payment Pending");
			generateScreenshots();
		}else if(text.toLowerCase().equals("confirmed")) {
			test.log(Status.INFO, "Order status is Confirmed");
			generateScreenshots();
		}else if(text.toLowerCase().equals("invoiced")) {
			test.log(Status.INFO, "Order status is Invoiced");
			generateScreenshots();
		}else if(text.toLowerCase().equals("shipped")) {
			test.log(Status.INFO, "Order status is Shipped");
			generateScreenshots();
		}else if(text.toLowerCase().equals("complete")) {
			test.log(Status.INFO, "Order status is Complete");
			generateScreenshots();
		}else if(text.isEmpty()) {
			test.log(Status.FAIL, "Order status is blank");
			generateScreenshots();
		}else if(text.toLowerCase().equals("closed")) {
			test.log(Status.INFO, "Order staus is Closed");
			generateScreenshots();
		}
	}
	
	public void validateLogin(String valid, String invalid) {
	    try {
	        WebElement element = driver.findElement(getLocator(valid));
	        if (element.isDisplayed()) {
	            test.log(Status.PASS, "Logged in successfully");
	            generateScreenshots();
	        } else {
	            validateNotLoginText(invalid);
	            test.log(Status.FAIL, "Login unsuccessful");
	            reportFailure("User unable to login", true);
	        }
	    } catch (NoSuchElementException e) {
	        test.log(Status.FAIL, "Element not found: " + valid);
	        reportFailure("Element not found: " + valid, true);
	    }
	}
	
	public void validateLogin(String locatorKey) {
		WebElement e = driver.findElement(getLocator(locatorKey));
		String text=e.getText();
		String expectedText = "Hi";
		
		if(text.toLowerCase().contains(expectedText.toLowerCase())) {
			test.log(Status.PASS, "User loggedIn Successfully");
			generateScreenshots();
		}else {
			test.log(Status.FAIL, "User Not loggedIn");
			//reportFailure("element not Present", true);
			generateScreenshots();
		}
	}
	
	
	public void validateLogout(String locatorKey) {
		WebElement e = driver.findElement(getLocator(locatorKey));
		String text=e.getText();
		if(text.equals("Sign In")) {
			test.log(Status.PASS, "User Logged out Successfully");
			generateScreenshots();
		}else {
			test.log(Status.FAIL, "User not logged out");
			generateScreenshots();
		}
	}
	
	
	
	public void validateElementPresent(String locatorkey) {
		try{
		WebElement e = driver.findElement(getLocator(locatorkey));
		
		String text = e.getText();
		if(e.isDisplayed()) {
			test.log(Status.PASS,text+ " Element is present");
			
			generateScreenshots();
		}else {
			test.log(Status.FAIL,text+ " Element not present");
			reportFailure("element not Present", false);
		}
		}catch (Exception e) {
	    test.log(Status.FAIL, "Element not present: " + e.getMessage());
	    generateScreenshots();
		}
	}

	public void validateElementPresentText(String locatorkey, String message) {
		try {
		WebElement e = driver.findElement(getLocator(locatorkey));
		
		String text = e.getText();
		if(e.isDisplayed()) {
			test.log(Status.PASS,text+ " "+message+" Element is present");
			
			generateScreenshots();
		}else {
			test.log(Status.FAIL,text+" "+message+ " Element not present");
			reportFailure("element not Present", false);
		}
		}catch(Exception e) {
			test.log(Status.FAIL, "Element not present: " + e.getMessage());
		    generateScreenshots();
		}
	}
	
	public void validateElementsPresent(String locatorKey) {
	    List<WebElement> elements = driver.findElements(getLocator(locatorKey));
	    int elementsCount = elements.size();

	    if (elementsCount > 0) {
	        for (WebElement e : elements) {
	            String text = e.getText();
	            if (e.isDisplayed()) {
	                test.log(Status.PASS, text + " Element is present");
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, text + " Element not present");
	                reportFailure("Element not Present", false);
	            }
	        }
	    } else {
	        test.log(Status.FAIL, "No elements found with locator: " + locatorKey);
	        reportFailure("No elements found", false);
	    }
	}

	
	
	
	public void validateCriticalElements(String locatorkey) {
		
		WebElement e = driver.findElement(getLocator(locatorkey));
		if(e.isDisplayed()) {
			test.log(Status.PASS, "Element is present");
			generateScreenshots();
		}else {
			test.log(Status.FAIL, "Element not present");
			reportFailure("element not Present", true);
	
		}
	}
	public void validateText(String locatorkey ,String expectedText) {
		WebElement e = driver.findElement(getLocator(locatorkey));
		String text=e.getText();
		
		if(text.toLowerCase().contains(expectedText.toLowerCase())) {
			test.log(Status.PASS, text+" "+"Text is Displayed");
			generateScreenshots();
		}else {
			test.log(Status.FAIL, "Text is not Displayed");
			//reportFailure("element not Present", true);
			generateScreenshots();
		}
//		if(e.isDisplayed()) {
//			test.log(Status.INFO, "Element is Displayed");
			
//			Assert.assertEquals(text, expectedText, "Text is as expected");
			
//		}else {
//			test.log(Status.INFO, "Element is not Displayed");
//			
//		}
	}
	
	public void validateFieldRequired(String locatorKey) {
		WebElement e = driver.findElement(getLocator(locatorKey));
		String text=e.getText();
		if(text.equals("This is a required field.")) {
			test.log(Status.PASS, text+ " "+ "text is displayed");
			generateScreenshots();
		}else {
			test.log(Status.FAIL, text+ " "+ "text is displayed");
			generateScreenshots();
		}
	}
	
//	public void validateErrortext(String textBoxLocator, String errorLocator) {
//		WebElement inputbox = driver.findElement(getLocator(textBoxLocator));
//		WebElement error = driver.findElement(getLocator(errorLocator));
//		
//		String inputtext = inputbox.getAttribute("value");
//		String errortext = error.getText();
//		String specialCharsPattern = "[!\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]";
//		String specialCharctersError= "First Name should not contain special characters";
//		String fieldRequiredText="This is a required field.";
//		log(inputtext);
//		log(errortext);
//		if(!inputtext.isBlank()) {
//			if(!errortext.isEmpty()) {
//					if(inputtext.contains(specialCharsPattern)) {
//						if(errortext.equals(specialCharctersError)) {
//							test.log(Status.PASS, errortext+" "+ "Text is displayed for Special charcters");
//							generateScreenshots();
//						}else if(errortext.equals(fieldRequiredText)) {
//							test.log(Status.FAIL, errortext+" "+ "Text is displayed for Special charcters");
//							generateScreenshots();
//						}else {
//							test.log(Status.FAIL, errortext+" "+"error is displayed");
//							generateScreenshots();
//						}
//					}
//					else if(!inputtext.contains(specialCharsPattern)){
//						test.log(Status.FAIL, errortext+" "+"error is displayed even without special charcters in the input");
//						generateScreenshots();	
//					}
//			}else if(errortext.isEmpty()) {
//				if(!inputtext.contains(specialCharsPattern)) {
//					test.log(Status.PASS, "Input entered does not contain special charcters");
//					generateScreenshots();
//				}else if(inputtext.contains(specialCharsPattern)) {
//					test.log(Status.FAIL, "error message is not displayed even after entering special charcters");
//					generateScreenshots();
//				}
//			}
//		else if(inputtext.isBlank()) {
//			if(errortext.isEmpty()) {
//				test.log(Status.FAIL, "Error is not popped up even if inpout field is empty");
//				generateScreenshots();
//			}else if(!errortext.isEmpty()) {
//				if(errortext.equals(fieldRequiredText)) {
//					test.log(Status.PASS, errortext+" "+ "Error is displayed");
//					generateScreenshots();
//				}
//			}
//		}
//		}
//	}
	
	
	public void validatePasswordVisibility(String textBoxLocator) {
		WebElement inputbox = driver.findElement(getLocator(textBoxLocator));
		String inputValue = inputbox.getAttribute("type");
		if(inputValue.equals("password")) {
			test.log(Status.INFO, "Password is not visible");
		}else if(inputValue.equals("text")) {
			test.log(Status.INFO, "Password is visible.");
		}
	}
	
	
	public void validateErrortext(String textBoxLocator, String errorLocator) {
	    WebElement inputbox = driver.findElement(getLocator(textBoxLocator));
	    WebElement error = driver.findElement(getLocator(errorLocator));

	    String inputtext = inputbox.getAttribute("value");
	    String errortext = error.getText();
	    @SuppressWarnings("unused")
		String specialCharsPattern = "[!\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]";
	    String specialCharactersError = "should not contain special characters";
	    String fieldRequiredText = "This is a required field.";

	    log(inputtext);
	    log(errortext);

	    if (!inputtext.isBlank()) {
	        if (!errortext.isEmpty()) {
	            if (validateSpecialCharacters(inputtext)) {
	                if (errortext.toLowerCase().contains(specialCharactersError.toLowerCase())) {
	                    test.log(Status.PASS, errortext + " Text is displayed for entering special characters in the Input");
	                    generateScreenshots();
	                } else if (errortext.equals(fieldRequiredText)) {
	                    test.log(Status.FAIL, errortext + " Text is displayed for entering special characters in the Input");
	                    generateScreenshots();
	                } else {
	                    test.log(Status.FAIL, errortext + " Error is displayed");
	                    generateScreenshots();
	                }
	            } else if (!validateSpecialCharacters(inputtext)) {
	                test.log(Status.FAIL, errortext + " Error is displayed even without special characters in the input");
	                generateScreenshots();
	            }
	        } else if (errortext.isEmpty()) {
	            if (!validateSpecialCharacters(inputtext)) {
	                test.log(Status.PASS, "Input entered does not contain special characters");
	                generateScreenshots();
	            } else if (validateSpecialCharacters(inputtext)) {
	                test.log(Status.FAIL, "Error message is not displayed even after entering special characters");
	                generateScreenshots();
	            }
	        }
	    } else if (inputtext.isBlank()) {
	        if (errortext.isEmpty()) {
	            test.log(Status.FAIL, "Error is not popped up even if input field is empty");
	            generateScreenshots();
	        } else if (!errortext.isEmpty()) {
	            if (errortext.equals(fieldRequiredText)) {
	                test.log(Status.PASS, errortext + " Error is displayed");
	                generateScreenshots();
	            }
	        }
	    }
	}

	public boolean validateSpecialCharacters(String inputText) {
	    String specialCharsPattern = "[!\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]";
	    return inputText.matches(".*" + specialCharsPattern + ".*");
	}

	public void validateEmailIputField(String textBoxLocator, String errorLocator) {
        WebElement inputBox = driver.findElement(getLocator(textBoxLocator));
        WebElement error = driver.findElement(getLocator(errorLocator));

        String inputText = inputBox.getAttribute("value");
        String errorText = error.getText();
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String emailFormatError = "Email should not contain special characters other than . and @.";
        String fieldRequiredText = "This is a required field.";

        log(inputText);
        log(errorText);

        if (!inputText.isBlank()) {
            if (!errorText.isEmpty()) {
                if (!inputText.contains(emailPattern)) {
                    if (errorText.equals(emailFormatError)) {
                        test.log(Status.PASS, errorText + " Text is displayed for entering an invalid email format in the input");
                        generateScreenshots();
                    } else if (errorText.equals(fieldRequiredText)) {
                        test.log(Status.FAIL, errorText + " Text is displayed for entering an invalid email format in the input");
                        generateScreenshots();
                    } else {
                        test.log(Status.FAIL, errorText + " Error is displayed");
                        generateScreenshots();
                    }
                } else if (inputText.matches(emailPattern)) {
                    test.log(Status.FAIL, "Error message is displayed even with a valid email format in the input");
                    generateScreenshots();
                }
            } else if (errorText.isEmpty()) {
                if (inputText.matches(emailPattern)) {
                    test.log(Status.PASS, "Input entered is in a valid email format");
                    generateScreenshots();
                } else if (!inputText.matches(emailPattern)) {
                    test.log(Status.FAIL, "Error message is not displayed even after entering an invalid email format");
                    generateScreenshots();
                }
            }
        } else if (inputText.isBlank()) {
            if (errorText.isEmpty()) {
                test.log(Status.FAIL, "Error is not popped up even if the input field is empty");
                generateScreenshots();
            } else if (!errorText.isEmpty()) {
                if (errorText.equals(fieldRequiredText)) {
                    test.log(Status.PASS, errorText + " Error is displayed");
                    generateScreenshots();
                }
            }
        }
    }
	 public void validatePassword(String textBoxLocator, String errorLocator) {
	        WebElement inputBox = driver.findElement(getLocator(textBoxLocator));
	        WebElement error = driver.findElement(getLocator(errorLocator));

	        String inputText = inputBox.getAttribute("value");
	        String errorText = error.getText();
	        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d\\s]).{8,}$";
	        String passwordFormatError = "Must contain at least 8 character(s).";
	        String passwordFormatError2 = "A password must contain at least 3 of the following: lowercase, uppercase, digits, special characters.";
	        String fieldRequiredText = "This is a required field.";

	        log(inputText);
	        log(errorText);

	        if (!inputText.isBlank()) {
	            if (!errorText.isEmpty()) {
	                if (!inputText.matches(passwordPattern)) {
	                    if (errorText.equals(passwordFormatError)) {
	                        test.log(Status.PASS, errorText + " Text is displayed for entering less than 8 charcters");
	                        generateScreenshots();
	                    } else if (errorText.equals(fieldRequiredText)) {
	                        test.log(Status.FAIL, errorText + " Text is displayed for entering an invalid password format in the input");
	                        generateScreenshots();
	                    }else if(errorText.equals(passwordFormatError2)) {
	                    	test.log(Status.PASS, errorText + " Text is displayed for not meeting the password requirements");
	                        generateScreenshots();
	                    } else {
	                        test.log(Status.FAIL, errorText + " Error is displayed");
	                        generateScreenshots();
	                    }
	                } else if (inputText.matches(passwordPattern)) {
	                    test.log(Status.PASS, "Input entered is in a valid password format");
	                    generateScreenshots();
	                }
	            } else if (errorText.isEmpty()) {
	                if (!inputText.matches(passwordPattern)) {
	                    test.log(Status.PASS, "Input entered does not match the required password format");
	                    generateScreenshots();
	                } else if (inputText.matches(passwordPattern)) {
	                    test.log(Status.FAIL, "Error message is not displayed even after entering a valid password format");
	                    generateScreenshots();
	                }
	            }
	        } else if (inputText.isBlank()) {
	            if (errorText.isEmpty()) {
	                test.log(Status.FAIL, "Error is not displayed even if the input field is empty");
	                generateScreenshots();
	            } else if (!errorText.isEmpty()) {
	                if (errorText.equals(fieldRequiredText)) {
	                    test.log(Status.PASS, errorText + " Error is displayed");
	                    generateScreenshots();
	                }
	            }
	        }
	    }
	
	 public void validateAccountDuplicate(String locatorKey) {
		 try{
			WebElement e = driver.findElement(getLocator(locatorKey));
			
			String text = e.getText();
			if(e.isDisplayed()) {
				test.log(Status.PASS , text+" text is Displayed if an account is already created with the same mail ID.");
				generateScreenshots();
			}else {
				test.log(Status.FAIL,text+ " text is displayed");
				generateScreenshots();
			}
		}catch (Exception e) {
		    test.log(Status.INFO, "Account Duplicate error not displayed." );
		    generateScreenshots();
			}
	 }
	
	
	public void validateOrderID(String locatorKey, String columnName, String nameOfSheet) {
	    List<String> expectedOrderIDs = readExcelData(columnName, nameOfSheet);
	    if (expectedOrderIDs == null) {
	        // Failed to read Excel data
	        test.log(Status.FAIL, "Failed to read Excel data for validation");
	        generateScreenshots();
	        return;
	    }

	    WebElement element = driver.findElement(getLocator(locatorKey));
	    String actualOrderID = element.getText();

	    boolean isOrderIDDisplayed = false;
	    for (String expectedOrderID : expectedOrderIDs) {
	        if (actualOrderID.equals(expectedOrderID)) {
	            isOrderIDDisplayed = true;
	            test.log(Status.PASS, "Order ID is Displayed: " + expectedOrderID);
	            break;
	        }
	    }

	    if (!isOrderIDDisplayed) {
	        test.log(Status.FAIL, "Order ID is not Displayed");
	        generateScreenshots();
	    }
	}

	//Search Product Validation
	
//	public void validateSearchBoxError(String textBoxLocator, String errorLocator) {
//        WebElement inputBox = driver.findElement(getLocator(textBoxLocator));
//        WebElement errorMessage = driver.findElement(getLocator(errorLocator));
//        String inputText = inputBox.getAttribute("value");
//        String errorText = errorMessage.getText();
//        String blankSuggestionText = "Please enter a search term.";
//        int minimumLetters = 3;
//        String specialCharcterError="Only alphanumeric (a-z,0-9), _ and - characters are allowed.";
//
//        log("Input is "+inputText);
//        log("Error message is "+errorText);
//
//        if (!inputText.isBlank()) {
//        	 if (!validateSpecialCharacters(inputText)) {
//        		 if (inputText.length() >= minimumLetters) {
//                    if (!errorText.isEmpty()) {
//                        if (errorText.toLowerCase().contains(inputText.toLowerCase())) {
//                            test.log(Status.PASS, errorText + " Suggestions are displayed for the entered search text");
//                            generateScreenshots();
//                        } else if (!errorText.toLowerCase().contains(inputText.toLowerCase())) {
//                            test.log(Status.FAIL, errorText + " Suggestions are not displayed for the entered search text");
//                            generateScreenshots();
//                        }
//                    } else if (errorText.isEmpty()) {
//                        test.log(Status.FAIL, "No suggestions are displayed for the entered search text");
//                        generateScreenshots();
//                    }
//                } else if(!(inputText.length() >= minimumLetters)) {
//                	  if (!errorText.isEmpty()) {
//                          test.log(Status.FAIL, errorText + " Suggestion is displayed even without entering 3 letters");
//                      }
//                      test.log(Status.FAIL, "Minimum " + minimumLetters + " letters are required to display suggestions");
//                      generateScreenshots();
//                    }
//                }
//            } else if (validateSpecialCharacters(inputText)) {
//                if(errorText.toLowerCase().contains(specialCharcterError.toLowerCase())) {
//                	test.log(Status.PASS, errorText+" "+ "Text is displayed on entering special charcters");
//                	generateScreenshots();
//                }else if(!errorText.toLowerCase().contains(specialCharcterError.toLowerCase())) {
//                	test.log(Status.FAIL, errorText+" "+"Text is displayed");
//                	generateScreenshots();
//            }
//        } else if (inputText.isBlank()) {
//            if (errorText.toLowerCase().contains(blankSuggestionText.toLowerCase())) {
//                test.log(Status.PASS, errorText + " Text is displayed if search for blank input in the search box");
//                generateScreenshots();
//            } else if (!errorText.toLowerCase().contains(blankSuggestionText.toLowerCase())) {
//                test.log(Status.FAIL, "Text is not displayed if search for blank input in the search box");
//                generateScreenshots();
//            }
//        }
//	}
	public void validateSearchBoxError(String textBoxLocator, String errorLocator) {
	    WebElement inputBox = driver.findElement(getLocator(textBoxLocator));
	    WebElement errorMessage = driver.findElement(getLocator(errorLocator));
	    String inputText = inputBox.getAttribute("value");
	    String errorText = errorMessage.getText();
	    String blankSuggestionText = "Please enter a search term.";
	    String specialCharacterError = "Only alphanumeric (a-z,0-9), _ and - characters are allowed.";
	    int minimumLetters = 3;

	    log("Input is " + inputText);
	    log("Error message is " + errorText);

	    if (inputText.isEmpty()) {
	        if (errorText.toLowerCase().contains(blankSuggestionText.toLowerCase())) {
	            test.log(Status.PASS, errorText + " Text is displayed if search box is blank");
	            generateScreenshots();
	        } else {
	            test.log(Status.FAIL, "Text is not displayed if search box is blank");
	            generateScreenshots();
	        }
	    } else if (inputText.length() < minimumLetters) {
	        test.log(Status.FAIL, "Minimum " + minimumLetters + " letters are required to display suggestions");
	    } else if (validateSpecialCharacters(inputText)) {
	        if (errorText.contains(specialCharacterError)) {
	            test.log(Status.PASS, errorText + " Text is displayed on entering special characters");
	            generateScreenshots();
	        } else {
	            test.log(Status.FAIL, errorText + " Text is displayed");
	            generateScreenshots();
	        }
	    } else {
	        if (!errorText.isEmpty()) {
	            if (errorText.toLowerCase().contains(inputText.toLowerCase())) {
	                test.log(Status.PASS, errorText + " Suggestions are displayed for the entered search text");
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, errorText + " Suggestions are not displayed for the entered search text");
	                generateScreenshots();
	            }
	        } else {
	            test.log(Status.FAIL, "No suggestions are displayed for the entered search text");
	            generateScreenshots();
	        }
	    }
	}

	public void validateSearchBox(String textBoxLocator, String suggestionLocator ,String errorMessageLocator) {
		WebElement  inputBox = null;
		WebElement suggestion = null;
		try {
		  inputBox = driver.findElement(getLocator(textBoxLocator));
		 }catch(Exception e) {
			 test.log(Status.FAIL, "Search box is not editable");
			 generateScreenshots();
		 }
		try {
	      suggestion = driver.findElement(getLocator(suggestionLocator));
		}catch(Exception e) {
			 test.log(Status.FAIL, "Suggestion products are not visible.");
			 generateScreenshots();
		 }
	     try {
	      WebElement errorMsg = driver.findElement(getLocator(errorMessageLocator));
	      String errorText = errorMsg.getText();
	      test.log(Status.FAIL, errorText+" is displayed");
	      generateScreenshots();
	     }catch(Exception e) {
	    	 test.log(Status.PASS, "Error is not visible." );
	     }
	     
	      String inputText = inputBox.getAttribute("value");
	      String suggestionText = suggestion.getText();
	      int minimumLetters = 3;
	      String specialCharcterError="Only alphanumeric (a-z,0-9), _ and - characters are allowed.";

	      log("Input is "+inputText);
	      log("Suggestion text is "+suggestionText);
	      
	      if (!inputText.isBlank()) {
	        	 if (!validateSpecialCharacters(inputText)) {
	        		 if (inputText.length() >= minimumLetters) {
	                    if (!suggestionText.isEmpty()) {
	                        if (suggestionText.toLowerCase().contains(inputText.toLowerCase())) {
	                            test.log(Status.PASS, suggestionText + " Suggestions are displayed for the entered search text");
	                            generateScreenshots();
	                        } else if (!suggestionText.toLowerCase().contains(inputText.toLowerCase())) {
	                            test.log(Status.FAIL, suggestionText + " Suggestions are not displayed for the entered search text");
	                            generateScreenshots();
	                        }
	                    } else if (suggestionText.isEmpty()) {
	                        test.log(Status.FAIL, "No suggestions are displayed for the entered search text");
	                        generateScreenshots();
	                    }
	                } else if(!(inputText.length() >= minimumLetters)) {
	                	  if (!suggestionText.isEmpty()) {
	                          test.log(Status.FAIL, suggestionText + " Suggestion is displayed even without entering 3 letters");
	                      }
	                      test.log(Status.FAIL, "Minimum " + minimumLetters + " letters are required to display suggestions");
	                      generateScreenshots();
	                    }
	                }
	            } else if (validateSpecialCharacters(inputText)) {
	                if(suggestionText.toLowerCase().contains(specialCharcterError.toLowerCase())) {
	                	test.log(Status.PASS, suggestionText+" "+ "Text is displayed on entering special charcters");
	                	generateScreenshots();
	                }else if(!suggestionText.toLowerCase().contains(specialCharcterError.toLowerCase())) {
	                	test.log(Status.FAIL, suggestionText+" "+"Text is displayed");
	                	generateScreenshots();
	            }
	        } else if (inputText.isBlank()) {
	            if (!suggestionText.isEmpty()) {
	                test.log(Status.PASS, suggestionText + " Text is displayed if search for blank input in the search box");
	                generateScreenshots();
	            } else if (suggestionText.isEmpty()) {
	                test.log(Status.FAIL, "Text is not displayed if search for blank input in the search box");
	                generateScreenshots();
	            }
	        }
	}
	
	//Add to Cart Validation
	
	public void validatePincodePopup(String locatorKey) {
		 WebElement pincodePopUp = driver.findElement(getLocator(locatorKey));
		 if(pincodePopUp.isDisplayed()) {
			 test.log(Status.PASS, "Pincode Pop up is Displayed");
			 generateScreenshots();
		 }else if(!pincodePopUp.isDisplayed()) {
			 test.log(Status.FAIL, "pincode Pop Up is not Displayed");
			 generateScreenshots();
		 }
	}
	public void validatePincodePopupAfterCancel(String locatorKey) {
		 WebElement pincodePopUp = driver.findElement(getLocator(locatorKey));
		 if(pincodePopUp.isDisplayed()) {
			 test.log(Status.FAIL, "Pincode Pop up is Displayed");
			 generateScreenshots();
		 }else if(!pincodePopUp.isDisplayed()) {
			 test.log(Status.PASS, "pincode Pop Up is not Displayed");
			 generateScreenshots();
		 }
	}
	public void validatePincodePopupSecondTme(String locatorKey) {
		 WebElement pincodePopUp = driver.findElement(getLocator(locatorKey));
		 if(pincodePopUp.isDisplayed()) {
			 test.log(Status.FAIL, "Pincode Pop up is Displayed");
			 generateScreenshots();
		 }else if(!pincodePopUp.isDisplayed()) {
			 test.log(Status.PASS, "pincode Pop Up is not Displayed");
			 generateScreenshots();
		 }
	}
	
	public void validatePincodefield(String textboxLocator , String errorLocator) {
		 WebElement inputBox = driver.findElement(getLocator(textboxLocator));
	     WebElement error = driver.findElement(getLocator(errorLocator));
	     String inputText = inputBox.getAttribute("value");
	     String errorText = error.getText();
	     
	     String validPincodeerror ="Please enter valid PinCode";
	     String deliveryNotAvailable="Delivery not available to this location, Please check for other";
	     if(!inputText.isBlank()) {
	    	 if(!errorText.isEmpty()) {
	    		 if(errorText.toLowerCase().equals(validPincodeerror.toLowerCase())) {
	    			 test.log(Status.PASS, errorText+" "+"Text is displayed for invalid pincode");
	    			 generateScreenshots();
	    		 }else if(errorText.toLowerCase().equals(deliveryNotAvailable.toLowerCase()) && inputText.length() == 6) {
	    			 test.log(Status.PASS, errorText+" "+"Text is disaplyed if delivery is not available for this location.");
	    			 generateScreenshots();
	    		 }else {
	    			 test.log(Status.FAIL, errorText+" "+"is displayed");
	    			 generateScreenshots();
	    		 }
	    	 }else if(errorText.isEmpty()) {
	    		 test.log(Status.PASS, "Pincode entered");
	    		 generateScreenshots();
	    	 }
	     }else if(inputText.isBlank()) {
	    	 if(errorText.toLowerCase().equals(validPincodeerror.toLowerCase())) {
	    		 test.log(Status.PASS, errorText+" "+"Text is displayed for blank input and clicking on check button.");
	    		 generateScreenshots();
	    	 }else if(!errorText.toLowerCase().equals(validPincodeerror.toLowerCase())) {
	    		 test.log(Status.FAIL, errorText+" "+"Text is displayed");
	    		 generateScreenshots();
	    	 }
	     }
	     
	}
	
	
	public void validateCorrectPincode(String textLocator, String changePincodeButton) {
		WebElement deliverTo = driver.findElement(getLocator(textLocator));
		String deliverToText = deliverTo.getText();
	     
	      if(!deliverToText.contains("Delivery")) {
	    	 test.log(Status.FAIL, "Delivery message is not Displayed");
	    	 generateScreenshots(); 
	     }else if(deliverToText.contains("Delivery")) {
	    	 test.log(Status.PASS, "Delivery message is Displayed");
	    	 generateScreenshots(); 
	    	 validateChangePincodeButton(changePincodeButton);
	     }
	}
	public void validateChangePincodeButton(String locatorKey) {
		WebElement e = driver.findElement(getLocator(locatorKey));
		if(!e.isDisplayed()) {
			test.log(Status.FAIL, "Change Button is not visible");
			generateScreenshots();
		}else if(e.isDisplayed()) {
			test.log(Status.PASS, "Change Pincode button is visible");
			generateScreenshots();
		}
	}
	public void validateCartItem(String LocatorKey) {
		 WebElement cartItem = driver.findElement(getLocator(LocatorKey));
		 String numberOfItems = cartItem.getText();
		 
		 if(cartItem.isDisplayed()) {
			 test.log(Status.PASS,numberOfItems +" "+" present in cart");
			 generateScreenshots();
		 }else if(!cartItem.isDisplayed()) {
			 test.log(Status.FAIL, "Item is not added to cart");
			 generateScreenshots();
		 }
	}
	
	public void validateHiText(String locatorKey) {
		 WebElement HiTextLocator= driver.findElement(getLocator(locatorKey));
		 
		 String Text = HiTextLocator.getText();
		 if(Text.contains("Hi")) {
			 test.log(Status.PASS, "Name is Displayed in the place of sign In button");
			 generateScreenshots();
		 }else if(!Text.contains("Hi")) {
			 test.log(Status.FAIL, "Name is Not Displayed in the place of sign In button");
			 generateScreenshots();
		 }
	}
	
//	public void validateProductText(String text1, String text2) {
////	    WebElement e1 = driver.findElement(getLocator(locatorKey1));
////	    WebElement e2 = driver.findElement(getLocator(locatorKey2));
////	    
////	    String text1 = e1.getText();
////	    String text2 = e2.getText();
//	    
//	    if (text1.contains(text2)) {
//	        test.log(Status.PASS, "Text match: " + text1);
//	    } else {
//	        test.log(Status.FAIL, "Text does not match. Expected: " + text1 + ", Actual: " + text2);
//	        generateScreenshots();
//	    }
//	}
	
	public void validateProductText(String text1, String text2) {
	    String[] words = text2.split("\\s+");
	    String firstThreeWords = String.join(" ", Arrays.copyOfRange(words, 0, 3));

	    if (text1.contains(firstThreeWords)) {
	        test.log(Status.PASS, "Text match: " + text1);
	    } else {
	        test.log(Status.FAIL, "Text does not match. Expected: " + text1 + ", Actual: " + text2);
	        generateScreenshots();
	    }
	}

	
	public void validateWishlistIcon(String locatorKey) {
		 WebElement wishlistIcon = driver.findElement(getLocator(locatorKey));
		    String initialColor = wishlistIcon.getCssValue("color");
		    
		    log(initialColor);
		    
		    // Perform the action that triggers the color change, e.g., clicking on the wishlist icon
		    
		    String finalColor = wishlistIcon.getCssValue("color");
		    
		    if (!initialColor.equals(finalColor)) {
		        test.log(Status.PASS, "Wishlist icon color changed successfully");
		    } else {
		        test.log(Status.FAIL, "Wishlist icon color did not change");
		    }
	}
//	public void validateEmptyUserPass(String userLocator , String passLocator , String userErrorLocator , String passErrorLocator) {
//		WebElement username = driver.findElement(getLocator(userLocator));
//		WebElement password = driver.findElement(getLocator(passLocator));
//		//WebElement passErrorG = driver.findElement(getLocator(passErrorLocator));
//		String userErrorText = "This is a required field.";
//		String passErrorText = "This is a required field.";
//		String mandatoryCharcters = "@"+".";
//		WebElement userError = null;
//		WebElement passError = null;
//		
//		if (username.getText().isBlank() && password.getText().isBlank()) {
//			try {
//			userError = driver.findElement(getLocator(userErrorLocator));
//			passError = driver.findElement(getLocator(passErrorLocator));
//			} catch(Exception e) {
//				test.log(Status.INFO, "Error not visible");
//				generateScreenshots();
//			}
//			if(userError.isDisplayed() && passError.isDisplayed()) {
//				if(userError.getText().equals(userErrorText)) {
//					if(passError.getText().equals(passErrorText)) {
//						test.log(Status.PASS, userErrorText+" Text is displayed for the blank credentials in username and"+passErrorText+ " Text is displayed for the blank credentials in password");
//						generateScreenshots();
//					}else if (!passError.getText().equals(passErrorText)) {
//						test.log(Status.FAIL, passErrorText+" Text is displayed for the blank credentials");
//						generateScreenshots();
//					}
//				}else if(!userError.getText().equals(userErrorText)) {
//					if(passError.getText().equals(passErrorText)) {
//						test.log(Status.FAIL, userErrorText+" Text is displayed for the blank credentials in username");
//						generateScreenshots();
//					}else if (!passError.getText().equals(passErrorText)) {
//						test.log(Status.FAIL, passErrorText+" Text is displayed for the blank credentials");
//						generateScreenshots();
//					}
//				}
//			}else if (userError.isDisplayed() && !passError.isDisplayed()) {
//				if(userError.getText().equals(userErrorText)) {
//					test.log(Status.PASS, userErrorText+" Text is displayed");
//					test.log(Status.FAIL, "Password error message is not displayed for blank input");
//					generateScreenshots();
//				}else if (!userError.getText().equals(userErrorText)) {
//					test.log(Status.FAIL, userError.getText()+ " Text is displayed for username error");
//					test.log(Status.FAIL, "Password error message is not displayed for blank input");
//					generateScreenshots();
//				}
//			}else if (!userError.isDisplayed() && passError.isDisplayed()) {
//				if(passError.getText().equals(passErrorText)) {
//					test.log(Status.PASS,passErrorText+ " Text is displayed for blank password credentails");
//					test.log(Status.FAIL, "username error message not displayed for blank input");
//					generateScreenshots();
//				}else if (!passError.getText().equals(passErrorText)) {
//					test.log(Status.FAIL,passError.getText()+ " Text is displayed for blank password credentails");
//					test.log(Status.FAIL, "username error message not displayed for blank input");
//					generateScreenshots();
//				}
//			}
//		}else if (username.getText().isBlank() && !password.getText().isBlank()) {
//			try {
//				userError = driver.findElement(getLocator(userErrorLocator));
//				passError = driver.findElement(getLocator(passErrorLocator));
//				} catch(Exception e) {
//					test.log(Status.INFO, "Error not visible");
//					generateScreenshots();
//				}
//			userError = driver.findElement(getLocator(userErrorLocator));
//			passError = driver.findElement(getLocator(passErrorLocator));
//			if(!passError.isDisplayed()) {
//				if(userError.isDisplayed()) {
//					if(userError.getText().equals(userErrorText)) {
//						test.log(Status.PASS,userErrorText+ " Text is displayed");
//						test.log(Status.PASS, "Password required alert is not displayed after entering password");
//						generateScreenshots();
//					}else if (!userError.getText().equals(userErrorText)) {
//						test.log(Status.PASS, "Password required alert is not displayed after entering password");
//						test.log(Status.FAIL,userErrorText+ " Text is  displayed for username blank input.");
//						generateScreenshots();
//					}
//				}else if (!userError.isDisplayed()) {
//					test.log(Status.PASS, "Password required alert is not displayed after entering password");
//					test.log(Status.FAIL, "Username required alert is not displayed for blank username input");
//					generateScreenshots();
//				}
//			}else if(passError.isDisplayed()) {
//				if(userError.isDisplayed()) {
//					if(userError.getText().equals(userErrorText)) {
//						test.log(Status.PASS,userErrorText+ " Text is displayed");
//						test.log(Status.FAIL, "Password required alert is displayed after entering password");
//						generateScreenshots();
//					}else if (!userError.getText().equals(userErrorText)) {
//						test.log(Status.FAIL, "Password required alert is displayed after entering password");
//						test.log(Status.FAIL,userErrorText+ " Text is  displayed for username blank input.");
//						generateScreenshots();
//					}
//				}else if (!userError.isDisplayed()) {
//					test.log(Status.FAIL, "Password required alert is displayed after entering password");
//					test.log(Status.FAIL, "Username required alert is not displayed for blank username input");
//					generateScreenshots();
//				}
//			}
//		}else if (!username.getText().isBlank() && password.getText().isBlank()) {
//			try {
//				userError = driver.findElement(getLocator(userErrorLocator));
//				passError = driver.findElement(getLocator(passErrorLocator));
//				} catch(Exception e) {
//					test.log(Status.INFO, "Error not visible");
//					generateScreenshots();
//				}
//			userError = driver.findElement(getLocator(userErrorLocator));
//			passError = driver.findElement(getLocator(passErrorLocator));
//			
//			if(username.getText().contains(mandatoryCharcters)) {
//				if()
//			}
//		}
//	}
	
	
	public void blankInputLoginValidation(String userLocator, String passLocator, String userErrorLocator,
          String passErrorLocator) {
		WebElement username = driver.findElement(getLocator(userLocator));
		WebElement password = driver.findElement(getLocator(passLocator));
		String userErrorText = "This is a required field.";
		String passErrorText = "This is a required field.";
		//String mandatoryCharcters = "@"+".";
		WebElement userError = null;
		WebElement passError = null;
		
		if (username.getText().isBlank() && password.getText().isBlank()) {
			try {
					userError = driver.findElement(getLocator(userErrorLocator));
				} catch(Exception e) {
					test.log(Status.FAIL, "Username Error not visible");
					generateScreenshots();
				}
			try {
				passError = driver.findElement(getLocator(passErrorLocator));
			}catch(Exception e) {
				test.log(Status.FAIL, "Password error is not visible.");
				generateScreenshots();
			}
			if(userError.isDisplayed() && passError.isDisplayed()) {
				if(userError.getText().equals(userErrorText) && passError.getText().equals(passErrorText)) {
					test.log(Status.PASS,userErrorText+ " Text is displayed for empty username and password input");
					generateScreenshots();
				}else if(!userError.getText().equals(userErrorText) && !passError.getText().equals(passErrorText)) {
					test.log(Status.FAIL,userErrorText+ " "+passErrorText+" Texts are displayed for empty username and password input.");
					generateScreenshots();
				}else if(!userError.getText().equals(userErrorText) && passError.getText().equals(passErrorText)) {
					test.log(Status.FAIL,passErrorText+" Error is displayed for blank input password . But"+userErrorText+" Text is displayed for the blank input in username.");
					generateScreenshots();
				}else if (userError.getText().equals(userErrorText) && passError.getText().equals(passErrorText)) {
					test.log(Status.FAIL,userErrorText+" Error is displayed for blank input username . But"+passErrorText+" Text is displayed for the blank input in password.");
					generateScreenshots();
				}
				
			}
			}
		
		
	}
	
	
	public void validateAddAddressPopup2(List<String>firstName , List<String>middleName , List<String>lasttName , List<String>phoneNumber,
			List<String>streetAddress , List<String>streetAddress2 , List<String>postalCode, List<String>city , String state , String country) {
		
		
	}
	
	public void validateAddAddressPopup(String firstName, String middleName , String lasttName, String phoneNumber,
			String streetAddress, String streetAddress2, String postalCode, String city, String state, String country) {
		
	}
	
	//Listing page validation
	
	public int getElementsCount(String locatorKey) {
	    List<WebElement> elements = driver.findElements(getLocator(locatorKey));
	    int elementsCount = elements.size();
	    log("Number of elements present are"+ elementsCount);
	    return elementsCount;
	}
	public void validateNumberOfListingPageProducts(String showingResultsLocator, Class<?> productCountLocator) {
	    WebElement e = driver.findElement(getLocator(showingResultsLocator));
	    String showingResults = e.getText();
	    int i = numberOfSubClassesInClass(productCountLocator);
	    
	    if (showingResults.contains(String.valueOf(i))) {
	        test.log(Status.PASS, "Number of products visible: " + i);
	        generateScreenshots();
	    } else {
	        test.log(Status.FAIL, "Number of products visible: " + i);
	        generateScreenshots();
	    }
	}
	
	//Guest Checkout
	
	public void validateEmailConditionText(String Locator) {
		try {
			WebElement e = driver.findElement(getLocator(Locator));
			String text = e.getText();
			String expected = "Set a password at the end of guest checkout to create an account in one easy step.";
			if(text.equals(expected)) {
				test.log(Status.PASS, text+" is displayed.");
				generateScreenshots();
			}else {
				test.log(Status.FAIL,text+" is displayed" );
				generateScreenshots();
			}
		}catch(Exception e) {
			test.log(Status.FAIL, "Email Condition text is not displayed.");
			generateScreenshots();
		}
	}

	
}
