package com.soartech.simple.rhs;

import java.util.List;

import javax.swing.JOptionPane;

import org.jsoar.kernel.rhs.functions.AbstractRhsFunctionHandler;
import org.jsoar.kernel.rhs.functions.RhsFunctionContext;
import org.jsoar.kernel.rhs.functions.RhsFunctionException;
import org.jsoar.kernel.symbols.Symbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soartech.simple.RunAgent;
import com.soartech.simple.SimpleGui;

public class WarnAndReceiveResponseRhsFunction extends AbstractRhsFunctionHandler {
	
    private static final Logger LOG = LoggerFactory.getLogger(RunAgent.class);

	private SimpleGui gui;
	
	public WarnAndReceiveResponseRhsFunction(SimpleGui gui) {
		super("warn-and-receive-response", 1, 2);
		this.gui = gui;
	}

	@Override
	public Symbol execute(RhsFunctionContext context, List<Symbol> arguments) throws RhsFunctionException {
		Double v;
		if (arguments.get(0).asDouble() != null) {
			v = arguments.get(0).asDouble().getValue();
		} else if (arguments.get(0).asInteger() != null) {
			v = Long.valueOf(arguments.get(0).asInteger().getValue()).doubleValue();
		} else {
			LOG.error("Non-number passed to warn-and-receive-response. Defaulting to 0.0");
			v=0.0;
		}
		String result;
		if (arguments.size() == 2 && arguments.get(1).asString() != null &&
				arguments.get(1).asString().getValue().equals("forced")) {
			JOptionPane.showMessageDialog(gui, 
					"Sensor values differ by " + v, 
					"SENSOR MISMATCH WARNING", 
					JOptionPane.WARNING_MESSAGE);
			result = "forced";
			
		} else {
			int response = JOptionPane.showConfirmDialog(gui, 
					"Sensor values differ by " + v + ".\nDo you accept this warning?", 
					"SENSOR MISMATCH WARNING", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			result = (response == JOptionPane.YES_OPTION) ? "true" : "false";
		}
		return context.getSymbols().createString(result);
	}

    @Override
    public boolean mayBeStandalone()
    {
        return false;
    }

    @Override
    public boolean mayBeValue()
    {
        return true;
    }


}
