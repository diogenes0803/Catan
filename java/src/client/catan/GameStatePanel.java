package client.catan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.base.IAction;
import shared.definitions.CatanColor;
import shared.model.Game;


@SuppressWarnings("serial")
public class GameStatePanel extends JPanel
{
	private JButton button;
	
	public GameStatePanel()
	{
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		button = new JButton();
		
		Font font = button.getFont();
		Font newFont = font.deriveFont(font.getStyle(), 20);
		button.setFont(newFont);

		button.setPreferredSize(new Dimension(400, 50));

		this.add(button);
		
		updateGameState("Waiting for other Players", false, CatanColor.WHITE);
	}
	
	public void updateGameState(String stateMessage, boolean enable, CatanColor playerColor)
	{
		button.setText(stateMessage);
		button.setEnabled(enable);
        if (enable) {
            button.setBackground(playerColor.getJavaColor());
            button.setContentAreaFilled(false);
            button.setOpaque(true);
        }
	}
	
	public void setButtonAction(final IAction action)
	{
		ActionListener[] listeners = button.getActionListeners();
		for(ActionListener listener : listeners) {
            button.setBackground(Color.white);
		}
		
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				button.setBackground(Color.white);
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                action.execute();
			}
		};
		button.addActionListener(actionListener);
	}
}

