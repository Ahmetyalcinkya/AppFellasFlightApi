package com.appfellas.flightApi.config;

import com.appfellas.flightApi.core.enums.FlightDirection;
import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airline.repository.AirlineRepository;
import com.appfellas.flightApi.service.airline.service.AirlineService;
import com.appfellas.flightApi.service.airport.entity.Airport;
import com.appfellas.flightApi.service.airport.entity.embeddable.Address;
import com.appfellas.flightApi.service.airport.repository.AirportRepository;
import com.appfellas.flightApi.service.airport.service.AirportService;
import com.appfellas.flightApi.service.flight.entity.Flight;
import com.appfellas.flightApi.service.flight.entity.embeddable.AirCraftType;
import com.appfellas.flightApi.service.flight.entity.embeddable.FlightRoute;
import com.appfellas.flightApi.service.flight.repository.FlightRepository;
import com.appfellas.flightApi.service.flight.service.FlightService;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.repository.UserRepository;
import com.appfellas.flightApi.service.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public CommandLineRunner adminCreator(@Autowired UserService userService, @Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        return args -> {
            User found = userService.findByEmail("ahmetcan.yalcinkaya55@gmail.com");
            if (found == null) {
                User user = new User();
                user.setFirstName("Ahmet Can");
                user.setLastName("Yalçınkaya");
                user.setEmail("ahmetcan.yalcinkaya55@gmail.com");
                user.setPassword(passwordEncoder.encode("Ahmet123."));
                user.setCreatedDateTime(LocalDateTime.now());
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            } else {
                LOGGER.info("Admin account created -> Email: {} / Password: Ahmet123.", found.getEmail());
            }
        };
    }

    @Bean
    public CommandLineRunner appFellasAdminCreator(@Autowired UserService userService, @Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        return args -> {
            User found = userService.findByEmail("admin@appfellas.com");
            if (found == null) {
                User user = new User();
                user.setFirstName("Admin");
                user.setLastName("AppFellas");
                user.setEmail("admin@appfellas.com");
                user.setPassword(passwordEncoder.encode("APPFELLAS"));
                user.setCreatedDateTime(LocalDateTime.now());
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            } else {
                LOGGER.info("App Fellas admin account created -> Email: {} / Password: APPFELLAS", found.getEmail());
            }
        };
    }

    @Bean
    public CommandLineRunner appFellasUserCreator(@Autowired UserService userService, @Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        return args -> {
            User found = userService.findByEmail("user@appfellas.com");
            if (found == null) {
                User user = new User();
                user.setFirstName("User");
                user.setLastName("AppFellas");
                user.setEmail("user@appfellas.com");
                user.setPassword(passwordEncoder.encode("APPFELLASUSER"));
                user.setCreatedDateTime(LocalDateTime.now());
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            } else {
                LOGGER.info("App Fellas user account created -> Email: {} / Password: APPFELLASUSER", found.getEmail());
            }
        };
    }

    @Bean
    public CommandLineRunner airportCreator(@Autowired AirportService airportService, @Autowired AirportRepository airportRepository) {
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
            String[] countryCodes = {"TR", "TR", "TR", "TR", "TR", "GB", "FR", "NL", "AE", "US", "US", "JP", "SG", "AU", "HK", "DE", "CH", "IT", "CN", "RU"};
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
    public CommandLineRunner airlinesCreator(@Autowired AirlineService airlineService, @Autowired AirlineRepository airlineRepository) {
        return args -> {
            String[] IATACodes = {"TK", "LH", "EK", "QR", "AA", "BA", "AF", "KL", "SQ", "LX", "JL", "DL", "SU", "EY", "CX"};
            String[] names = {"Turkish Airlines", "Lufthansa", "Emirates", "Qatar Airways", "American Airlines", "British Airways", "Air France", "KLM Royal Dutch Airlines", "Singapore Airlines", "Swiss International Air Lines", "Japan Airlines", "Delta Air Lines", "Aeroflot", "Etihad Airways", "Cathay Pacific"};
            int[] airlineCodes = {123, 456, 789, 321, 654, 987, 135, 246, 357, 468, 579, 681, 792, 913, 104};
            String[] cities = {"Istanbul", "Frankfurt", "Dubai", "Doha", "Dallas", "London", "Paris", "Amsterdam", "Singapore", "Zurich", "Tokyo", "Atlanta", "Moscow", "Abu Dhabi", "Hong Kong"};
            String[] countries = {"Turkey", "Germany", "United Arab Emirates", "Qatar", "United States", "United Kingdom", "France", "Netherlands", "Singapore", "Switzerland", "Japan", "United States", "Russia", "United Arab Emirates", "Hong Kong"};
            String[] countryCodes = {"TR", "DE", "AE", "QA", "US", "GB", "FR", "NL", "SG", "CH", "JP", "US", "RU", "AE", "HK"};
            for (int i = 0; i < IATACodes.length; i++) {
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

    @Bean
    public CommandLineRunner flightCreator(@Autowired FlightService flightService, @Autowired FlightRepository flightRepository, @Autowired AirlineService airlineService) {
        return args -> {
            LocalDateTime[] lastUpdatedAts = {LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(14, 30)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(18, 45)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(9, 15)), LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(12, 10)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(15, 30)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(11, 20)),
                    LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(19, 50)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(6, 10)),
                    LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(8, 45)), LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(13, 55))};

            LocalDateTime[] actualLandingTimes = {LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(16, 30)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(20, 45)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(10, 30)), LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(14, 0)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(17, 10)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(13, 0)),
                    LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(21, 40)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(8, 30)),
                    LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(10, 50)), LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(15, 45))};
            String[] IATAMains = {"IST", "DFW", "FRA", "LHR", "ATL", "IST", "IST", "AMS", "DFW", "IST"};
            String[] IATASubs = {"FRA", "DOH", "CDG", "SIN", "ZRH", "DME", "HKG", "IST", "NRT", "DXB"};
            LocalDateTime[] estimatedLandingTimes = {LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(16, 20)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(20, 30)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(10, 20)), LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(13, 50)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(17, 0)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(12, 50)),
                    LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(21, 30)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(8, 25)),
                    LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(11, 0)), LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(15, 35))};

            LocalDateTime[] expectedTimeOnBelts = {LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(16, 45)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(21, 0)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(10, 50)), LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(14, 20)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(17, 35)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(13, 35)),
                    LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(22, 5)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(8, 40)),
                    LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(11, 10)), LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(16, 15))};
            FlightDirection[] flightDirections = {FlightDirection.A, FlightDirection.D, FlightDirection.A, FlightDirection.A, FlightDirection.A, FlightDirection.D, FlightDirection.D, FlightDirection.A, FlightDirection.D, FlightDirection.D};
            String[] flightNames = {"TK4014", "AA1578", "LH542", "BA985", "DL4532", "TK863", "TK967", "KL1923", "AA678", "TK9310"};
            int[] flightNumbers = {4014, 1578, 542, 985, 4532, 863, 967, 1923, 678, 9310};
            LocalDateTime[] scheduledDateTimes = { LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(14, 0)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(18, 0)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(8, 30)), LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(11, 45)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(14, 50)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(10, 30)),
                    LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(18, 40)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(5, 50)),
                    LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(8, 20)), LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(13, 30)) };

            LocalDate[] scheduleDates = {LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
                    LocalDate.now().plusDays(2), LocalDate.now().plusDays(2), LocalDate.now().plusDays(3),
                            LocalDate.now().plusDays(3), LocalDate.now().plusDays(3), LocalDate.now().plusDays(4), LocalDate.now().plusDays(4)};

            LocalTime[] scheduleTimes = { LocalTime.of(14, 0), LocalTime.of(18, 0), LocalTime.of(8, 30),
                    LocalTime.of(11, 45), LocalTime.of(14, 50), LocalTime.of(10, 30),
                    LocalTime.of(18, 40), LocalTime.of(5, 50), LocalTime.of(8, 20),
                    LocalTime.of(13, 30) };
            int[] capacities = {200, 250, 180, 220, 210, 230, 300, 250, 270, 240};
            int[] terminals = {1, 2, 1, 3, 2, 4, 3, 2, 1, 5};
            String[] IATACodes = {"TK", "AA", "LH", "BA", "DL", "TK", "TK", "KL", "AA", "TK"};
            Long[] prices = {500L, 600L, 450L, 700L, 550L, 500L, 800L, 650L, 750L, 600L};
            String[] departureAirports = {"SAW", "JFK", "MUC", "LHR", "LAX", "SAW", "IST", "AMS", "JFK", "AYT"};
            String[] arrivalAirports = {"MUC", "DXB", "CDG", "SIN", "ZRH", "SVO", "HKG", "ESB", "SAW", "DXB"};
            for (int i = 0; i < flightNames.length; i++) {
                Flight found = flightService.findByFlightName(flightNames[i]);
                if (found == null) {
                    Flight flight = new Flight();
                    flight.setLastUpdatedAt(lastUpdatedAts[i]);
                    flight.setActualLandingTime(actualLandingTimes[i]);
                    AirCraftType airCraftType = new AirCraftType();
                    airCraftType.setIataMain(IATAMains[i]);
                    airCraftType.setIataSub(IATASubs[i]);
                    flight.setAirCraftType(airCraftType);
                    flight.setEstimatedLandingTime(estimatedLandingTimes[i]);
                    flight.setExpectedTimeOnBelt(expectedTimeOnBelts[i]);
                    flight.setFlightDirection(flightDirections[i]);
                    flight.setFlightName(flightNames[i]);
                    flight.setFlightNumber(flightNumbers[i]);
                    flight.setOperationalFlight(false);
                    flight.setScheduledDateTime(scheduledDateTimes[i]);
                    flight.setScheduleDate(scheduleDates[i]);
                    flight.setScheduleTime(scheduleTimes[i]);
                    flight.setCapacity(capacities[i]);
                    flight.setTerminal(terminals[i]);
                    flight.setAirline(airlineService.findByIATACode(IATACodes[i]));
                    FlightRoute flightRoute = new FlightRoute();
                    flightRoute.setDepartureIATACode(departureAirports[i]);
                    flightRoute.setArrivalIATACode(arrivalAirports[i]);
                    flightRoute.setVisa(true);
                    flightRoute.setEu("5");
                    flight.setRoute(flightRoute);
                    flight.setPrice(prices[i]);
                    flightRepository.save(flight);
                } else {
                    LOGGER.info("Flight with the flightName => {} has been created.", found.getFlightName());
                }
            }
        };
    }

    @Bean
    public CommandLineRunner appFellasUserFlightAdder(@Autowired UserService userService, @Autowired FlightService flightService) {
        return args -> {
            User user = userService.findByEmail("user@appfellas.com");
            if (user != null) {
                Flight tk4014 = flightService.findByFlightName("TK4014");
                Flight lh542 = flightService.findByFlightName("LH542");
                Flight aa678 = flightService.findByFlightName("AA678");
                List<String> flightIds = new ArrayList<>();
                if (user.getFlights().size() < 3) {
                    flightIds.add(tk4014.getId());
                    flightIds.add(lh542.getId());
                    flightIds.add(aa678.getId());
                }
                userService.addFlightToUser(user.getId(), flightIds);
            } else {
                LOGGER.info("User not found with the given email: user@appfellas.com");
            }
        };
    }

    private Integer randomIndex() {
        Random random = new Random();
        return random.nextInt(11);
    }
}
