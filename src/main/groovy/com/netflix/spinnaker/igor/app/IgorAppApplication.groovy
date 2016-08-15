package com.netflix.spinnaker.igor.app
import com.netflix.config.ConfigurationManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication(
		exclude = GroovyTemplateAutoConfiguration,
		scanBasePackages = ['com.netflix.spinnaker.igor', 'com.netflix.spinnaker.config', 'com.netflix.spinnaker.igor.health'])
class IgorAppApplication {

	static final Map<String, String> DEFAULT_PROPS = [
			'netflix.environment'    : 'test',
			'netflix.account'        : '${netflix.environment}',
			'netflix.stack'          : 'test',
			'spring.config.location' : '${user.home}/.spinnaker/',
			'spring.application.name': 'igor',
			'spring.config.name'     : 'spinnaker,${spring.application.name}',
			'spring.profiles.active' : '${netflix.environment},local'
	]

	static {
		ConfigurationManager.loadCascadedPropertiesFromResources("hystrix")
	}


	static void main(String[] args) {
		new SpringApplicationBuilder().properties(DEFAULT_PROPS).sources(IgorAppApplication).run(args)
	}
    }
