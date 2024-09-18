package com.appfellas.flightApi.config;

import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airline.repository.AirlineRepository;
import com.appfellas.flightApi.service.airline.service.AirlineService;
import com.appfellas.flightApi.service.airport.entity.Airport;
import com.appfellas.flightApi.service.airport.entity.embeddable.Address;
import com.appfellas.flightApi.service.airport.repository.AirportRepository;
import com.appfellas.flightApi.service.airport.service.AirportService;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.repository.UserRepository;
import com.appfellas.flightApi.service.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Random;

@Configuration
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public CommandLineRunner createAdmin(@Autowired UserService userService, @Autowired UserRepository userRepository){
        return args -> {
            User found = userService.findByEmail("ahmetcan.yalcinkaya55@gmail.com");
            if(found == null) {
                User user = new User();
                user.setFirstName("Ahmet Can");
                user.setLastName("Yalçınkaya");
                user.setEmail("ahmetcan.yalcinkaya55@gmail.com");
                user.setPassword("Ahmet123."); // TODO : passwordEncoder -> encode the password after the security;
                user.setCreatedDateTime(LocalDateTime.now());
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            } else {
                LOGGER.info("Admin account created -> Email: {} / Password: Ahmet123.", found.getEmail());
            }
        };
    }

    @Bean
    public CommandLineRunner createAirport(@Autowired AirportService airportService, @Autowired AirportRepository airportRepository){
        return args -> {
            String[] names = {"Istanbul Airport", "Sabiha Gokcen International Airport", "Antalya Airport", "Izmir Adnan Menderes Airport", "Ankara Esenboga Airport",
                    "London Heathrow Airport", "Paris Charles de Gaulle Airport", "Amsterdam Schiphol Airport", "Dubai International Airport",
                    "New York John F. Kennedy International Airport", "Los Angeles International Airport", "Tokyo Haneda Airport", "Singapore Changi Airport",
                    "Sydney Kingsford Smith Airport", "Hong Kong International Airport", "Munich Airport", "Zurich Airport", "Rome Leonardo da Vinci International Airport",
                    "Beijing Capital International Airport", "Moscow Sheremetyevo International Airport"};
            String[] IATACodes = {"IST", "SAW", "AYT", "ADB", "ESB", "LHR", "CDG", "AMS", "DXB", "JFK", "LAX", "HND", "SIN",
                    "SYD", "HKG", "MUC", "ZRH", "FCO", "PEK", "SVO"};
            String[] cities = {
                    "Istanbul", "Istanbul", "Antalya", "Izmir", "Ankara", "London", "Paris", "Amsterdam", "Dubai", "New York", "Los Angeles", "Tokyo", "Singapore",
                    "Sydney", "Hong Kong", "Munich", "Zurich", "Rome", "Beijing", "Moscow"};
            String[] countries = {
                    "Turkey", "Turkey", "Turkey", "Turkey", "Turkey", "United Kingdom", "France", "Netherlands", "United Arab Emirates", "United States", "United States",      // United States
                    "Japan", "Singapore", "Australia", "Hong Kong", "Germany", "Switzerland", "Italy", "China", "Russia"};
            String[] countryCodes = {"TR", "TR", "TR", "TR", "TR", "GB", "FR", "NL", "AE", "US", "US", "JP", "SG", "AU", "HK", "DE", "CH", "IT", "CN","RU"};
            for (int i = 0; i < names.length; i++) {
                Airport found = airportService.findBYIATACode(IATACodes[i]);
                if (found == null) {
                    Airport airport = new Airport();
                    airport.setName(names[i]);
                    airport.setIATACode(IATACodes[i]);
                    Address address = new Address();
                    address.setCity(cities[i]);
                    address.setCountry(countries[i]);
                    address.setCountryCode(countryCodes[i]);
                    airport.setAddress(address);
                    airportRepository.save(airport);
                } else {
                    LOGGER.info("{} -> Airport already created!", names[i]);
                }
            }
        };
    }

    @Bean
    public CommandLineRunner airlinesCreator(@Autowired AirlineService airlineService, @Autowired AirlineRepository airlineRepository){
        return args -> {
            String[] IATACodes = {"TK", "LH", "EK", "QR", "AA", "BA", "AF", "KL", "SQ", "LX", "JL", "DL", "SU", "EY", "CX"};
            String[] names = {"Turkish Airlines", "Lufthansa", "Emirates", "Qatar Airways", "American Airlines", "British Airways", "Air France", "KLM Royal Dutch Airlines", "Singapore Airlines", "Swiss International Air Lines", "Japan Airlines", "Delta Air Lines", "Aeroflot", "Etihad Airways", "Cathay Pacific"};
            int[] airlineCodes = {123, 456, 789, 321, 654, 987, 135, 246, 357, 468, 579, 681, 792, 913, 104};
            String[] cities = {"Istanbul", "Frankfurt", "Dubai", "Doha", "Dallas", "London", "Paris", "Amsterdam", "Singapore", "Zurich", "Tokyo", "Atlanta", "Moscow", "Abu Dhabi", "Hong Kong"};
            String[] countries = {"Turkey", "Germany", "United Arab Emirates", "Qatar", "United States", "United Kingdom", "France", "Netherlands", "Singapore", "Switzerland", "Japan", "United States", "Russia", "United Arab Emirates", "Hong Kong"};
            String[] countryCodes = {"TR", "DE", "AE", "QA", "US", "GB", "FR", "NL", "SG", "CH", "JP", "US", "RU", "AE", "HK"};
            for (int i = 0; i < IATACodes.length; i++){
                Airline found = airlineService.findByIATACode(IATACodes[i]);
                if (found == null) {
                    Airline airline = new Airline();
                    airline.setName(names[i]);
                    airline.setAirlineCode(airlineCodes[i]);
                    airline.setAirlineIATACode(IATACodes[i]);
                    Address country = new Address();
                    country.setCity(cities[i]);
                    country.setCountry(countries[i]);
                    country.setCountryCode(countryCodes[i]);
                    airline.setCountry(country);
                    airlineRepository.save(airline);
                } else {
                    LOGGER.info("{} -> Airline has been added!", IATACodes[i]);
                }
            }
        };
    }

    private Integer randomIndex() {
        Random random = new Random();
        return random.nextInt(11);
    }
}
