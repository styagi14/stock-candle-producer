# stock-candle-producer

1. This is a Producer application which will be used to create a stock tick and send the data to a topic named as "stock-topic".
2. Prerequisite for running the application is

    a) Zookeeper is running on 2181 port, or else change the kafka configuration in "server.properties" file.
    b) Kafka instance is running on 9091 port
    
3. The application is spring boot application and running on port 9095
4. I have coded Web Socket as well but the tick receiving was in different format so for the time being i generated tick based on the code.
