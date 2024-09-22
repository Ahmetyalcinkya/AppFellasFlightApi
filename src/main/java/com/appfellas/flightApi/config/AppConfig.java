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
import com.appfellas.flightApi.service.flight.dto.input.AirCraftTypeInput;
import com.appfellas.flightApi.service.flight.dto.input.FlightInput;
import com.appfellas.flightApi.service.flight.dto.input.FlightRouteInput;
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
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.*;

@Configuration
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Order(1)
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
    @Order(2)
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
    @Order(3)
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
                user.setRole(Role.USER);
                userRepository.save(user);
            } else {
                LOGGER.info("App Fellas user account created -> Email: {} / Password: APPFELLASUSER", found.getEmail());
            }
        };
    }

    @Bean
    @Order(4)
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
    @Order(5)
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
    @Order(6)
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
            LocalDateTime[] scheduledDateTimes = {LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(14, 0)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(18, 0)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(8, 30)), LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(11, 45)),
                    LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(14, 50)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(10, 30)),
                    LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(18, 40)), LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(5, 50)),
                    LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(8, 20)), LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(13, 30))};

            LocalDate[] scheduleDates = {LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
                    LocalDate.now().plusDays(2), LocalDate.now().plusDays(2), LocalDate.now().plusDays(3),
                    LocalDate.now().plusDays(3), LocalDate.now().plusDays(3), LocalDate.now().plusDays(4), LocalDate.now().plusDays(4)};

            LocalTime[] scheduleTimes = {LocalTime.of(14, 0), LocalTime.of(18, 0), LocalTime.of(8, 30),
                    LocalTime.of(11, 45), LocalTime.of(14, 50), LocalTime.of(10, 30),
                    LocalTime.of(18, 40), LocalTime.of(5, 50), LocalTime.of(8, 20),
                    LocalTime.of(13, 30)};
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
    @DependsOn("flightCreator")
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

    @Bean
    @Order(7)
    public CommandLineRunner fetchFlights(@Autowired RestTemplate restTemplate, @Autowired FlightService flightService) {
        return args -> {
            String url = "https://api.schiphol.nl/public-flights/flights?includedelays=false&page=0&sort=+scheduleTime";
            HttpHeaders headers = new HttpHeaders();
            headers.set("app_id", "aa970d78");
            headers.set("app_key", "41f2bf667160dd29fed8f2a51c1ea390");
            headers.set("ResourceVersion", "v4");
            String[] IATACodes = {"TK", "AA", "LH", "BA", "DL", "TK", "TK", "KL", "AA", "TK"};

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Object body = response.getBody();
                System.out.println(body);
                LinkedHashMap<String, ArrayList<Map<String, Object>>> flights = (LinkedHashMap<String, ArrayList<Map<String, Object>>>) body;
                if (flights != null) {
                    ArrayList<Map<String, Object>> fetchedFlights = flights.get("flights");
                    for (int i = 0; i < fetchedFlights.size(); i++) {
                        Map<String, Object> data = fetchedFlights.get(i);
                        FlightInput flightInput = new FlightInput();
                        for (Map.Entry<String, Object> flight : data.entrySet()) {
                            String key = flight.getKey();
                            Object value = flight.getValue();
                            System.out.println("Key: " + key + "Value: " + value);
                            if (key.equalsIgnoreCase("lastUpdatedAt")) {
                                OffsetDateTime offsetDateTime = OffsetDateTime.parse((String) value);
                                LocalDateTime lastUpdatedAt = offsetDateTime.toLocalDateTime();
                                flightInput.setLastUpdatedAt(lastUpdatedAt);
                            }
                            if (key.equalsIgnoreCase("actualLandingTime")) {
                                OffsetDateTime offsetDateTime = OffsetDateTime.parse((String) value);
                                LocalDateTime actualLandingTime = offsetDateTime.toLocalDateTime();
                                flightInput.setActualLandingTime(actualLandingTime);
                            }
                            if (key.equalsIgnoreCase("aircraftType")) {
                                Map<String, String> aircraftType = (LinkedHashMap<String, String>) value;
                                AirCraftTypeInput airCraftTypeInput = new AirCraftTypeInput();
                                if (aircraftType.containsKey("iataMain"))
                                    airCraftTypeInput.setIataMain(aircraftType.get("iataMain"));
                                if (aircraftType.containsKey("iataSub"))
                                    airCraftTypeInput.setIataSub(aircraftType.get("iataSub"));
                                flightInput.setAirCraftType(airCraftTypeInput);
                            }
                            if (key.equalsIgnoreCase("estimatedLandingTime")) {
                                OffsetDateTime offsetDateTime = OffsetDateTime.parse((String) value);
                                LocalDateTime estimatedLandingTime = offsetDateTime.toLocalDateTime();
                                flightInput.setEstimatedLandingTime(estimatedLandingTime);
                            }
                            if (key.equalsIgnoreCase("expectedTimeOnBelt")) {
                                OffsetDateTime offsetDateTime = OffsetDateTime.parse((String) value);
                                LocalDateTime expectedTimeOnBelt = offsetDateTime.toLocalDateTime();
                                flightInput.setExpectedTimeOnBelt(expectedTimeOnBelt);
                            }
                            flightInput.setFlightDirection(Math.round(Math.random() * 2) == 1 ? "D" : "A");
                            String IATACode = IATACodes[randomIndex()];
                            flightInput.setPrefixIATA(IATACode);
                            if (key.equalsIgnoreCase("flightNumber")) {
                                flightInput.setFlightNumber((Integer) value);
                                flightInput.setFlightName(IATACode + flightInput.getFlightNumber());
                            }
                            flightInput.setOperationalFlight(false);
                            if (key.equalsIgnoreCase("scheduleDateTime")) {
                                OffsetDateTime offsetDateTime = OffsetDateTime.parse((String) value);
                                LocalDateTime scheduleDateTime = offsetDateTime.toLocalDateTime();
                                flightInput.setScheduledDateTime(scheduleDateTime);
                            }
                            if (key.equalsIgnoreCase("scheduleDate"))
                                flightInput.setScheduleDate(LocalDate.parse((String) value));
                            if (key.equalsIgnoreCase("scheduleTime"))
                                flightInput.setScheduleTime(LocalTime.parse((String) value));
                            if (key.equalsIgnoreCase("terminal")) flightInput.setTerminal((Integer) value);
                            String[] departureAirports = {"SAW", "JFK", "MUC", "LHR", "LAX", "SAW", "IST", "AMS", "JFK", "AYT"};
                            String[] arrivalAirports = {"MUC", "DXB", "CDG", "SIN", "ZRH", "SVO", "HKG", "ESB", "SAW", "DXB"};
                            FlightRouteInput flightRouteInput = new FlightRouteInput();
                            flightRouteInput.setEu("S");
                            flightRouteInput.setVisa(false);
                            flightRouteInput.setDepartureIATACode(arrivalAirports[randomIndex()]);
                            flightRouteInput.setArrivalIATACode(departureAirports[randomIndex()]);
                            flightInput.setRoute(flightRouteInput);
                        }
                        flightService.save(flightInput);
                    }
                } else {
                    LOGGER.info("Flights is empty!");
                }
            } else {
                LOGGER.info("An error occurred while fetching the flight data!");
            }
        };
    }

    private Integer randomIndex() {
        Random random = new Random();
        return random.nextInt(10);
    }
}
