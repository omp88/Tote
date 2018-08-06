package by.epam.tote.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FooterTag extends TagSupport {

	
	@Override
	public int doEndTag() throws JspException {
		
		return EVAL_PAGE;
	}

	 /**
 	 * Custom footer tag
 	 *
 	 * @throws JspException
 	 */
	@Override
	public int doStartTag() throws JspException {
		
		String footerInfo = "<label> © 2018 ООО «CDS» - All right reserved (с).</label>";
		
		try {
			 JspWriter out = pageContext.getOut();
			out.write(footerInfo);
			 } catch (IOException e) {
			 throw new JspException(e.getMessage());
			 }
		
		return SKIP_BODY;
	}

	
}
