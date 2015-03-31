package server.commands;

/**
 * 
 * @author oxbor
 *
 */
public class ResetGameCommand implements Command {

    protected boolean success = false;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
        this.success = true;
	}

    public boolean wasSuccessful() {return this.success;}

}
