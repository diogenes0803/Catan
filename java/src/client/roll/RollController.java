package client.roll;

import client.base.*;
import shared.model.GameModelFacade;
import shared.model.ModelException;
import shared.model.ServerModelFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RollController extends Controller implements IRollController {
    private final static Logger logger = Logger.getLogger("catan");

	private IRollResultView resultView;
    private Timer m_rollTimer;

    private static final int TIMEOUT_MS = 4000;


	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		setResultView(resultView);

        m_rollTimer = new Timer(TIMEOUT_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        GameModelFacade.instance().getGame().addObserver(this);
    }
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	@Override
	public void rollDice() {
        logger.entering("client.roll.RollController", "RollDice");

        getRollView().closeThisModal();
        m_rollTimer.stop();

        try {
            int rollValue = ServerModelFacade.getInstance().rollNumber();
            getResultView().setRollValue(rollValue);
            getResultView().showModal();
        }
        catch (ModelException e) {
            logger.log(Level.WARNING, "Rolling failed.", e);
        }
        logger.exiting("client.roll.RollController", "RollDice");
	}

    @Override
    public void update(Observable o, Object arg) {
        logger.entering("client.roll.RollController", "update", o);

        if (GameModelFacade.instance().localPlayerIsRolling()) {
            if (!m_rollTimer.isRunning()) {
                m_rollTimer.restart();
            }

            IRollView view = getRollView();
            if (!view.isModalShowing()) {
                view.setMessage("Roll the dice!");
                view.showModal();
            }
        }

        logger.exiting("client.roll.RollController", "update");
    }
}

