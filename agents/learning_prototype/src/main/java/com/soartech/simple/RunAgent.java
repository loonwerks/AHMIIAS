package com.soartech.simple;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jsoar.kernel.SoarException;
import org.jsoar.kernel.rhs.functions.RhsFunctionManager;
import org.jsoar.runtime.ThreadedAgent;
import org.jsoar.util.commands.SoarCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soartech.simple.input.DoubleInput;
import com.soartech.simple.rhs.WarnAndReceiveResponseRhsFunction;

public class RunAgent {
	
    private static final Logger LOG = LoggerFactory.getLogger(RunAgent.class);

    private ThreadedAgent agent;
    private SimpleGui gui;
    private DoubleInput x1Input, x2Input;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RunAgent();
	}
	
	public RunAgent() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Look and feel not found.  GUI probably will not work.  Bailing out.", e);
        }
        gui = new SimpleGui(this, "Agent Control");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                cleanUp();
            }
        });

        gui.setVisible(true);
        try {
			setupAgent();
		} catch (SoarException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        agent.runForever();
		
	}
	
	public void setupAgent() throws SoarException, InterruptedException {
		agent = ThreadedAgent.create("Simple Learning Agent");
		
		ClassLoader cl = this.getClass().getClassLoader();
		
		createCallbacks();
		
		agent.openDebuggerAndWait();
		
		SoarCommands.source(agent.getInterpreter(), cl.getResource("com/soartech/simple/load.soar"));
	}
	
	private void createCallbacks() {
		if (agent == null) {
			LOG.error("Cannot create callbacks for null agent");
		} else {
			RhsFunctionManager rhs = agent.getRhsFunctions();
			if (gui == null) {
				LOG.warn("Registering RHS functions with null GUI");
			}
			rhs.registerHandler(new WarnAndReceiveResponseRhsFunction(gui));
		}
		
		x1Input = new DoubleInput(agent.getInputOutput(), "x1", 0.0);
		x2Input = new DoubleInput(agent.getInputOutput(), "x2", 0.0);
	}
	
	private void cleanUp() {
		if (agent != null) {
			agent.dispose();
			agent = null;
		}
	}
	
	public void setX1Value(Double v) {
		if (x1Input != null) {
			x1Input.setValue(v);	
		}
	}

	public void setX2Value(Double v) {
		if (x2Input != null) {
			x2Input.setValue(v);
		}
	}

}
