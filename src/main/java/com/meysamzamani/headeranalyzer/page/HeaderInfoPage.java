package com.meysamzamani.headeranalyzer.page;

import com.meysamzamani.headeranalyzer.domain.Header;
import com.meysamzamani.headeranalyzer.service.MailService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * The HeaderInfoPage class represents a Wicket web page that displays HTTP request header information and allows users
 * to enter an email address to receive the header information via email. The page contains a form with an email input
 * field, a submit button, and a feedback panel to display success or error messages. The header information is presented
 * in a ListView table format, showing the name and detail of each header.
 */
public class HeaderInfoPage extends WebPage {

    /**
     * Constructs a new HeaderInfoPage.
     * The page includes a form to enter an email address and send header list to this email,
     * a ListView to display header information, and a feedback panel to show success or error messages.
     */
    public HeaderInfoPage() {
        Form<String> emailForm = new Form<>("emailForm");
        emailForm.add(AttributeModifier.append("class", "email"));
        final EmailTextField emailTextField = new EmailTextField("emailInput", Model.of(""));
        emailForm.add(emailTextField);
        emailForm.add(new Button("submitBtn") {
            @Override
            public void onSubmit() {
                String email = emailTextField.getModelObject();
                if (isValidEmail(email)) {
                    try {
                        MailService.getInstance().sendMail(email, getHeaderInfo().toString(), "Header Analyzer");
                        success("Email address is valid and your email successfully sent");
                    } catch (Exception e) {
                        error("Sending email failed!");
                    }
                } else {
                    error("Invalid email address format!");
                }
            }
        });
        add(emailForm);

        add(new FeedbackPanel("feedback")
                .add(AttributeModifier.append("class", "feedbackPanel")));

        add(new ListView<Header>("listview", getHeaderInfo()) {
            protected void populateItem(ListItem<Header> item) {
                Header header = item.getModelObject();
                item.add(new Label("name", header.getName()));
                item.add(new Label("type", header.getDetail()));
            }
        }).add(AttributeModifier.append("class", "table"));
    }

    /**
     * Retrieves the HTTP request header information and returns a list of Header objects, where each Header object
     * represents a header with its name and detail.
     *
     * @return a list of Header objects containing the HTTP request header information.
     */
    private List<Header> getHeaderInfo() {
        List<Header> headers = new ArrayList<>();
        ServletWebRequest servletWebRequest = (ServletWebRequest) RequestCycle.get().getRequest();
        HttpServletRequest httpServletRequest = servletWebRequest.getContainerRequest();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = httpServletRequest.getHeader(headerName);
            headers.add(new Header(headerName, headerValue));
        }
        return headers;
    }

    /**
     * Validates whether the provided email address has a valid format.
     *
     * @param email the email address to validate.
     * @return true if the email address has a valid format, false otherwise.
     */
    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
