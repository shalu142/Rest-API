# Rest-API

COMPANY: CODTECH IT SOLUTIONS PVT. LTD.

NAME: SHALU

INTERN ID: CT06DF405

DOMAIN: JAVA PROGRAMMING

DURATION: 6 WEEKS

MENTOR: NEELS SANTHOSH KUMAR

DESCRITON: 
WeatherFetcher
WeatherFetcher is a Java-based command-line application that allows users to fetch current weather information for any city in the world using the OpenWeatherMap API. It uses the Geocoding API to convert the city and country input into latitude and longitude, which are then used to query the Weather API for real-time data such as temperature, humidity, and weather descriptions. This project is ideal for beginners looking to understand API integration, JSON parsing, and HTTP requests in Java.

Features
Input any city name and country code (e.g., delhi, in or new york, us)

Uses OpenWeatherMap Geocoding API to fetch coordinates

Retrieves real-time weather data based on coordinates

Displays temperature in Celsius, humidity, and a short weather description

Simple command-line interface

Uses Gson library to parse JSON responses

Works globally for all valid cities and countries

Requirements
Java Development Kit (JDK) 11 or above

Gson library (e.g., gson-2.10.1.jar)

OpenWeatherMap API key (free from openweathermap.org)

Internet connection

Setup Instructions
Download or clone the project files.

Ensure the folder structure is like:

vbnet
Copy
Edit
Codetech/
├── lib/
│   └── gson-2.10.1.jar
├── WeatherFetcher.java
Register at https://openweathermap.org/api to get a free API key.

Open WeatherFetcher.java and replace the API_KEY string with your own key:


private static final String API_KEY = "your_api_key_here";
Open the terminal in the Codetech directory and compile the program using:

javac -cp ".;./lib/gson-2.10.1.jar" WeatherFetcher.java
Run the program using:

bash
Copy
Edit
java -cp ".;./lib/gson-2.10.1.jar" WeatherFetcher
(Use : instead of ; on Linux/macOS for the classpath separator.)

Example Output
vbnet
Copy
Edit
Enter city name: new york
Enter country code (e.g., IN for India): us

Weather Info for new york:
Temperature: 25.3 °C
Humidity: 60 %
Description: scattered clouds
Common Issues
ClassNotFoundException (JsonParser)
Make sure the Gson JAR is properly included in your classpath during both compilation and execution.

HTTP 400 Error
This usually means the city or country code format is incorrect. Use lowercase and avoid special characters or spaces.

Project Structure
WeatherFetcher.java – Main source file

lib/gson-2.10.1.jar – Gson JSON parsing library


OUTPUT: 

