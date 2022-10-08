# Coinpayments Cryptocurrency Payment Gateway Integration with Spring Boot

Example how to integrate Coinpayments with a spring boot app.

## How to run the project?

- Go to your Coinpayments account and generate a new private & public keys
- Replace on `application.yml` the properties `public-key` and `private-key` with your keys
- Run `gradle clean build`
- Start the `CoinpaymentsApplication` project

## Technologies & Tools

- Java 11
- Gradle 7.1.1
- Spring Boot 2.6.3
- FeignClient from Open Feign

## Testing & Coverage

- Unit Tests
- Mutation Tests
- Integration Tests
- Wiremock used to mock HTTP requests to Coinpayments
- 100% Testing Coverage

## Project Explanation

Simple SpringBoot app that exposes an endpoint to generate a new cryptocurrency wallet.
Coinpayments platform is used to generate new cryptocurrencies wallets.

In this project you can find how to interact with Coinpayments API through Open Feign in Spring Boot, how to build custom requests interceptors to automatically generate the HMAC header, how to handle Coinpayments responses, how to mock the http calls with Wiremock and how to test your service to get 100% test coverage (Unit, mutations and integrations tests).