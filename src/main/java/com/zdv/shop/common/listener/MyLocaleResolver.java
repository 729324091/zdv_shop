package com.zdv.shop.common.listener;
import java.util.Locale;  

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.springframework.web.servlet.LocaleResolver;  
 
public class MyLocaleResolver implements LocaleResolver{  
    
  private static final String I18N_LANGUAGE = "lang";  
  private static final String I18N_LANGUAGE_SESSION = "lang_session";  

  @Override  
  public Locale resolveLocale(HttpServletRequest req) {  
      String i18n_language = req.getParameter(I18N_LANGUAGE); 
      //System.out.println(i18n_language+"========1=====:"+I18N_LANGUAGE);
      Locale locale = Locale.getDefault();  
      if(!(null==i18n_language||i18n_language.equalsIgnoreCase(""))) {
    	  i18n_language = i18n_language.replaceAll(" ", "");
    	  i18n_language = i18n_language.replaceAll("-", "_");
    	  if(i18n_language.equalsIgnoreCase("zh_Hans")||i18n_language.equalsIgnoreCase("zh_Hans_CN")) {
    		  i18n_language="zh_CN";
    	  }
          String[] language = i18n_language.split("_");
          
          locale = new Locale(language[0], language[1]);  
         
          //将国际化语言保存到session  
          HttpSession session = req.getSession();  
          session.setAttribute(I18N_LANGUAGE_SESSION, locale);  
      }else {  
          //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对  
          HttpSession session = req.getSession();  
          Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);  
          if(localeInSession != null) {  
              locale = localeInSession;  
          }else {
        	  i18n_language="zh_CN";    	  
        	  String[] language = i18n_language.split("_");          
        	  locale = new Locale(language[0], language[1]); 
          }
      }  
      return locale;  
  }  

  @Override  
  public void setLocale(HttpServletRequest req, HttpServletResponse res, Locale locale) {  
        
  }  

}
