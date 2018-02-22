package com.tbc.playarea.rxjava;

import rx.AsyncEmitter;
import rx.Observable;
import rx.observables.ConnectableObservable;

public class Example {
	public static void main(String[] args) {
		Feed feed = new Feed();
		Observable<PriceTick> observable = 
				Observable.fromEmitter((AsyncEmitter<PriceTick> emitter) -> {
					Listener<PriceTick> listener = new Listener<PriceTick>() {

						@Override
						public void priceTick(PriceTick event) {
							emitter.onNext(event);
							if(event.isLast())
								emitter.onCompleted();
						}

						@Override
						public void error(Throwable throwable) {
							emitter.onError(throwable);
						}
					};
					feed.register(listener);
				}, AsyncEmitter.BackpressureMode.BUFFER);
		ConnectableObservable<PriceTick> hotObs = observable.publish();
		hotObs.connect();
		hotObs.take(10).subscribe((priceTick) ->
	      System.out.printf("1 %s %4s %6.2f%n", priceTick.getDate(),
	        priceTick.getInstrument(), priceTick.getPrice()));

	    try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    hotObs.take(10).subscribe((priceTick) ->
	      System.out.printf("2 %s %4s %6.2f%n", priceTick.getDate(),
	        priceTick.getInstrument(), priceTick.getPrice()));
	}
}
