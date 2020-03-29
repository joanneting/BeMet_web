package tw.com.business_meet.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import tw.com.business_meet.filter.WebInterceptor;




@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	/**
	 * 
	 * 模板設定
	 * setPrefix：設定前綴字
	 * setSuffix：外部訪問路徑
	 * 
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(true);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setOrder(0);
		return templateResolver;
	}
	
	
	/**
	 * 
	 * 模板設定
	 * 
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}
	
	/**
	 * 
	 * Thymeleaf 模板設定
	 * 
	 */
	@Bean 
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setCharacterEncoding("UTF-8");
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		return thymeleafViewResolver;
		
	}
	
	/**
	 * 
	 * View 設定
	 * setPrefix：設定前綴字
	 * setSuffix：設定後綴字
	 * 
	 */
    @Bean
    public InternalResourceViewResolver viewResolver(){
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/static/html/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
	
	/**
	 * 
	 * 靜態位置訪問路徑
	 * addResourceLocations：檔案放置目錄
	 * addResourceHandler：外部訪問路徑
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/images/**").addResourceLocations("file:D:\\business_meet\\images\\");
		registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\business_meet\\img\\");
		registry.addResourceHandler("/protected/**").addResourceLocations("file:D:\\business_meet\\protected\\");
	}

	/**
	 * listener，設置多次登入時，取消舊登入，配合SecurityConfig中的maximumSessions = 1
	 */
	
	 @Bean
	 public WebInterceptor demoInterceptor(){
	        return new WebInterceptor();
	    }

	 @Override
		public void addInterceptors(InterceptorRegistry registry) {
		 	InterceptorRegistration interceptorRegistration = registry.addInterceptor(demoInterceptor());
		    interceptorRegistration.addPathPatterns("/**");
		    interceptorRegistration.excludePathPatterns("/static/**");
		}
	 

}
