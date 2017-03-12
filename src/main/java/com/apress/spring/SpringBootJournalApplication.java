package com.apress.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.apress.spring.domain.Journal;
import com.apress.spring.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication implements CommandLineRunner, ApplicationRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringBootJournalApplication.class);
	
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
		.bannerMode(Banner.Mode.OFF)
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

	@Override
	public void run(String... args) throws Exception {
		log.info("## > CommandLineRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		for (String arg : args)
			log.info(arg);
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

