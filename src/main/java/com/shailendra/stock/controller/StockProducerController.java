package com.shailendra.stock.controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shailendra.stock.StockContants;
import com.shailendra.stock.model.StockCandle;


@RestController
@RequestMapping("/api/v1")
public class StockProducerController {

	@Autowired
	private KafkaTemplate<String, StockCandle> kafkaTemplate;

	@GetMapping("/generate")
	public void generateStockMessages() throws InterruptedException { 
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
		    @Override
		    public void run() {
		    	StockCandle stockCandle = new StockCandle();
		    	stockCandle.setSymbol(String.valueOf(StockContants.STOCK_SYMBOL.charAt(new Random().nextInt(10))));
		    	stockCandle.setExchange(StockContants.EXCHANGE);
		    	stockCandle.setTime(System.currentTimeMillis()); 
		    	stockCandle.setPrice(ThreadLocalRandom.current().nextDouble(1, 100)); 
		    	stockCandle.setClosePrice(ThreadLocalRandom.current().nextDouble(1, 100)); 
		    	
		        kafkaTemplate.send(new ProducerRecord<String, StockCandle>(StockContants.STOCK_TOPIC, 
		        		stockCandle.getSymbol(), stockCandle));
		    	System.out.println("Message Generated ");
		    }
		};
		timer.schedule(timerTask, 0, 10000);
	}

}
