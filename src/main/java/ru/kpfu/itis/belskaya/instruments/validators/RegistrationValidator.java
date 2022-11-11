package ru.kpfu.itis.belskaya.instruments.validators;

import java.util.List;

public class RegistrationValidator {

    private static final String NAME_REGEX = "[A-Z][a-z]{1,30}";
    private static final String EMAIL_REGEX = "[A-Za-z0-9-]{2,50}@[a-z]{2,20}.[a-z]{2,4}";
    private static final String PASSWORD_REGEX = "([a-z]+[A-Z]+[0-9]+|[a-z]+[0-9]+[A-Z]+|[A-Z]+[a-z]+[0-9]+|[A-Z]+[0-9]+[a-z]+|[0-9]+[a-z]+[A-Z]+|[0-9]+[A-Z]+[a-z]+)";
    private static final String PHONE_REGEX = "[0-9]{11}";
    private static final String WRONG_NAME_ANSWER = "The user name must contain only Latin letters";
    private static final String WRONG_EMAIL_ANSWER = "The email is not correct";
    private static final String WRONG_PASSWORD_ANSWER = "The password must contain only Latin letters and number in both registry";
    private static final String WRONG_CITY_ANSWER = "The city is empty";
    private static final String WRONG_SUBJECT_ANSWER = "You have to choose subjects";
    private static final String WRONG_PHONE_ANSWER = "The format of the phone number recording is incorrect";
    private static final String EMPTY_FIELD_ANSWER = "You have to fill all forms";
    public static final String SUCCESS_VALIDATION_ANSWER = "Data is correct";

    public static String generateTutorAnswer(String name, String email, String password, String city, List<String> subjects, String phone) {
        if (name == null || email == null || password == null || city == null || phone == null
                || name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            return EMPTY_FIELD_ANSWER;
        } else if (!name.matches(NAME_REGEX)) {
            return WRONG_NAME_ANSWER;
        } else if (!email.matches(EMAIL_REGEX)) {
            return WRONG_EMAIL_ANSWER;
        } else if (!password.matches(PASSWORD_REGEX)) {
            return WRONG_PASSWORD_ANSWER;
        } else if (city.isEmpty()) {
            return WRONG_CITY_ANSWER;
        } else if (subjects.size() == 0) {
            return WRONG_SUBJECT_ANSWER;
        } else if (!phone.matches(PHONE_REGEX)) {
            return WRONG_PHONE_ANSWER;
        } else {
            return SUCCESS_VALIDATION_ANSWER;
        }
    }

    public static String generateStudentAnswer(String name, String email, String password, String city, String phone) {
        if (name == null || email == null || password == null || city == null || phone == null
                || name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            return EMPTY_FIELD_ANSWER;
        } else if (!name.matches(NAME_REGEX)) {
            return WRONG_NAME_ANSWER;
        } else if (!email.matches(EMAIL_REGEX)) {
            return WRONG_EMAIL_ANSWER;
        } else if (!password.matches(PASSWORD_REGEX)) {
            return WRONG_PASSWORD_ANSWER;
        } else if (city.isEmpty()) {
            return WRONG_CITY_ANSWER;
        } else if (!phone.matches(PHONE_REGEX)) {
            return WRONG_PHONE_ANSWER;
        } else {
            return SUCCESS_VALIDATION_ANSWER;
        }
    }


}
