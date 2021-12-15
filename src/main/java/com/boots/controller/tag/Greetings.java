package com.boots.controller.tag;

import com.boots.entity.DayParts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Greetings extends SimpleTagSupport {
    private String dayPart = "";
    private String username = "";
    private StringWriter sw = new StringWriter();
    private static final String pattern = "Хорошего %s, %s";


    public void doTag() throws JspException, IOException {
        String greetingLine;
        if (dayPart != null && username != null) {
            /* Use message from attribute */
            JspWriter out = getJspContext().getOut();
            greetingLine = buildGreeting(dayPart, username);
            out.println(greetingLine);
        } else {
            /* use message from the body */
            getJspBody().invoke(sw);
            greetingLine = buildGreeting(dayPart, username);
            getJspContext().getOut().println(greetingLine);
        }
    }

    private String buildGreeting(String dayPart, String username){
        String modDayPart = "утра";
        if (dayPart.equals(DayParts.DAY.toString().toLowerCase())){
            modDayPart = "дня";
        } else if(dayPart.equals(DayParts.EVENING.toString().toLowerCase())){
            modDayPart = "вечера";
        } else if (dayPart.equals(DayParts.NIGHT.toString().toLowerCase())){
            modDayPart = "ночи";
        }
        return String.format(pattern, modDayPart, username);
    }
}
