package com.citrix.gotowebinar.environment;
public enum Environment {

	QA("qa"), PROD("prod"), STAGE("stage"),DEV("dev"),PERF("perf");

	private String environment;

	private Environment(String env) {
		environment = env;
	}

	public String getEnvironment() {
		return environment;
	}

	public Environment parse(String environment) {
		switch (environment) {
		case "QA":
		case "qa":
			return QA;
		case "PROD":
		case "prod":
			return PROD;
		case "stage":
		case "STAGE":
			return STAGE;
		case "dev":
		case "DEV":
			return DEV;
		case "perf":
		case "PERF":
			return PERF;
		default:
			return QA;
		}
	}

}