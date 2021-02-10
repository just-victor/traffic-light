# traffic-light

test application simulating road traffic

**Requirements**

* Java Runtime Environment (JRE) 11
* Maven

**Running**
```
mvn spring-boot:run
```

* Allowed endpoints
```
GET localhost:8080/traffic
GET localhost:8080/traffic?from=date&to=date
```

* Examples
date format: `yyyy-mm-ddThh:mm:ss.fffffffffZ`
```
localhost:8080/traffic?from=2021-02-09T07:47:25.573600Z&to=2021-02-09T07:47:25.573600Z
```

**Common configuration (environment variables)**
```
TRAFFIC_COLOR_DELAY:20000
```
the variable is responsible for the frequency of the traffic light color change in millis

```
NUMBER_TRAFFIC_GENERATION_THREADS:5
```
the variable is responsible for the number of threads which generate new car
```
IS_ASYNC:true
```
the variable is responsible for the application running mode, `true` - possible collisions, `false` - sync mode without collisions
