# magic-mirror
Magic Mirror Backend

A REST service using Spring Boot that serves (in no particular order)

1. Local weather information - /weather/{lat,lng}
  1. Current Weather
  2. Current Weather with a description
  3. Hourly/Daily Max Summary
  4. Today's Max/Min Temp
  5. Sunrise/Sunset time
  6. Future cast
  7. Alerts if any
2. Calendar Event - TODO
3. Unread email list - TODO
4. Tweets - TODO
5. Facebook Notification display - TODO
6. News - /news
   1. News Headlines
   2. Short description of headlines - support added but this takes up too much space
7. Video - TODO
8. Time - Current Date/Time - this is better handled on the client


# Microsevices Everywhere
All backend services are REST endpoints created using Spring Boot:
1. Mausam - A RESTful weather service that pulls data from forecast.io
2. Khabarnama - A RESTful news service that pulls data from NyTimes

# Chowkidaar
API Gateway is based on Netflix Zuul. The API Gateway also acts as a service aggregator and exposes a single endpoint for a front-end service.

# Service Discovery
Service discovery is based on Netflix Eureka


