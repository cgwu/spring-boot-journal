package com.apress.spring;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.apress.spring.domain.Testjson2;
import com.apress.spring.rabbitmq.Producer;
import com.apress.spring.repository.Testjson2Repository;
import com.apress.spring.repository.TestjsonRepository;
import com.apress.spring.service.JournalService;
import com.apress.spring.service.LayoutImpl;


@EnableScheduling
@SpringBootApplication
public class SpringBootJournalApplication extends SpringBootServletInitializer 
		implements CommandLineRunner, ApplicationRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringBootJournalApplication.class);
	
	//可在在servlet中部署的war包  
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(SpringBootJournalApplication.class);  
    }
    
    /***********  CXF Config Begin ************/
    /*
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	@Bean
	public LayoutImpl layout() {
		return new LayoutImpl();
	}
	*/
	@Autowired
	private Bus bus;
	@Bean
	public Endpoint endpoint() {
//		EndpointImpl endpoint = new EndpointImpl(springBus(), layout());
		EndpointImpl endpoint = new EndpointImpl(bus, new LayoutImpl());
		endpoint.publish("/layout");
//		 endpoint.getInInterceptors().add(new AuthInterceptor());
		return endpoint;
	}
	/**********  CXF Config End *************/
    
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("lang");
		resolver.setDefaultLocale(Locale.CHINA);		// 设置默认区域.
		resolver.setCookieMaxAge(60*60*24);		// 设置cookie有效期(seconds).
		return resolver;
	}

	/*
	// 设置SessionTrackingMode未起作用.
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.URL));
				SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
				sessionCookieConfig.setName("xid");
				sessionCookieConfig.setHttpOnly(true);
			}
		};
	}
	*/
	
	/*
	//初始数据
	@Bean
	InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(new Journal("开始学习Spring Boot!", "Today I will learn Spring	Boot", "01/01/2016"));
			repo.save(new Journal("简单Spring-boot项目:Simple Spring Boot Project", "I will do my first Spring	Boot Project",
					"01/02/2016"));
			repo.save(new Journal("Spring Boot Reading", "Read more about Spring	Boot", "02/01/2016"));
			repo.save(new Journal("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "03/01/2016"));
		};
	}
	
	@Bean
	InitializingBean initJsonData(TestjsonRepository repo) {
		return () -> {
			repo.save(new Testjson("{\"name\":\"吴cg\",\"age\":130}", new BigDecimal(3.1415926)));
			repo.save(new Testjson("[1,2,3,4,5,987]"));
			repo.save(new Testjson("{}"));
			repo.save(new Testjson("[]", BigDecimal.TEN));
			repo.save(new Testjson(null));
			repo.save(new Testjson("", BigDecimal.ONE));
		};
	}
	
	@Bean
	InitializingBean initJsonData2(Testjson2Repository repo) {
		return () -> {
			repo.save(new Testjson2("{\"name\":\"吴cg\",\"age\":130}", new BigDecimal(3.1415926),
					Arrays.asList("好答案", "aa", "BB", "c#")));
			repo.save(new Testjson2("[1,2,3,4,5,987]", new BigDecimal(12345), Arrays.asList("好答案1")));
			repo.save(new Testjson2("{}", new BigDecimal(12)));
			repo.save(new Testjson2("[]", BigDecimal.TEN, Arrays.asList("好答案", "aa", "BB", "c#!@#$")));
			repo.save(new Testjson2(null));
//			repo.save(new Testjson2("", BigDecimal.ONE));	// ERROR:不允许为空串
		};
	}
	*/
	
	/*
	// 没起作用：
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
//	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
	    factory.setPort(9000);
	    factory.setSessionTimeout(10, TimeUnit.MINUTES);
	    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
	    return factory;
	}
	*/
	
	@Autowired
	JournalService service;
	
	@Autowired
	TestjsonRepository jsonRepo;
	
	@Autowired
	Testjson2Repository json2Repo;

	public static void main(String[] args) {
		// 法1：
//		SpringApplication.run(SpringBootJournalApplication.class, args);
		// 法2：
		/*
		SpringApplication app = new SpringApplication(SpringBootJournalApplication.class);
		app.setBanner(new Banner(){

			@Override
			public void printBanner(Environment arg0, Class<?> arg1, PrintStream out) {
				out.print("\n\n这是我的自定义BANNER!\n\n");
			}});
		app.run(args);
		*/
		// 法3：
		new SpringApplicationBuilder()
//		.bannerMode(Banner.Mode.OFF)		// 禁止显示Banner信息
		.sources(SpringBootJournalApplication.class)
//		.logStartupInfo(false)	// getter方法来生成 default: true
//		.profiles("prod","cloud")
//		.web(false)
		.run(args);
	}
	
	@Bean
	String info(){
		return "Just a simple String bean,只是一个简单的字符串Bean!";
	}
	
	@Autowired
	String info;
	
	@Value("${server.port}")
	private String serverPort;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("## > ApplicationRunner Implementation..., SERVER PORT: " + serverPort);
		log.info("Accessing the Info bean: " + info);
		args.getNonOptionArgs().forEach(file -> log.info(file));
	}

	@Value("${myqueue}")
	String queue;
	
	@Bean
	Queue queue(){
		return new Queue(queue,false);
	}
	
	@Autowired 
	Producer producer;
	
	@Bean
	CommandLineRunner sender(Producer producer) {
		return args -> {
			producer.sendTo(queue, "Hello World");
		};
	}
	
	/*
	 @EnableScheduling. This annotation will tell (via auto-configuration) the
	Spring container that the org.springframework.scheduling.annotation.
	ScheduleAnnotationBeanPostProcessor class needs to be created. It will register all
	the methods annotated with @Scheduled to be invoked by a org.springframework.
	scheduling.TaskScheduler interface implementation according to the fixedRate,
	fixedDelay, or cron expression in the @Scheduled annotation.
	*/
//	@Scheduled(fixedDelay = 500L)
//	public void sendMessages() {
//		producer.sendTo(queue, "Hello World at " + new Date());
//	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("## > CommandLineRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		for (String arg : args)
			log.info(arg);
		
		log.info("@@ Inserting Data....");
//		service.insertData();
		log.info("@@ findAll() call...");
		service.findAll().forEach(entry -> log.info(entry.toString()));
		
		log.info("读取test_json测试:");
		jsonRepo.findAll().forEach(e -> log.info(e.toString()));
		
		log.info("读取testjson2测试:");
		json2Repo.findAll().forEach(e -> log.info(e.toString()));
	}
	
	
	@Value("${topic}")
	String topic;

	@Bean
	CommandLineRunner sendRedisMessage(com.apress.spring.redis.Producer producer) {
		return args -> {
			producer.sendTo(topic, "Redis消息:Spring Boot rocks with Redis messaging!");
		};
	}
	
}

/*
@Component
class MyComponent {
	private static final Logger log = LoggerFactory.getLogger(MyComponent.class);
	
	@Autowired
	public MyComponent(ApplicationArguments args) {
		boolean enable = args.containsOption("enable");
		if(enable) log.info("## > You are enable!");
		List<String> _args = args.getNonOptionArgs();
		log.info("## > extra args ...");
		if(!_args.isEmpty()) _args.forEach(file -> log.info(file));
	}
}
*/

