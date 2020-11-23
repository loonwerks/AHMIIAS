package com.soartech.simple.input;

import org.jsoar.kernel.events.BeforeInitSoarEvent;
import org.jsoar.kernel.events.InputEvent;
import org.jsoar.kernel.io.InputOutput;
import org.jsoar.kernel.io.InputWme;
import org.jsoar.kernel.io.InputWmes;
import org.jsoar.util.Arguments;
import org.jsoar.util.events.SoarEvent;
import org.jsoar.util.events.SoarEventListener;

import com.google.common.util.concurrent.AtomicDouble;

public class DoubleInput {
	
	private final InputOutput io;
	private final InitSoarListener initListener;
	private final InputListener inputListener;
	private InputWme wme;
	
	private String name;
	private AtomicDouble value = new AtomicDouble(0.0);
	private Double updatedValue = 0.0;
	
	public DoubleInput(InputOutput io, String name, Double value) {
		Arguments.checkNotNull(io,  "io");
		this.io = io;
		this.name = name;
		this.value.set(value);
		this.initListener = new InitSoarListener();
		this.inputListener = new InputListener();
		this.io.getEvents().addListener(InputEvent.class,  inputListener);
		this.io.getEvents().addListener(BeforeInitSoarEvent.class,  initListener);
	}
	
	public void setValue(Double value) {
		this.value.set(value);
	}

	public Double getValue() {
		return this.value.get();
	}
	
	private class InputListener implements SoarEventListener {
		
		@Override
		public void onEvent(SoarEvent event) {
			if (wme == null) {
				wme = InputWmes.add(io, name, io.asWmeFactory().getSymbols().createDouble(value.get()));
				updatedValue = value.get();
			} else if (value.get() != updatedValue) {
				wme = InputWmes.update(wme, io.asWmeFactory().getSymbols().createDouble(value.get()));
				updatedValue = value.get();
			}
		}
	}
	
	private class InitSoarListener implements SoarEventListener {
		
		@Override
		public void onEvent(SoarEvent event) {
			wme = null;
		}
	}
}
