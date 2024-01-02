#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>

#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BME280.h>

const char* ssid = "Keenetic-Home_NumONE";
const char* password = "Toop472@";

const char* serverName = "http://192.168.10.8:8189/sensors/saveDataSensor";

Adafruit_BME280 bme;
float temperature;
float humidity;
float pressure;
String jsonRequestData;

int cnt=1*60;//Timer for count seconds

void setup() {
  Serial.begin(115200);
  
  if (!bme.begin(0x76)) {
    Serial.println("Could not find a valid BME280 sensor, check wiring!");
    while (1);
  }  
  
  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
  sendDB();
  Serial.println("Timer set to 1 minute (timerDelay variable), it will take 1 minute before publishing the first reading.");
}

void sendDB()
{
  temperature = bme.readTemperature()*0.9500F;
    humidity = bme.readHumidity()*1.1833F;
    pressure = bme.readPressure()/100.0F;

    //Check WiFi connection status
    if(WiFi.status()== WL_CONNECTED){
      WiFiClient client;
      HTTPClient http;
      
      // Your Domain name with URL path or IP address with path
      http.begin(client, serverName);

      http.addHeader("Content-Type", "application/json");
      jsonRequestData = "{\"idsen\":\"";
      jsonRequestData += "1";
      jsonRequestData += "\",\"temperature\":\"";
      jsonRequestData += String(temperature, 2);
      jsonRequestData += "\",\"humidity\":\"";
      jsonRequestData += String(humidity,2);
      jsonRequestData += "\",\"pressure\":\"";
      jsonRequestData += String(pressure,2);
      jsonRequestData += "\",\"co\":\"";
      jsonRequestData += "0\"}";
      int httpResponseCode = http.POST(jsonRequestData);
     
      Serial.print("HTTP Response code: ");
      Serial.println(httpResponseCode);
        
      // Free resources
      http.end();
    }
    else {
      Serial.println("WiFi Disconnected");
    }
}

void loop() {
      Serial.println(cnt);
 
  if(cnt==0){
    Serial.println("Reset..");
    ESP.restart();
  }
 
  cnt--;
  delay(1000);
}