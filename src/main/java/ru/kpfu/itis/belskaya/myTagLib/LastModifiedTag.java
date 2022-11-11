package ru.kpfu.itis.belskaya.myTagLib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

public class LastModifiedTag extends TagSupport {
    private String lastModified;

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModified() {
        return lastModified;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            writer.println(lastModifiedProcessing());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private String lastModifiedProcessing() {
        LocalDateTime lm = LocalDateTime.parse(lastModified);
        String result;
        if (lm.getYear() == LocalDateTime.now().getYear() && lm.getDayOfYear() == LocalDateTime.now().getDayOfYear()) {
            if (lm.getHour() == LocalDateTime.now().getHour() && lm.getMinute() == LocalDateTime.now().getMinute()) {
                result = "just now";
            } else if (LocalDateTime.now().getHour() == lm.getHour()) {
                int minutes = (LocalDateTime.now().getMinute() - lm.getMinute());
                result = minutes + " minutes ago";
            } else if (((LocalDateTime.now().getHour() - lm.getHour()) == 1) && (60 + LocalDateTime.now().getMinute() - lm.getMinute()) < 60) {
                int minutes =  (60 + LocalDateTime.now().getMinute() - lm.getMinute());
                result = minutes + " minutes ago";
            } else if (((LocalDateTime.now().getHour() - lm.getHour() < 7))) {
                int hours = LocalDateTime.now().getHour() - lm.getHour();
                result = hours + " hours ago";
            } else {
                result = " today at " + lm.getHour() + ":" + lm.getMinute();
            }
        } else if (lm.getYear() == LocalDateTime.now().getYear() && lm.getDayOfYear() - LocalDateTime.now().getDayOfYear() == 1) {
            result = " yesterday at " + lm.getHour() + ":" + lm.getMinute();
        } else if (lm.getYear() == LocalDateTime.now().getYear()) {
            result = " on " + getMonth(lm.getMonthValue()) + ", " + lm.getDayOfMonth() + "th at " + lm.getHour() + ":" + lm.getMinute();
        } else {
            result =  " on " + getMonth(lm.getMonthValue()) + ", " + lm.getDayOfMonth() + "th of " + " " + lm.getYear();
        }
        return action + result;

    }

    private String getMonth(int monthId) {
        String month;
        switch (monthId) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
            default:
                month = "";
        }
        return month;
    }
}
