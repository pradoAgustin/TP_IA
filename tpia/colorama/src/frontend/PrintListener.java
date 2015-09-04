package frontend;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import backend.Board;
import backend.Listener;

public class PrintListener implements Listener{
	private static final long sleepTime=100;
	private ColoramaFrame frame;
	@SuppressWarnings("unused")
	private PrintWriter file;
	public PrintListener(ColoramaFrame f){
		frame=f;
	}
	
	@Override
	public void printToScreen() {
		if(frame!=null){
			//frame.hide();
			frame.colorSquares();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

    @Override
	public void changeBoard(Board b) {
		frame.changeBoard(b);
	}
}